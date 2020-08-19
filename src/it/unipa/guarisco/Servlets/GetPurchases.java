package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che fornisce la lista di tutti gli ordini effettuati e li mostra al "Cashier"
 * @author anton
 */
@WebServlet("/getpurchases")
public class GetPurchases extends HttpServlet {
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
        String html = "<table><tr><th>Order ID</th><th>Client ID</th><th>Description</th>"
                + "<th>Quantity</th><th>Price for order</th><th>Status</th></tr>";
        
        try{
            List<Order> purchaseList = dbMethod.getAllPurchases();
            float tot = 0;
            for(Order o: purchaseList){
                int id = o.getId();
                int idc = o.getIdclient();
                String des = o.getDescription();
                int q = o.getQuantity();
                float p = o.getPrice();
                tot += p;
                String status = o.getStatus();
                
                html += "<tr><td>"+ id + "</td><td>"+ idc +"</td><td>"+ des +"</td>"
                        + "<td>"+ q +"</td><td>"+ p + "</td><td>"+ status +"</td></tr>";
            }
            
            html += "</table><br/><br/>Total cash flow: <div>"+ tot+ "</div>";
            
            out.print(html);
        } catch(Exception ex){
            response.sendRedirect("./errorPage.html");
        }
    }
}