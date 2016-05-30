<%@page import="accesscontrol.db.DBUtils"%>
<%
    
    DBUtils.connect();
    DBUtils.deleteWiFi(request.getParameter("id"));
%>

<script>
    alert("Wifi Deleted");
    window.location.href = "register_wifi.jsp";
</script>