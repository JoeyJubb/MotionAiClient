package uk.co.bubblebearapps.motionaiclient.conversation.model;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public abstract class MessageModel {

    public MessageModel(String localId, DateTime timeStamp) {
        this.timeStamp = timeStamp;
        this.localId = localId;
    }

    private DateTime timeStamp;
    private String localId;

    public abstract String getContentsHash();

    public String getLocalId() {
        return localId;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }
}
