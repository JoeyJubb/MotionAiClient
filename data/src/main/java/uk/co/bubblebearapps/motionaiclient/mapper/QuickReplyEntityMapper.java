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

import java.util.ArrayList;
import java.util.List;

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


    @Inject
    public QuickReplyEntityMapper() {

    }

    public QuickReply map(QuickReplyEntity quickReplyEntity) {

        return new QuickReply()
                .setId(quickReplyEntity.getId())
                .setTextContent(quickReplyEntity.getTitle()
                );


    }


    public QuickReplyList map(ResponseEntity responseEntity) {
        if (responseEntity == null || responseEntity.getQuickReplies() == null || responseEntity.getQuickReplies().length == 0) {
            return null;
        }


        List<QuickReply> result = new ArrayList<>(responseEntity.getQuickReplies().length);
        for (QuickReplyEntity quickReplyEntity : responseEntity.getQuickReplies()) {
            result.add(map(quickReplyEntity));
        }

        return new QuickReplyList(responseEntity.getSession(), DateTime.now(), result);
    }
}
