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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.Message;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 02/02/2017.
 */
@PerActivity
public class MessageModelMapper {


    @Inject
    public MessageModelMapper() {

    }

    public List<MessageModel> map(List<Message> messages, DateTime timeStamp) {

        if (messages == null || messages.size() == 0) {
            return Collections.emptyList();
        }

        ArrayList<MessageModel> messageModels = new ArrayList<>(messages.size());

        for (Message message : messages) {
            messageModels.add(map(message, timeStamp));
        }

        return messageModels;

    }

    private MessageModel map(Message message, DateTime timeStamp) {
        return new MessageModel()
                .setTimeStamp(timeStamp)
                .setLocalId(UUID.randomUUID().toString())
                .setTarget(message.getPayload())
                .setType(message.getType())
                ;
    }
}
