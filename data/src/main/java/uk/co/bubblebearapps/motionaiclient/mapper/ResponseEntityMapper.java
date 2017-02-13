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

import com.google.common.base.Strings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
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

    private static final String IMG_TAG_REGEX = "\\[(img|video|youtube)\\]([\\s\\S]+?)\\[/\\1\\]";
    private static final String BB_CODE_REMOVER_REGEX = "(\\[/?(img|video|youtube)])";
    private static final String BREAK_PATTERN_REGEX = "(::next(-[0-9]+)?::)";


    private static final String TAG = "ResponseEntityMapper";

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

            List<String> sectionList = StringUtils.splitButKeep(responseEntity.getBotResponse(), IMG_TAG_REGEX);

            ArrayList<Message> botResponseList = new ArrayList<>();
            for (String section : sectionList) {

                String[] paragraphList = section.split(BREAK_PATTERN_REGEX);

                for (String paragraph : paragraphList) {

                    String target = paragraph.replaceAll(BB_CODE_REMOVER_REGEX, "").trim();
                    if (Strings.isNullOrEmpty(target)) {
                        continue;
                    }

                    Message message = new Message().setPayload(target);

                    if (paragraph.startsWith("[img]")) {
                        message.setType(Message.Type.IMAGE);
                    } else if (paragraph.startsWith("[video]")) {
                        message.setType(Message.Type.VIDEO);
                    } else if (paragraph.startsWith("[youtube]")) {
                        message.setType(Message.Type.YOUTUBE);
                    } else {
                        message.setType(Message.Type.TEXT);
                    }

                    botResponseList.add(message);

                }
            }


            return Observable.just(new BotResponse()
                    .setQuickReplies(quickReplyEntityMapper.map(responseEntity.getQuickReplies()))
                    .setCards(cardEntityMapper.map(responseEntity.getCards()))
                    .setMessages(botResponseList)
                    .setSessionId(responseEntity.getSession())
                    .setTimeStamp(DateTime.now()));


        } else {
            return Observable.error(new UnknownError());
        }

    }


}
