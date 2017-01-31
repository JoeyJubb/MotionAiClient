package uk.co.bubblebearapps.motionaiclient.entity;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class ResponseEntity {

    @Nullable
    @SerializedName("botResponse")
    private String botResponse;

    @SerializedName("result")
    private String result;

    @SerializedName("inReplyTo")
    private String inReplyTo;

    @SerializedName("module")
    private int module;

    @SerializedName("cards")
    private CardEntity[] cards;

    @SerializedName("session")
    private String session;

    @SerializedName("quickReplies")
    private QuickReplyEntity[] quickReplies;

    @SerializedName("code")
    /**
     * @see https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
     */
    private int responseCode;

    private boolean autoReply;

    public ResponseEntity() {
    }

    public boolean isAutoReply() {
        return autoReply;
    }

    public ResponseEntity setAutoReply(boolean autoReply) {
        this.autoReply = autoReply;
        return this;
    }

    @Nullable
    public String getBotResponse() {

        return botResponse;
    }

    public ResponseEntity setBotResponse(@Nullable String botResponse) {
        this.botResponse = botResponse;
        return this;
    }

    public String getResult() {
        return result;
    }

    public ResponseEntity setResult(String result) {
        this.result = result;
        return this;
    }

    public String getInReplyTo() {
        return inReplyTo;
    }

    public ResponseEntity setInReplyTo(String inReplyTo) {
        this.inReplyTo = inReplyTo;
        return this;
    }

    public int getModule() {
        return module;
    }

    public ResponseEntity setModule(int module) {
        this.module = module;
        return this;
    }

    public CardEntity[] getCards() {
        return cards;
    }

    public ResponseEntity setCards(CardEntity[] cards) {
        this.cards = cards;
        return this;
    }

    public String getSession() {
        return session;
    }

    public ResponseEntity setSession(String session) {
        this.session = session;
        return this;
    }

    public QuickReplyEntity[] getQuickReplies() {
        return quickReplies;
    }

    public ResponseEntity setQuickReplies(QuickReplyEntity[] quickReplies) {
        this.quickReplies = quickReplies;
        return this;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public ResponseEntity setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

}
