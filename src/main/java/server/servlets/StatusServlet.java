package server.servlets;

import database.BasicDatabaseBuilder;
import model.Probe;
import model.Status;
import settings.DatabaseSettings;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.EnvironmentVariableReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static utils.Settings.getDatabaseSettings;

public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        List<Probe> probes = asList(BasicDatabaseBuilder.build("sky").probe());

        new Status(probes).json().write(response.getWriter());
    }
}
