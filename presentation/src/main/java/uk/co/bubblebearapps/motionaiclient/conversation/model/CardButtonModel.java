package uk.co.bubblebearapps.motionaiclient.conversation.model;

import uk.co.bubblebearapps.motionaiclient.CardButton;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class CardButtonModel {
    private String id;
    private String target;
    private CardButton.ButtonType type;
    private String label;

    public CardButtonModel() {
    }

    public String getId() {
        return id;
    }

    public CardButtonModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public CardButtonModel setTarget(String target) {
        this.target = target;
        return this;
    }

    public CardButton.ButtonType getType() {
        return type;
    }

    public CardButtonModel setType(CardButton.ButtonType type) {
        this.type = type;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public CardButtonModel setLabel(String label) {
        this.label = label;
        return this;
    }

    public void visit(CardButton.Visitor visitor){

        switch(type){

            case URL:
                visitor.onVisitUrl(getTarget());
                break;
            case MESSAGE:
                visitor.onVisitMessage(getTarget());
                break;
        }

    }

    /**
     * Created by joefr_000 on 23/01/2017.
     */
    public static interface Visitor {
        void onVisitUrl(String target);

        void onVisitMessage(String target);
    }
}
