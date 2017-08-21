package server.jetty.servlets;

import server.database.repositories.model.Staff;
import server.database.repositories.StaffRepository;
import server.wiring.Wiring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class AddServlet extends HttpServlet {

    private Wiring wiring;

    public AddServlet(Wiring wiring) {
        this.wiring = wiring;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader()
                .lines().collect(joining(lineSeparator()));

        System.out.println(String.format("Received request to add body to %s: '%s'%n", wiring.databaseProperties().databaseURL(), body));

        Staff order = Staff.unmarshal(body);
        try {
            StaffRepository repository = wiring.staffRepository();
            repository.insert(order);
            repository.getAll().write(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}