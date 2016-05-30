<%@page import="accesscontrol.db.DBUtils"%>
<%
    DBUtils.connect();
    DBUtils.deleteUser(request.getParameter("id"));
%>

<script>
    alert("User Deleted");
    window.location.href="register.jsp";
</script>