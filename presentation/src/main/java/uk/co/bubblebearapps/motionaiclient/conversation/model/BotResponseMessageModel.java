package uk.co.bubblebearapps.motionaiclient.conversation.model;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import uk.co.bubblebearapps.motionaiclient.BotResponse;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class BotResponseMessageModel extends MessageModel {

    private String target;
    private BotResponse.Type type;
    private String sessionId;

    public BotResponseMessageModel(String localId, DateTime timeStamp) {
        super(localId, timeStamp);
    }

    public String getSessionId() {
        return sessionId;
    }

    public BotResponseMessageModel setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public BotResponseMessageModel setTarget(String target) {
        this.target = target;
        return this;
    }

    @NonNull
    @Override
    public String getContentsHash() {
        return target;
    }

    public BotResponse.Type getType() {
        return type;
    }

    public BotResponseMessageModel setType(BotResponse.Type type) {
        this.type = type;
        return this;
    }
}
