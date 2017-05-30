package repositories;

import model.PortInOrder;
import org.json.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Repository {

    JSONArray getAll() throws SQLException;
    JSONArray getById(int id) throws SQLException;
    JSONArray find(String column, String criteria) throws SQLException;

    JSONArray convertResultsToJson(ResultSet result) throws SQLException;

    void insert(PortInOrder order) throws SQLException;

    void removeById(int id);

}
