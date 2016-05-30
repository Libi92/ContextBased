/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesscontrol;

import accesscontrol.db.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Libin
 */
@WebServlet(name = "UpdatePermissionServlet", urlPatterns = {"/UpdatePermissionServlet"})
public class UpdatePermissionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String id = request.getParameter("permissionid");
        String userName = request.getParameter("username");
        String roomName = request.getParameter("roomname");
        String microphone = request.getParameter("microphone");
        String camera = request.getParameter("camera");
        String bluetooth = request.getParameter("bluetooth");
        String time = request.getParameter("time");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        
        DBUtils.connect();
        
        if(DBUtils.updatePermission(id, userName,roomName,microphone,camera,bluetooth,time, latitude, longitude)) {
            PrintWriter out = response.getWriter();
            out.print("<script>\n" +
"    alert(\"Permission Updated\");\n" +
"    window.location.href=\"add_permissions.jsp\";\n" +
"</script>");
        } else
            response.sendRedirect("login.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
