package server.servlets;

import database.BasicDatabase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            BasicDatabase database = new BasicDatabase();

            response.setContentType("application/json");
            database.runQuery().write(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}