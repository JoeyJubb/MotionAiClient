package uk.co.bubblebearapps.motionaiclient.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class QuickReplyEntity {

    @SerializedName("title")
    private String title;

    @SerializedName("_id")
    private String id;

    @SerializedName("payload")
    private Object payload;

    public String getTitle() {
        return title;
    }

    public QuickReplyEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public QuickReplyEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Object getPayload() {
        return payload;
    }

    public QuickReplyEntity setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public QuickReplyEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @SerializedName("content_type")
    /**
     * Can be one of "text"
     */
    private String contentType;

}
