<%-- 
    Document   : success.jsp
    Created on : Jan 30, 2016, 12:42:28 PM
    Author     : Chandramouliswaran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status Page</title>
    </head>
    <body>
        <h1><%= session.getAttribute("status") %></h1>
    </body>
</html>
