package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce il login degli utenti
 * @author anton
 */
@WebServlet("/login")
public class Login extends HttpServlet {
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
        String username = request.getParameter("email");
        String password = request.getParameter("pssw");
        
        login(checkUser(username, password), request, response);
        
    }
    
    /**
     * Controlla che le credenziali siano corrette
     * @param username
     * @param password
     * @return 
     */
    private User checkUser(String username, String password){
        List<User> userList = dbMethod.getAllUsers();
        
        for(User e : userList){
            if(e.getUsername().equals(username) && e.getPassword().equals(password)){
                return e;
            }
        }
        return null;
    }
    
    /**
     * In base al "type" dell'utente, inoltra la richiesta alla jsp corretta
     * @param e
     * @param request
     * @param response 
     */
    private void login(User e, HttpServletRequest request, HttpServletResponse response){
        try{
            request.getSession().setMaxInactiveInterval(60 * 10);
            if(e == null){
                PrintWriter out = response.getWriter();
                out.print("<script> if(confirm('Incorrect username or password, press ok for retry')){"
                        + "location.replace('/guarisco'); }"
                        + "else{"
                        + "window.close();} </script>");
            }else{
               if(e.getType().equalsIgnoreCase("admin")){
                    request.getSession().setAttribute("employee", e);
                    RequestDispatcher rd = request.getRequestDispatcher("./adminPage.jsp");
                    rd.forward(request, response);
                } else if(e.getType().equalsIgnoreCase("client")){
                    request.getSession().setAttribute("user", e);
                    RequestDispatcher rd = request.getRequestDispatcher("./userInfo.jsp");
                    rd.forward(request, response);
                } else if(e.getType().equalsIgnoreCase("cashier")){
                    request.getSession().setAttribute("employee", e);
                    RequestDispatcher rd = request.getRequestDispatcher("./cashier.jsp");
                    rd.forward(request, response);
                } else if(e.getType().equalsIgnoreCase("lifeguard")){
                    request.getSession().setAttribute("employee", e);
                    RequestDispatcher rd = request.getRequestDispatcher("./lifeguard.jsp");
                    rd.forward(request, response);
                } else if(e.getType().equalsIgnoreCase("cook")){
                    request.getSession().setAttribute("employee", e);
                    RequestDispatcher rd = request.getRequestDispatcher("./cook.jsp");
                    rd.forward(request, response);
                } 
            }
        } catch(NullPointerException | ServletException | IOException ex){
            ex.printStackTrace();
        }           
    }

}
