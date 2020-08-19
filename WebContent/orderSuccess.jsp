<%-- 
    Document   : orderSuccess
    Created on : 25-giu-2020, 10.26.53
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order success JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="order" type="it.unipa.guarisco.model.Order" scope="request" />
        <h3>Your order including <jsp:getProperty name="order" property="quantity" /> <jsp:getProperty name="order" property="description" />
            is been processing!
        </h3><br/><br/>
        <a href="userInfo.jsp">Back to your page</a>            
    </body>
</html>
