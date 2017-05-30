package server.servlets;

import repositories.PortInRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            PortInRepository repository = new PortInRepository();
            repository.getAll().write(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}