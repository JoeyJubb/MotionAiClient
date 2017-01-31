package uk.co.bubblebearapps.motionaiclient;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class User {
    private String id;
    private String title;

    public User() {
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public User setTitle(String title) {
        this.title = title;
        return this;
    }
}
