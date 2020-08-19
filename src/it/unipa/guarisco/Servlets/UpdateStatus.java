package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che aggiorna lo stato di un ordine effettuato
 * @author anton
 */
@WebServlet("/updatestatus")
public class UpdateStatus extends HttpServlet {
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
        response.setContentType("text/html");
        try{
            PrintWriter out = response.getWriter();
            int id = Integer.parseInt(request.getParameter("id"));
            if(dbMethod.updateOrderStatus(id) != 1){
                out.print("<script> alert('Please retry'); location.replace('./cook.jsp');</script>");
            }else{
                out.print("<script> alert('Order completed!'); location.replace('./cook.jsp'); </script>");
            }
        } catch(IOException | NumberFormatException ex){
            request.getSession().invalidate();
            ex.printStackTrace();
            response.sendRedirect("./errorPage.html");
        }
        
        
    }

}
