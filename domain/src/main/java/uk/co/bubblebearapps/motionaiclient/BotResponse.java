package uk.co.bubblebearapps.motionaiclient;

import org.joda.time.DateTime;

import java.util.List;


/**
 * Created by joefr_000 on 23/01/2017.
 */
public class BotResponse {

    private String target;

    private Type type;

    private List<Card> cards;

    private String sessionId;

    private List<QuickReply> quickReplies;

    private DateTime timeStamp;

    public String getTarget() {
        return target;
    }

    public BotResponse setTarget(String target) {
        this.target = target;
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public BotResponse setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public BotResponse setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public BotResponse setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
        return this;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public BotResponse setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public Type getType() {
        return type;
    }

    public BotResponse setType(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "BotResponse{" +
                "target='" + target + '\'' +
                ", type='" + type + '\'' +
                ", timeStamp='" + timeStamp.toString() + '\'' +
                '}';
    }

    /**
     * Created by joefr_000 on 27/01/2017.
     */
    public static enum Type {
        IMAGE, TEXT, VIDEO, UNKNOWN, YOUTUBE
    }
}
