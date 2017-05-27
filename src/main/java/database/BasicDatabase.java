package database;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.EnvironmentVariableReader;
import utils.PropertiesReader;

import java.sql.*;

import static database.sql.Query.queryAllFromTable;

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
        Statement statement = connection.createStatement(); //Default ResultSet => TYPE_FORWARD_ONLY (Read More)

        ResultSet result = statement.executeQuery(queryAllFromTable("country"));
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
