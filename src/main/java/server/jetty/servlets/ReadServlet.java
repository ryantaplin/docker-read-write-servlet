package server.jetty.servlets;

import server.database.repositories.StaffRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReadServlet extends HttpServlet {

    private StaffRepository staffRepository;

    public ReadServlet(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setContentType("application/json");
            staffRepository.getAll().write(response.getWriter());
        } catch (SQLException | IOException error) {
            System.out.println("There was a problem reading and displaying data from the read servlet: \n"  +  error);
        }
    }
}