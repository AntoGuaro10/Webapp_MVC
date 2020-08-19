package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.SeatList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che richiede la lista delle sdraio al Database e inoltra la richiesta alla jsp corrispondente
 * @author anton
 */
public class LoadSeats extends HttpServlet {
    DBUtilities dbMethod = new DBUtilities();
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SeatList seatList = dbMethod.getAllSeats();
        request.setAttribute("seatlist", seatList);
        RequestDispatcher rd = request.getRequestDispatcher("./showseats.jsp");
        rd.forward(request, response);
    }
}
