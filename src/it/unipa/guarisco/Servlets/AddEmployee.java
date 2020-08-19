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
 * Servlet utilizzata per la creazione di un nuovo dipendente
 * @author anton
 */
@WebServlet("/addemployee")
public class AddEmployee extends HttpServlet {
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
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("pass");
            String type = request.getParameter("type");
            int id = checkEmployee(username);

            if(id > 0){
                createEmployee(id, username, password, type);
                PrintWriter out = response.getWriter();
                out.println("<script> alert('New employee added successfully!'); location.replace('./adminPage.jsp'); </script>");
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("./errorPage.html");
                rd.forward(request, response);
            }
        
        } catch(IOException | ServletException ex){
            response.sendRedirect("./errorPage.html");
        }
    }        
        
            
    
    /**
     * Verifica che l'username non esista già nel DB e gli assegna un ID
     * @param username
     * @return 
     */
    private int checkEmployee(String username){
        List<User> employeeList = dbMethod.getAllUsers();
        
        for(User u : employeeList){
            if(u.getUsername().equals(username)){
                return 0;            
            }
        }
               
        int size = employeeList.size() - 1;
        int id = (employeeList.get(size).getId()) + 1;
        return id;
    }    
    
    /**
     * Fa la richiesta alla classe di utilità e crea il dipendente nel Database
     * @param id
     * @param name
     * @param pass
     * @param type 
     */
    private void createEmployee(int id, String name, String pass, String type){
        User user = new User(id, name, pass, type);     
        dbMethod.createEmployee(user);
    }
}