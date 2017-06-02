package model;

import org.json.JSONObject;

public class Staff {
    public String id = "";
    public String title = "";
    public String firstname = "";
    public String surname = "";

    public Staff(String id, String msisdn, String service, String date) {
        this.id = id;
        this.title = date;
        this.firstname = msisdn;
        this.surname = service;
    }

    public static Staff unmarshal(String request) {
        JSONObject json = new JSONObject(request);
        return new Staff(json.getString("id"), json.getString("title"), json.getString("firstname"), json.getString("surname"));
    }

    public boolean isComplete() {
        return !id.isEmpty() && !firstname.isEmpty() && !surname.isEmpty() && !title.isEmpty();
    }
}
