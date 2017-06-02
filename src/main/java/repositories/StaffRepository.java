package repositories;

import database.BasicDatabase;
import model.Staff;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.Settings.getDatabaseSettings;

public class StaffRepository implements Repository {

    private String TABLE_NAME = "staff";
    private BasicDatabase database;

    public StaffRepository() throws SQLException {
        this.database = new BasicDatabase(getDatabaseSettings());
    }

    @Override
    public JSONArray getAll() throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s", TABLE_NAME)));
    }

    @Override
    public JSONArray getById(int id) throws SQLException {
       return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, StaffColumn.ID, id)));
    }

    @Override
    public JSONArray find(String column, String criteria) throws SQLException {
        if (!StaffColumn.exists(column))
            throw new NullPointerException(String.format("Column '%s' does not exist in table '%s'", column, TABLE_NAME));

        return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, column, criteria)));
    }

    @Override
    public void insert(Staff order) throws SQLException {
        if (order.isComplete()) throw new IllegalArgumentException("");
        database.update(String.format("INSERT INTO %s VALUES (%s,%s,%s)", TABLE_NAME,
                order.title, order.firstname, order.surname));
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
            obj.put("id", result.getInt(StaffColumn.ID.toString()));
            obj.put("title", result.getString(StaffColumn.TITLE.toString()));
            obj.put("First Name", result.getString(StaffColumn.FIRSTNAME.toString()));
            obj.put("firstname", result.getString(StaffColumn.SURNAME.toString()));
            array.put(obj);
        }
        return array;
    }
}
