package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce la registrazione di un nuovo utente
 * @author anton
 */
@WebServlet("/newUser")
public class NewUser extends HttpServlet {
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int id = checkUser(username);
        
        if(id > 0){
            User user = createUser(id, username, password);
            request.getSession().setMaxInactiveInterval(60 * 5);
            request.getSession().setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("./userInfo.jsp");
            rd.forward(request, response);
        }else{
            request.getSession().invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("./errorPage.html");
            rd.forward(request, response);
        }
    }
    
    /**
     * Controlla che l'username sia unico
     * @param username
     * @return 
     */
    private int checkUser(String username){
        List<User> userList = dbMethod.getAllUsers();
        
        for(User u : userList){
            if(u.getUsername().equals(username)){
                return 0;            
            }
        }
         
        int size = userList.size() - 1;
        return userList.get(size).getId() + 1;
    }    
    
    /**
     * Inoltra la richiesta al Database e crea il nuovo utente
     * @param id
     * @param name
     * @param pass
     * @return 
     */
    private User createUser(int id, String name, String pass){
        String type = "client";
        User user = new User(id, name, pass, type);        
        dbMethod.createUser(user);
        return user;
    }
    
}