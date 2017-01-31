package uk.co.bubblebearapps.motionaiclient;

import java.util.List;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class Card {

    private String id;

    private String subTitle;

    private String title;

    public String getId() {
        return id;
    }

    public Card setId(String id) {
        this.id = id;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Card setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Card setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Card setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Card setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<CardButton> getCardButtons() {
        return cardButtons;
    }

    public Card setCardButtons(List<CardButton> cardButtons) {
        this.cardButtons = cardButtons;
        return this;
    }

    private String url;

    private String imageUrl;

    private List<CardButton> cardButtons;


}
