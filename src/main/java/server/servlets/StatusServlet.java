package server.servlets;

import database.BasicDatabaseBuilder;
import settings.DatabaseSettings;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.EnvironmentVariableReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Settings.getDatabaseSettings;

public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        statusPageAsJSON().write(response.getWriter());
    }

    private JSONObject statusPageAsJSON() {
        JSONObject obj = new JSONObject();
        JSONArray probes = getProbes();

        obj.put("Status", getOverallStatus(probes)); //hard coded; needs implementing
        obj.put("probes", probes);
        obj.put("Environment", EnvironmentVariableReader.getEnvironment());

        return obj;
    }

    private String getOverallStatus(JSONArray probes) {
        for (int i = 0; i < probes.length(); i++) {
            if (probes.getJSONObject(i).toString().equals("FAIL")) return "FAIL";
        }
        return "OK";
    }

    private JSONArray getProbes() {
        JSONArray array = new JSONArray();
        array.put(getDatabaseProbe());
        return array;
    }

    private JSONObject getDatabaseProbe()  {
        JSONObject obj = new JSONObject();
        DatabaseSettings settings = getDatabaseSettings();

        obj.put("Name", "MySQL Database");
        obj.put("URL", settings.databaseURL() + settings.databaseName());
        obj.put("User", settings.databaseUsername());
        obj.put("Status", BasicDatabaseBuilder.build().status());

        return obj;
    }
}
