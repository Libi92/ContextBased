<%@page import="accesscontrol.db.DBUtils"%>
<%
    
    DBUtils.connect();
    DBUtils.deleteEvent(request.getParameter("id"));
%>

<script>
    alert("Event deleted");
    window.location.href="add_event.jsp";
</script>