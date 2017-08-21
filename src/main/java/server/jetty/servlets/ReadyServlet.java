package server.jetty.servlets;

import server.wiring.Wiring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class ReadyServlet extends HttpServlet {

//    private Wiring wiring;

    public ReadyServlet(Wiring wiring) {
//        this.wiring = wiring;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(OK_200);
        response.getWriter().write("OK");
    }
}
