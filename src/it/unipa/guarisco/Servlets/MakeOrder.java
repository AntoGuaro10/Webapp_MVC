package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.Order;
import it.unipa.guarisco.model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che permette ad un utente di effettuare un ordine
 * @author anton
 */
@WebServlet("/MakeOrder")
public class MakeOrder extends HttpServlet {
    DBUtilities dbMethod = new DBUtilities();
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int idclient = user.getId();
        String description = request.getParameter("menu");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));
        Order order = new Order(idclient, description, quantity, price, "Incomplete");
        
        if(dbMethod.createOrder(order) != 1){
            session.invalidate();
            response.sendRedirect("./errorPage.html");
        }else{
            request.setAttribute("order", order);
            RequestDispatcher rd = request.getRequestDispatcher("./orderSuccess.jsp");
            rd.forward(request, response);
        }
    }
}