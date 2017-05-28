package database;

import database.sql.Column;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.EnvironmentVariableReader;
import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicDatabase {

    private Connection connection;
    private DatabaseSettings settings;

    public BasicDatabase() throws SQLException {
        String environment = EnvironmentVariableReader.getSystemEnvironment();
        settings = new DatabaseSettings(new PropertiesReader(environment));

        this.connection = DriverManager.getConnection(settings.databaseURL(), settings.databaseUsername(), settings.databasePassword());
        System.out.println(String.format("Accessing database '%s' with '%s'.", settings.databaseURL(), settings.databaseUsername()));
    }

    public JSONArray runQuery() throws SQLException {
        database.sql.Statement a = new database.sql.Statement(connection);
        List<Column> cols = new ArrayList<>();
        cols.add(new Column("*"));
        a.select(cols).from("country");

        ResultSet result = a.execute();
        JSONArray array = new JSONArray();
        while (result.next()) {
            JSONObject obj = new JSONObject();
            obj.put("Code", result.getString("Code"));
            obj.put("Name", result.getString("Name"));
            obj.put("Continent", result.getString("Continent"));
            array.put(obj);
        }
        return array;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public String status() throws SQLException {
        return connection.isValid(settings.databaseTimeout()) ? "OK" : "FAIL";
    }
}
