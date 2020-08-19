package it.unipa.guarisco.Servlets;

import it.unipa.guarisco.Database.DBUtilities;
import it.unipa.guarisco.model.Seat;
import it.unipa.guarisco.model.SeatList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che restituisce al "Lifeguard" la panoramica delle sdraio
 * @author anton
 */
public class SeatState extends HttpServlet {
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
            
            response.setContentType("text/html");
            String html="`<form action='./SeatInfo' >";
            
            try{
                PrintWriter pw = response.getWriter();
                SeatList list = dbMethod.getAllSeats();
                
                List<Seat> seatlist = list.getList();
                for(int i = 0; i < 4; i++){ 
                    for(int j=0; j<4; j++){
                        
                        int x = list.getPos(i, j, seatlist).getPosX();
                        int y = list.getPos(i, j, seatlist).getPosY();
                        boolean av = list.getPos(i, j, seatlist).isAvailable();
                        int idSeat = list.getPos(i, j, seatlist).getId();
                        
                            if(av == true && y <= 3){
                                html += "<input type='submit' name='"+ idSeat +"-"+ x + "-"+ y + "-available' class='free' value='free'></input>";
                            }else if(av == false && y <= 3){
                                int idC = list.getPos(x, y, seatlist).getIdclient();
                                html += "<input type='submit' name='"+ idSeat +"-"+ idC +"-"+ x +"-"+ y +"-not available' class='occupied' value='occupied'></input>";
                            }
                                                            
                    }
                    html += "<br/><br/>";                    
                }
                html += "<br/></form>`";
                pw.print(html);
            } catch(IOException ex){
                ex.printStackTrace();
            }           
    }
}