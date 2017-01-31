package uk.co.bubblebearapps.motionaiclient.conversation.model;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

/**
 * Created by joefr_000 on 24/01/2017.
 */

public class UserInputMessageModel extends MessageModel {

    public String getText() {
        return text;
    }

    private String text;

    public UserInputMessageModel(String localId, DateTime timeStamp, String text) {
        super(localId, timeStamp);
        this.text = text;
    }

    @Override
    public String getContentsHash() {
        return text;
    }

}
