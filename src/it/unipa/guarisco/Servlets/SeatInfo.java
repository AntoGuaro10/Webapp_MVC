package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.model.Seat;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che raccoglie le informazioni corrispondenti alla sdraio selezionata
 * e inoltra la richiesta alla jsp corretta
 * @author anton
 */
public class SeatInfo extends HttpServlet {

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
        try{            
            String []queryString = request.getQueryString().split("-");
            
            if(queryString.length == 4){
                int idSeat = Integer.parseInt(queryString[0]);
                int posX = Integer.parseInt(queryString[1]);
                int posY = Integer.parseInt(queryString[2]);
                String []subQuery = queryString[3].split("=");
                boolean av = Boolean.parseBoolean(subQuery[0]);

                Seat info = new Seat(idSeat, posX, posY, av);
                request.setAttribute("info", info);
                RequestDispatcher rd = request.getRequestDispatcher("./seatinfo2.jsp");
                rd.forward(request, response);
            }else{
                int idSeat = Integer.parseInt(queryString[0]);
                int idClient = Integer.parseInt(queryString[1]);
                int posX = Integer.parseInt(queryString[2]);
                int posY = Integer.parseInt(queryString[3]);
                String []subQuery = queryString[4].split("=");
                boolean av = Boolean.parseBoolean(subQuery[0]);

                Seat info = new Seat(idSeat, idClient, posX, posY, av);
                request.setAttribute("info", info);
                RequestDispatcher rd = request.getRequestDispatcher("./seatinfo.jsp");
                rd.forward(request, response);
            }
        } catch(IOException | NumberFormatException | ServletException ex){
            PrintWriter out = response.getWriter();
            out.print("<script> alert('Something go wrong'); location.replace('./lifeguard.jsp'); </script>");
        }
               
    }
}