<%@page import="accesscontrol.db.DBUtils"%>
<%
    
    DBUtils.connect();
    DBUtils.deleteRoom(request.getParameter("id"));
%>

<script>
    alert("Room Deleted");
    window.location.href="register_room.jsp";
</script>