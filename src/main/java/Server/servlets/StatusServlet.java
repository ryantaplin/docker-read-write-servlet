package Server.servlets;

import Utils.Properties.EnvironmentVariableReader;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        statusPageAsJSON().write(response.getWriter());
    }

    private JSONObject statusPageAsJSON() {
        JSONObject obj = new JSONObject();

        obj.put("Status", "OK"); //hard coded; needs implementing
        obj.put("Environment", EnvironmentVariableReader.getEnvironment());

        return obj;
    }


}
