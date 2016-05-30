/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesscontrol;

import accesscontrol.db.DBUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Chandramouliswaran
 */
public class RegisterServlet extends HttpServlet {

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

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            System.out.println("multipart");
        } else {
            System.out.println("not multipart");
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String fileName = null;
        String fileKey = null;
        HashMap<String, String> itemMap = new HashMap<String, String>();

        //Processing file upload //////////////////////////////////////////
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setSizeThreshold(500 * 1024 * 1024);
        File fileLocation = File.createTempFile("energy", "tmp");
        fileItemFactory.setRepository(fileLocation);
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
        try {
            List items = fileUpload.parseRequest(request);
            for (Object object : items) {
                FileItem item = (FileItem) object;
                if (item.isFormField()) {

                    fileKey = item.getFieldName();
                    fileName = item.getString();
                    itemMap.put(fileKey, fileName);

                } else {

                    String root = getServletContext().getRealPath("");
                    File path = new File(root + "/uploads");
                    if (!path.exists()) {
                        boolean status = path.mkdirs();
                    }

                    fileKey = item.getFieldName();
                    fileName = item.getName();
                    itemMap.put(fileKey, fileName);

                    String filename1 = fileName.substring(fileName.lastIndexOf("/") + 1);
                    System.out.println(filename1);
                    File file = new File(path + "/" + filename1);
                    try {
                        item.write(file);

                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                }

            }
            
            String usertype = itemMap.get("usertype");
        
            String userName = itemMap.get("username");
            String password = itemMap.get("password");
            String emailid = itemMap.get("emailid");
            String phone = itemMap.get("phone");
            String address = itemMap.get("address");
            String image = itemMap.get("photo");
            
            
            DBUtils.connect();
            
            if(DBUtils.addRegister(userName, password, emailid, phone, address, usertype, image)) {
                request.getSession().setAttribute("status", "Success");
            } else
                request.getSession().setAttribute("status", "Failed");
                    
       
            response.sendRedirect("register.jsp");

        } catch (FileUploadException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
