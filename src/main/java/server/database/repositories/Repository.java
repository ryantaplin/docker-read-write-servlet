package server.database.repositories;

import server.database.repositories.model.Staff;
import org.json.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Repository {

    JSONArray getAll() throws SQLException;
    JSONArray find(StaffColumn column, String criteria) throws SQLException;
    JSONArray convertResultsToJson(ResultSet result) throws SQLException;

    void insert(Staff order) throws SQLException;
    void removeById(int id);

}
