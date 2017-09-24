package server.database.repositories;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum StaffColumn {
    ID,
    TITLE,
    FIRSTNAME,
    SURNAME;

    public String getValueAsString(JSONObject obj) {
        String value = obj.getString(String.valueOf(this));
        return value != null ? value : "NO VALUE FOUND";
    }
}
