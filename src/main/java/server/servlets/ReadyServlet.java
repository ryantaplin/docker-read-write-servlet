package server.servlets;

import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
//        response.getWriter().write("FAIL");

        response.setStatus(HttpStatus.OK_200);
        response.getWriter().write("OK");
    }
}
