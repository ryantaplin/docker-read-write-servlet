package server.servlets;

import model.Staff;
import repositories.StaffRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.printf("Received request to add body to database: '%s'%n", body);

        Staff order = Staff.unmarshal(body);
        try {
            StaffRepository repository = new StaffRepository();
            repository.insert(order);
            repository.getAll().write(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}