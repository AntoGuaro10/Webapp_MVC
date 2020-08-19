package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.Order;
import it.unipa.guarisco.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet per visualizzare gli ordini effettuati
 * @author anton
 */
@WebServlet("/checkorder")
public class CheckOrder extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        User user = (User) request.getSession().getAttribute("user");
        int idclient = user.getId();
        
        String html = "<table><tr><th>Order ID</th><th>Description</th><th>Quantity</th><th>Price for order</th><th>Status</th></tr>";
        
        try{
            List<Order> orderList = dbMethod.getUserOrders(idclient);
            
            for(Order o: orderList){
                int id = o.getId();
                String des = o.getDescription();
                int q = o.getQuantity();
                float p = o.getPrice();
                String status = o.getStatus();
                
                html += "<tr><td>"+ id + "</td><td>"+ des +"</td><td>"+ q +"</td><td>"+ p + "</td><td>"+ status +"</td></tr>";
            }
            
            html += "</table>";
            
            out.print(html);
        } catch(Exception ex){
            response.sendRedirect("./errorPage.html");
        }
    }

}
