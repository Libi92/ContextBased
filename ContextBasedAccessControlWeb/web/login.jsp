<%-- 
    Document   : login
    Created on : Jan 31, 2016, 1:03:16 PM
    Author     : Chandramouliswaran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginServlet" method="post">
            User Name :
            <br/>
            <input type="text" id="username" name="username"/>
            <br/>
            Password :
            <br/>
            <input type="password" id="password" name="password"/>
            <br/>
            <input type="submit" id="submit" name="submit" value="Login"/>
            <br/>
        </form>
    </body>
</html>
