package server.jetty.servlets;

import server.database.repositories.StaffRepository;
import server.wiring.Wiring;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReadServlet extends HttpServlet {

    private Wiring wiring;

    public ReadServlet(Wiring wiring) {
        this.wiring = wiring;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setContentType("application/json");
            StaffRepository repository = wiring.staffRepository();
            repository.getAll().write(response.getWriter());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}