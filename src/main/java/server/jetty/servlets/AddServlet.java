package server.jetty.servlets;

import server.database.repositories.model.Staff;
import server.database.repositories.StaffRepository;
import utils.properties.DatabaseProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class AddServlet extends HttpServlet {

    private DatabaseProperties databaseProperties;
    private StaffRepository staffRepository;

    public AddServlet(DatabaseProperties databaseProperties, StaffRepository staffRepository) {
        this.databaseProperties = databaseProperties;
        this.staffRepository = staffRepository;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader()
                .lines().collect(joining(lineSeparator()));

        System.out.println(String.format("Received request to add body to %s: '%s'%n", databaseProperties.databaseURL(), body));

        Staff order = Staff.unmarshal(body);
        try {
            StaffRepository repository = staffRepository;
            repository.insert(order);
            repository.getAll().write(response.getWriter());
        } catch (SQLException error) {
            System.out.println("There was a problem inserting, reading or displaying data from the add servlet: \n"  +  error);
        }
    }
}