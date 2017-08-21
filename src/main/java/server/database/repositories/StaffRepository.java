package server.database.repositories;

import server.database.Database;
import server.database.repositories.model.Staff;
import org.json.JSONArray;
import org.json.JSONObject;
import server.wiring.Wiring;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRepository implements Repository {

    private String TABLE_NAME = "staff";
    private Database database;

    public StaffRepository(Wiring wiring) throws SQLException {
        this.database = wiring.database();
    }

    public JSONArray getAll() throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s", TABLE_NAME)));
    }

    public JSONArray find(StaffColumn column, String criteria) throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, column, criteria)));
    }

    public void insert(Staff order) throws SQLException {
        if (order.notComplete()) throw new IllegalArgumentException("order is incomplete");

        database.update(String.format("INSERT INTO %s (title, firstname, surname) VALUES\n" +
                "('%s', '%s', '%s');", TABLE_NAME, order.title, order.firstname, order.surname));
    }

    public void removeById(int id) {
        throw new NotImplementedException();
    }

    public JSONArray convertResultsToJson(ResultSet result) throws SQLException {
        JSONArray array = new JSONArray();
        while (result.next()) {
            JSONObject obj = new JSONObject();
            obj.put("title", result.getString(StaffColumn.TITLE.toString()));
            obj.put("First Name", result.getString(StaffColumn.FIRSTNAME.toString()));
            obj.put("firstname", result.getString(StaffColumn.SURNAME.toString()));
            array.put(obj);
        }
        return array;
    }
}
