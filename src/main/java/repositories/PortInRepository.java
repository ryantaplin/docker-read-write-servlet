package repositories;

import database.BasicDatabase;
import model.PortInOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.Settings.getDatabaseSettings;

public class PortInRepository implements Repository {

    private String TABLE_NAME = "PortIn";
    private BasicDatabase database;

    public PortInRepository() throws SQLException {
        this.database = new BasicDatabase(getDatabaseSettings());
    }

    @Override
    public JSONArray getAll() throws SQLException {
        return convertResultsToJson(database.query(String.format("SELECT * FROM %s", TABLE_NAME)));
    }

    @Override
    public JSONArray getById(int id) throws SQLException {
       return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, PortInColumn.ID, id)));
    }

    @Override
    public JSONArray find(String column, String criteria) throws SQLException {
        if (!PortInColumn.exists(column))
            throw new NullPointerException(String.format("Column '%s' does not exist in table '%s'", column, TABLE_NAME));

        return convertResultsToJson(database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, column, criteria)));
    }

    @Override
    public void insert(PortInOrder order) throws SQLException {
        if (order.isComplete()) throw new IllegalArgumentException("");
        database.update(String.format("INSERT INTO %s VALUES (%s,%s,%s,%s)", TABLE_NAME,
                            order.ID, order.MSISDN, order.SERVICE, order.DATE));
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
            obj.put("ID", result.getInt(PortInColumn.ID.toString()));
            obj.put("MSISDN", result.getInt(PortInColumn.MSISDN.toString()));
            obj.put("SERVICE", result.getInt(PortInColumn.SERVICE.toString()));
            obj.put("DATE", result.getDate(PortInColumn.DATE.toString()));
            array.put(obj);
        }
        return array;
    }
}
