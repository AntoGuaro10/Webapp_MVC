package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet utile a modificare le credenziali di un utente
 * @author anton
 */
@WebServlet("/update")
public class Update extends HttpServlet {
    DBUtilities dbMethod = new DBUtilities();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        User user = (User) request.getSession().getAttribute("user");
        
        updateUser(user.getId(), password, request, response);
    }
    
    /**
     * Servlet che interroga il Database e inoltra la richiesta all'URL specifico
     * @param id
     * @param newpssw
     * @param request
     * @param response 
     */
    private void updateUser(int id, String newpssw, HttpServletRequest request, HttpServletResponse response){
        try{
            if(dbMethod.updateUser(id, newpssw) == 1){
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                response.sendRedirect("./userInfo.jsp");
                out.print("<script>window.alert('Update succesfull!');</script>");
            }else{
                request.getSession().invalidate();
                response.sendRedirect("./errorPage.html");
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }
            
    }
}