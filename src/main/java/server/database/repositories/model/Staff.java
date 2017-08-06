package server.database.repositories.model;

import org.json.JSONObject;

public class Staff {
    public String title = "";
    public String firstname = "";
    public String surname = "";

    public Staff(String title, String firstname, String surname) {
        this.title = title;
        this.firstname = firstname;
        this.surname = surname;
    }

    public static Staff unmarshal(String request) {
        JSONObject json = new JSONObject(request);
        return new Staff(json.getString("title"), json.getString("firstname"), json.getString("surname"));
    }

    public boolean notComplete() {
        return firstname.isEmpty() || surname.isEmpty() || title.isEmpty();
    }
}
