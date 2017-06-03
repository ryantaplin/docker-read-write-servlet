package repositories;

import database.BasicDatabase;
import database.BasicDatabaseBuilder;
import model.Staff;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRepository implements Repository {

    private String TABLE_NAME = "staff";
    private BasicDatabase database;

    public StaffRepository() throws SQLException {
        this.database = BasicDatabaseBuilder.as("sky").build();
    }

    @Override
    public JSONArray getAll() throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s", TABLE_NAME)));
    }

    @Override
    public JSONArray getBySurname(String surname) throws SQLException {
       return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, StaffColumn.SURNAME, surname)));
    }

    @Override
    public JSONArray find(String column, String criteria) throws SQLException {
        if (!StaffColumn.exists(column))
            throw new NullPointerException(String.format("Column '%s' does not exist in table '%s'", column, TABLE_NAME));

        return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, column, criteria)));
    }

    @Override
    public void insert(Staff order) throws SQLException {
        if (order.isComplete()) throw new IllegalArgumentException("order is incomplete");

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
