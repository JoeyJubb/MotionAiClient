package uk.co.bubblebearapps.motionaiclient.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class CardEntityButton {

    @SerializedName("_id")
    private String id;

    @SerializedName("target")
    private String target;

    @SerializedName("buttonType")
    private String type;
    @SerializedName("buttonText")
    private String label;

    public String getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    /**
     * Can be one of "module" or "url"
     */
    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

}
