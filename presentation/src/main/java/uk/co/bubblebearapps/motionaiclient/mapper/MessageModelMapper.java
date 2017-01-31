package uk.co.bubblebearapps.motionaiclient.mapper;

import java.util.List;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.conversation.model.BotResponseMessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@PerActivity
public class MessageModelMapper {

    private final CardModelMapper cardModelMapper;
    private final QuickReplyModelMapper quickReplyModelMapper;

    @Inject
    public MessageModelMapper(CardModelMapper cardModelMapper, QuickReplyModelMapper quickReplyModelMapper) {
        this.cardModelMapper = cardModelMapper;
        this.quickReplyModelMapper = quickReplyModelMapper;
    }


    public BotResponseBundle map(BotResponse botResponse, String localId) {
        return new BotResponseBundle()
                .setCards(cardModelMapper.map(botResponse.getCards()))
                .setQuickReplies(quickReplyModelMapper.map(botResponse.getQuickReplies()))
                .setBotResponse(new BotResponseMessageModel(localId, botResponse.getTimeStamp())
                        .setTarget(botResponse.getTarget())
                        .setSessionId(botResponse.getSessionId())
                        .setType(botResponse.getType())

                );

    }

    public static class BotResponseBundle {

        private BotResponseMessageModel botResponse;
        private List<CardModel> cards;
        private List<QuickReplyModel> quickReplies;

        public List<CardModel> getCards() {
            return cards;
        }

        public BotResponseBundle setCards(List<CardModel> cards) {
            this.cards = cards;
            return this;
        }

        public List<QuickReplyModel> getQuickReplies() {
            return quickReplies;
        }

        public BotResponseBundle setQuickReplies(List<QuickReplyModel> quickReplies) {
            this.quickReplies = quickReplies;
            return this;
        }

        public BotResponseMessageModel getBotResponse() {
            return botResponse;
        }

        public BotResponseBundle setBotResponse(BotResponseMessageModel botResponse) {
            this.botResponse = botResponse;
            return this;
        }
    }

}
