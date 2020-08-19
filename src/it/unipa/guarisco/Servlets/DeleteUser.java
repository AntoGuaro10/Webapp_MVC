package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anton
 */
public class DeleteUser extends HttpServlet {
    DBUtilities dbMethod = new DBUtilities();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            PrintWriter out = response.getWriter();
            dbMethod.deleteUser(id);            
            out.print("<script> alert('The user with ID: "+ id +" has been removed'); location.replace('./adminPage.jsp'); </script>");
        } catch(IOException ex){
            PrintWriter out = response.getWriter();
            out.print("<script> alert('An error occurs, please retry'); location.replace('./adminPage.jsp'); </script>");
        }        
    }
}