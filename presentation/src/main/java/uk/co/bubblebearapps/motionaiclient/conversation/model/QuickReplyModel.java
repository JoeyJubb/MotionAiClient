package uk.co.bubblebearapps.motionaiclient.conversation.model;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class QuickReplyModel{

    private String textContent;

    private String id;


    public String getTextContent() {
        return textContent;
    }

    public QuickReplyModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getId() {
        return id;
    }

    public QuickReplyModel setId(String id) {
        this.id = id;
        return this;
    }

}
