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

import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.QuickReply;
import uk.co.bubblebearapps.motionaiclient.QuickReplyList;
import uk.co.bubblebearapps.motionaiclient.entity.QuickReplyEntity;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@Singleton
public class QuickReplyEntityMapper {

    private static final String URL_MARKDOWN_REGEX = "\\[([^\\]]+)\\]\\(([^)]+)\\)";
    private static final Pattern URL_MARKDOWN_PATTERN = Pattern.compile(URL_MARKDOWN_REGEX);


    @Inject
    public QuickReplyEntityMapper() {

    }

    private QuickReply map(QuickReplyEntity quickReplyEntity) {


        String title = quickReplyEntity.getTitle();

        QuickReply.Builder builder = new QuickReply.Builder()
                .setId(quickReplyEntity.getId());

        Matcher matcher = URL_MARKDOWN_PATTERN.matcher(title);
        if (matcher.find()) {
            builder.setType(QuickReply.QuickReplyType.URL);
            builder.setTextContent(matcher.group(2));
            builder.setPayload(matcher.group(1));
        } else {
            builder.setType(QuickReply.QuickReplyType.TEXT);
            builder.setTextContent(quickReplyEntity.getTitle());
        }

        return builder.build();


    }


    QuickReplyList map(ResponseEntity responseEntity) {
        if (responseEntity == null || responseEntity.getQuickReplies() == null || responseEntity.getQuickReplies().length == 0) {
            return null;
        }

        QuickReplyList.Builder builder = new QuickReplyList.Builder()
                .setSessionId(responseEntity.getSession())
                .setTimeStamp(DateTime.now());

        for (QuickReplyEntity quickReplyEntity : responseEntity.getQuickReplies()) {
            builder.addQuickReply(map(quickReplyEntity));
        }

        return builder.build();
    }
}
