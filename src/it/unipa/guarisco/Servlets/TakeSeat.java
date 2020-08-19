package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.Seat;
import it.unipa.guarisco.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che permette ad un utente di occupare una sola sdraio
 * @author anton
 */
public class TakeSeat extends HttpServlet {
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
        User user = (User) (request.getSession().getAttribute("user"));
        int id = user.getId();
        String []queryString = request.getQueryString().split("-");
        int idSeat = Integer.parseInt(queryString[0]);
        int posX = Integer.parseInt(queryString[1]);
        String []subQuery = queryString[2].split("=");
        int posY = Integer.parseInt(subQuery[0]);
        
        Seat seat = new Seat(idSeat, id, posX, posY, false);
        HttpSession session = request.getSession();
        session.setAttribute("seat", seat);
        if(updateSeatStatus(seat) != 1){        
            out.print("<script> if(confirm('You can book only one seat!')){"
                    + "location.replace('./userInfo.jsp'); }"
                    + "else{ window.close(); }  </script>");
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("./userInfo.jsp");
            rd.forward(request, response);
        }
    }
    
    /**
     * Aggiorna lo stato di una sdraio effettuando una richiesta al Database
     * @param s
     * @return 
     */
    private int updateSeatStatus(Seat s){
        if(dbMethod.updateSeat(s) == 1){
            return 1;
        }else{
            return -1;
        }
    }
}