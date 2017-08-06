package server.database.repositories;

import server.database.MySQLDatabase;
import server.database.DatabaseBuilder;
import server.database.repositories.model.Staff;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRepository implements Repository {

    private String TABLE_NAME = "staff";
    private MySQLDatabase database;

    public StaffRepository() throws SQLException {
        this.database = DatabaseBuilder.build("sky");
    }

    @Override
    public JSONArray getAll() throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s", TABLE_NAME)));
    }

    @Override
    public JSONArray find(StaffColumn column, String criteria) throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, column, criteria)));
    }

    @Override
    public void insert(Staff order) throws SQLException {
        if (order.notComplete()) throw new IllegalArgumentException("order is incomplete");

        database.update(String.format("INSERT INTO %s (title, firstname, surname) VALUES\n" +
                "('%s', '%s', '%s');", TABLE_NAME, order.title, order.firstname, order.surname));
    }

    @Override
    public void removeById(int id) {
        throw new NotImplementedException();
    }

    @Override
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
