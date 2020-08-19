package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
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
 *
 * @author anton
 */
@WebServlet("/getall")
public class GetAll extends HttpServlet {
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
        String html = "<h3>By clicking on an user ID, you can delete him</h3><form action='./DeleteUser' method='post'>"
                + "<table><tr><th>Client ID</th><th>Username</th>"
                + "<th>Type</th></tr>";
        
        try{
            List<User> userList = dbMethod.getAll();
            
            for(User u: userList){
                int id = u.getId();
                String username = u.getUsername();
                String type = u.getType();
                
                html += "<tr><td><input onclick='confirm()' type='submit' name='id' value='"+ id + "' /></td><td>"+ username +"</td><td>"+ type +"</td></tr>";
            }
            
            html += "</table> </form><br/><br/>";
            out.print(html);
        } catch(Exception ex){
            response.sendRedirect("./errorPage.html");
        }
    }
}