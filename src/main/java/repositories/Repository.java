package repositories;

import model.Staff;
import org.json.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Repository {

    JSONArray getAll() throws SQLException;
    JSONArray getBySurname(String surname) throws SQLException;
    JSONArray find(String column, String criteria) throws SQLException;

    JSONArray convertResultsToJson(ResultSet result) throws SQLException;

    void insert(Staff order) throws SQLException;

    void removeById(int id);

}
