/*
 * Copyright 2017 Bubblebear Apps Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.bubblebearapps.motionaiclient.mapper;

import android.support.annotation.Nullable;

import com.google.common.base.Strings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.Message;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;
import uk.co.bubblebearapps.motionaiclient.exception.EndOfConversationException;
import uk.co.bubblebearapps.motionaiclient.util.StringUtils;

/**
 * Created by joefr_000 on 23/01/2017.
 */

@Singleton
public class ResponseEntityMapper {

    private static final String TAG_REGEX = "\\[(img|video|youtube)\\]([\\s\\S]+?)\\[/\\1\\]";
    private static final String BB_CODE_REMOVER_REGEX = "(\\[/?(img|video|youtube)])";
    private static final String BREAK_PATTERN_REGEX = "(::next(-[0-9]+)?::)";

    private static final String NUMBER_REGEX = "([0-9]+)";
    private static final Pattern DELAY_AMOUNT_PATTERN = Pattern.compile(NUMBER_REGEX);


    private static final String TAG = "ResponseEntityMapper";

    /**
     * Softens the output by adding a small minimum delay between each message
     */
    private static final long MIN_DELAY_MILLIS = 300;

    private final CardEntityMapper cardEntityMapper;
    private final QuickReplyEntityMapper quickReplyEntityMapper;

    @Inject
    public ResponseEntityMapper(CardEntityMapper cardEntityMapper, QuickReplyEntityMapper quickReplyEntityMapper) {

        this.cardEntityMapper = cardEntityMapper;
        this.quickReplyEntityMapper = quickReplyEntityMapper;
    }

    public Observable<BotResponse> map(ResponseEntity responseEntity) {

        if (responseEntity.getResponseCode() == 300) {
            return Observable.error(new EndOfConversationException());
        } else if (responseEntity.getResponseCode() == 200) {

            List<ResponseDelayPair> messageDelayPairs = new ArrayList<>();

            // splits the message at the ::next:: tags
            List<String> paragraphs = StringUtils.splitButKeep(responseEntity.getBotResponse(), BREAK_PATTERN_REGEX);

            long runningDelay = 0;
            Matcher matcher;
            for (String paragraph : paragraphs) {
                final long thisDelay = runningDelay;

                // find the pause time
                matcher = DELAY_AMOUNT_PATTERN.matcher(paragraph);
                if (matcher.find()) {
                    runningDelay += Math.max(Long.parseLong(paragraph.substring(matcher.start(), matcher.end())), MIN_DELAY_MILLIS);
                } else {
                    runningDelay += MIN_DELAY_MILLIS;
                }


                String tidiedParagraph = paragraph.replaceAll(BREAK_PATTERN_REGEX, "").trim();
                if (Strings.isNullOrEmpty(tidiedParagraph)) {
                    continue;
                }


                // splits the message into bbcode chunks
                for (String bbcodeChunk : StringUtils.splitButKeep(tidiedParagraph, TAG_REGEX)) {
                    Message message = paragraphToMessage(responseEntity.getSession(), DateTime.now(), bbcodeChunk);
                    if (message == null) continue;
                    messageDelayPairs.add(new ResponseDelayPair(message, thisDelay));
                }
            }

            BotResponse cards = cardEntityMapper.map(responseEntity);
            if (cards != null) {
                runningDelay += MIN_DELAY_MILLIS;
                messageDelayPairs.add(new ResponseDelayPair(cards, runningDelay));
            }

            BotResponse quickReplies = quickReplyEntityMapper.map(responseEntity);
            if (quickReplies != null) {
                messageDelayPairs.add(new ResponseDelayPair(quickReplies, runningDelay));
            }

            return Observable.from(messageDelayPairs).flatMap(new Func1<ResponseDelayPair, Observable<BotResponse>>() {
                @Override
                public Observable<BotResponse> call(ResponseDelayPair responseDelayPair) {

                    BotResponse value = responseDelayPair.getValue();
                    return Observable.just(value).delay(responseDelayPair.getDelay(), TimeUnit.MILLISECONDS);

                }
            });


        } else {
            return Observable.error(new UnknownError());
        }

    }

    @Nullable
    private Message paragraphToMessage(String sessionId, DateTime timeStamp, String paragraph) {

        //  remove bbcode from paragraph
        String payload = paragraph.replaceAll(BB_CODE_REMOVER_REGEX, "").trim();
        if (Strings.isNullOrEmpty(payload)) {
            return null;
        }

        Message.Type type;
        if (paragraph.startsWith("[img]")) {
            type = Message.Type.IMAGE;
        } else if (paragraph.startsWith("[video]")) {
            type = Message.Type.VIDEO;
        } else if (paragraph.startsWith("[youtube]")) {
            type = Message.Type.YOUTUBE;
        } else {
            type = Message.Type.TEXT;
        }

        return new Message.Builder().setSessionId(sessionId).setTimeStamp(timeStamp).setType(type).setPayload(payload).build();
    }


    /**
     * Created by joefr_000 on 15/02/2017.
     */

    private static class ResponseDelayPair {

        private final long delay;
        private final BotResponse botResponse;

        ResponseDelayPair(BotResponse botResponse, long delay) {
            this.delay = delay;
            this.botResponse = botResponse;
        }

        long getDelay() {
            return delay;
        }

        public BotResponse getValue() {
            return botResponse;
        }
    }
}
