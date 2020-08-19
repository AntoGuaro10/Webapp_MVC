<%-- 
    Document   : showseats
    Created on : 22-giu-2020, 19.33.34
    Author     : anton
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="it.unipa.guarisco.model.Seat"%>
<%@page import="java.util.List"%>
<%@page import="it.unipa.guarisco.model.SeatList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of all seats</title>
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="script/seatFuncs.js" type="text/javascript"></script>
        <link rel="stylesheet" href="style/seatStyle.css" type="text/css">
    </head>
    <body onload="show()">
        <div id="result" style="position: absolute; top: 150px; left: 100px"></div>
        <h3 style="position: absolute; top: 0px; left: 10px">LEGEND: </h3>
        <label style="position: absolute; top: 40px; left: 100px"><button class="free" disabled="">FREE</button> The seat is free </label>
        <label style="position: absolute; top: 70px; left: 100px"><button class="occupied" disabled="">OCCUPIED</button> The seat is occupied </label><br/>        
        <button id="back" style="position: absolute; top: 100px; left: 100px">Go back</button> <br/>      
        
        <%
            String html="`<form action='./TakeSeat' >";
            PrintWriter pw = response.getWriter();
            try{
                SeatList list = (SeatList) request.getAttribute("seatlist");
                
                List<Seat> seatlist = list.getList();
                for(int i = 0; i < 4; i++){ 
                    for(int j=0; j<4; j++){
                        
                        int x = list.getPos(i, j, seatlist).getPosX();
                        int y = list.getPos(i, j, seatlist).getPosY();
                        boolean av = list.getPos(i, j, seatlist).isAvailable();
                        int idSeat = list.getPos(i, j, seatlist).getId();
                        
                            if(av == true && y <= 3){
                                html += "<input onclick='check()' type='submit' name='"+ idSeat +"-"+ x + "-"+ y + "' class='free' value='free'></input>";
                            }else if(av == false && y <= 3){
                                html += "<input type='submit' name='"+ idSeat+ "' class='occupied' value='occupied' disabled=''></input>";
                            }
                                                            
                    }
                    html += "<br/><br/>";                    
                }
                html += "<br/></form>`";
            } catch(Exception ex){
                ex.printStackTrace();
                response.sendRedirect("userInfo.jsp");
            }
            pw.print("<script> function show(){"
                        + " document.getElementById('result').innerHTML = "+html
                    +"} </script>");
        %>  
        
    </body>
</html>
