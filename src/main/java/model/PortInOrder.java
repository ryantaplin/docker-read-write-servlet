package model;

import org.json.JSONObject;

public class PortInOrder {
    public String ID = "";
    public String MSISDN = "";
    public String SERVICE = "";
    public String DATE = "";

    public PortInOrder(String id, String msisdn, String service, String date) {
        this.ID = id;
        this.MSISDN = msisdn;
        this.SERVICE = service;
        this.DATE = date;
    }

    public static PortInOrder unmarshal(String request) {
        JSONObject json = new JSONObject(request);
        return new PortInOrder(json.getString("ID"), json.getString("MSISDN"), json.getString("SERVICE"), json.getString("DATE"));
    }

    public boolean isComplete() {
        return !ID.isEmpty() && !MSISDN.isEmpty() && !SERVICE.isEmpty() && !DATE.isEmpty();
    }
}
