package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.Seat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che libera una sdraio occupata
 * @author anton
 */
public class FreeSeat extends HttpServlet {
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
        HttpSession session = request.getSession();
        try{
            
            Seat seat = (Seat) session.getAttribute("seat");
        
            if(seat == null){
            	dbMethod.freeOrders();
                session.invalidate();
                response.sendRedirect("./exit.html");
            }else{
                if(dbMethod.free(seat) == -1 || dbMethod.freeOrders() == -1){
                    response.sendRedirect("./errorPage.html");
                }else{
                    session.invalidate();
                    response.sendRedirect("./exit.html");
                }
            }
        } catch(IOException ex){
            session.invalidate();
            response.sendRedirect("./errorPage.html");
        }                    
    }
}