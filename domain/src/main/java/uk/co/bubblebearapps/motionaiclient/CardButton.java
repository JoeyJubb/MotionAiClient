package uk.co.bubblebearapps.motionaiclient;

public class CardButton {

    private String id;
    private String target;
    private ButtonType type;
    private String label;

    public CardButton() {
    }

    public String getId() {
        return id;
    }

    public CardButton setId(String id) {
        this.id = id;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public CardButton setTarget(String target) {
        this.target = target;
        return this;
    }

    public ButtonType getType() {
        return type;
    }

    public CardButton setType(ButtonType type) {
        this.type = type;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public CardButton setLabel(String label) {
        this.label = label;
        return this;
    }

    public void visit(Visitor visitor){

        switch(type){

            case URL:
                visitor.onVisitUrl(getTarget());
                break;
            case MESSAGE:
                visitor.onVisitMessage(getTarget());
                break;
        }

    }

    public enum ButtonType {

        URL, MESSAGE

    }

    /**
     * Created by joefr_000 on 23/01/2017.
     */
    public static interface Visitor {
        void onVisitUrl(String target);

        void onVisitMessage(String target);
    }
}