package uk.co.bubblebearapps.motionaiclient.conversation.model;

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.CardButton;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class CardModel {

    private String id;

    private String subTitle;

    private String title;
    private String url;
    private String imageUrl;
    private List<CardButtonModel> cardButtons;

    public String getId() {
        return id;
    }

    public CardModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public CardModel setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CardModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CardModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CardModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<CardButtonModel> getCardButtons() {
        return cardButtons;
    }

    public CardModel setCardButtons(List<CardButtonModel> cardButtons) {
        this.cardButtons = cardButtons;
        return this;
    }

}
