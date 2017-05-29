package server.servlets;

import database.BasicDatabase;
import database.DatabaseSettings;
import org.json.JSONArray;
import utils.EnvironmentVariableReader;
import org.json.JSONObject;
import utils.PropertiesReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        statusPageAsJSON().write(response.getWriter());
    }

    private JSONObject statusPageAsJSON() {
        JSONObject obj = new JSONObject();

        obj.put("Status", "OK"); //hard coded; needs implementing
        obj.put("probes", getProbes());
        obj.put("Environment", EnvironmentVariableReader.getSystemEnvironment());

        return obj;
    }

    private JSONArray getProbes() {
        JSONArray array = new JSONArray();
        array.put(getDatabaseProbe());
        return array;
    }

    private JSONObject getDatabaseProbe()  {
        JSONObject obj = new JSONObject();

        String environment = EnvironmentVariableReader.getSystemEnvironment();
        DatabaseSettings settings = new DatabaseSettings(new PropertiesReader(environment));

        obj.put("Name", "World Database");
        obj.put("URL", settings.databaseURL());
        obj.put("User", settings.databaseUsername());

        try {
            obj.put("Status", new BasicDatabase(settings).status());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;

    }


}
