package uk.co.bubblebearapps.motionaiclient;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class QuickReply {

    private String textContent;

    private String id;

    public String getTextContent() {
        return textContent;
    }

    public QuickReply setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getId() {
        return id;
    }

    public QuickReply setId(String id) {
        this.id = id;
        return this;
    }
}
