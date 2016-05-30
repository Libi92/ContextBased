<%@page import="accesscontrol.db.DBUtils"%>
<%
    
    String id = request.getParameter("id");
    DBUtils.connect();
    DBUtils.deletePermission(id);
    
%>

<script>
    alert("Permission Deleted");
    window.location.href="add_permissions.jsp";
</script>