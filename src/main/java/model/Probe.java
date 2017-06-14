package model;

import org.json.JSONObject;

public class Probe {
    public String name, status, description;

    public Probe(String name, String status, String description) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public JSONObject json() {
        JSONObject obj = new JSONObject();

        obj.put("Name", name);
        obj.put("Description", description);
        obj.put("Status", status);

        return obj;
    }
}
