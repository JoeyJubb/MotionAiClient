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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.CardList;
import uk.co.bubblebearapps.motionaiclient.Message;
import uk.co.bubblebearapps.motionaiclient.QuickReply;
import uk.co.bubblebearapps.motionaiclient.QuickReplyList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModelsList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModelsList;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@PerActivity
public class BotResponseModelMapper {


    @Inject
    public BotResponseModelMapper() {
    }


    public MessageModel map(Message message) {
        return new MessageModel()
                .setTimeStamp(message.getTimeStamp())
                .setLocalId(UUID.randomUUID().toString())
                .setTarget(message.getPayload())
                .setType(message.getType())
                ;
    }

    public QuickReplyModelsList map(QuickReplyList quickReplyList) {

        List<QuickReplyModel> quickReplyModels = new ArrayList<>(quickReplyList.getQuickReplyList().size());
        for (QuickReply quickReply : quickReplyList.getQuickReplyList()) {
            quickReplyModels.add(map(quickReply));
        }

        return new QuickReplyModelsList(UUID.randomUUID().toString(), quickReplyList.getTimeStamp(), quickReplyModels);


    }


    public QuickReplyModel map(QuickReply quickReply) {
        return new QuickReplyModel()
                .setId(quickReply.getId())
                .setTextContent(quickReply.getTextContent());
    }

    public CardModelsList map(CardList cardList, int botColor) {

        List<CardModel> cardModelList = new ArrayList<>(cardList.getCardList().size());
        for (Card card : cardList.getCardList()) {
            cardModelList.add(map(card, botColor));
        }

        return new CardModelsList(UUID.randomUUID().toString(), cardList.getTimeStamp(), cardModelList);


    }

    public CardModel map(Card card, int botColor) {
        return new CardModel()
                .setId(card.getId())
                .setImageUrl(card.getImageUrl())
                .setSubTitle(card.getSubTitle())
                .setTitle(card.getTitle())
                .setUrl(card.getUrl())
                .setCardButtons(map(card.getCardButtons(), botColor));
    }


    public List<CardButtonModel> map(List<CardButton> cardButtons, int botColor) {

        List<CardButtonModel> result = new ArrayList<>();
        for (CardButton cardButton : cardButtons) {
            result.add(map(cardButton, botColor));
        }

        return result;

    }


    public CardButtonModel map(CardButton card, int botColor) {
        return new CardButtonModel()
                .setId(card.getId())
                .setLabel(card.getLabel())
                .setType(card.getType())
                .setTarget(card.getTarget())
                .setButtonColor(botColor);

    }

}
