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

package uk.co.bubblebearapps.motionaiclient.conversation;

import uk.co.bubblebearapps.motionaiclient.Message;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModelsList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubbleVisitor;
import uk.co.bubblebearapps.motionaiclient.conversation.model.EndOfConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModelsList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.UserInputConversationBubble;
import uk.co.bubblebearapps.motionaiclient.model.BotInfoModel;

/**
 * Created by joefr_000 on 09/02/2017.
 */
public class ConversationBubbleDecorator implements ConversationBubbleVisitor {

    private final BotInfoModel botInfo;
    private int messageCount;
    private MessageModel mostRecentMessage;

    public ConversationBubbleDecorator(BotInfoModel botInfo) {
        this.botInfo = botInfo;
        reset();
    }

    @Override
    public void visit(CardModelsList cardModelsList) {
        reset();
    }

    private void markBottomMessage() {
        if (mostRecentMessage != null) {
            mostRecentMessage.setJuxtaposition(messageCount > 1 ? MessageModel.Juxtaposition.BOTTOM : MessageModel.Juxtaposition.ONLY);
        }
    }

    @Override
    public void visit(EndOfConversationBubble endOfConversationBubble) {
        reset();
    }

    @Override
    public void visit(MessageModel messageModel) {

        if (messageModel.getType().equals(Message.Type.TEXT)) {

            messageModel.setMessageBackgroundColor(botInfo.getColor());

            messageModel.setJuxtaposition(messageCount == 0 ? MessageModel.Juxtaposition.TOP : MessageModel.Juxtaposition.MIDDLE);

            messageCount++;
            mostRecentMessage = messageModel;
        } else {
            reset();
        }
    }

    @Override
    public void visit(QuickReplyModelsList quickReplyModelsList) {
        reset();
    }

    @Override
    public void visit(UserInputConversationBubble userInputConversationBubble) {
        reset();

    }

    @Override
    public void reset() {
        markBottomMessage();
        mostRecentMessage = null;
        messageCount = 0;
    }
}
