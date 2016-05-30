/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesscontrol.services;

import accesscontrol.db.DBUtils;
import accesscontrol.models.Eventschedule;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Chandramouliswaran
 */
@WebService(serviceName = "AccessControlService")
public class AccessControlService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        DBUtils.connect();
        
        if(DBUtils.login(username,password)) {
            return "success";
        } else
            return "failed";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getconfig")
    public String getconfig(@WebParam(name = "username") String username) {
        DBUtils.connect();
        final List<String[]> permissions = DBUtils.getPermissions();
        
        final List<String[]> perms = new ArrayList<String[]>();
        
        for(String[] perm : permissions) {
               if(perm[1].equals(username)) {
                   perms.add(perm);
               }
        }
            
        final List<String> perms1 = new ArrayList<String>();
        
        String perms2 = "";
        
        for(String[] perm : perms) {
            String perm1 = Arrays.toString(perm);
            perms2 += perm1+"<>";
        }
                
        return perms2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getwifiid")
    public String getwifiid(@WebParam(name = "roomname") String roomname) {
        //TODO write your implementation code here:
        DBUtils.connect();
        
        final List<String[]> rooms = DBUtils.getRooms();
        
        String wifiid = "";
                
        for(String[] room : rooms) {
            if(room[1].equals(roomname)) {
                wifiid = room[2];
                break;
            }
        }
                
        return wifiid;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPermissionLocation")
    public String getPermissionLocation(@WebParam(name = "username") String username) {
        DBUtils.connect();
        
        return DBUtils.getPermissionLocation(username);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEvents")
    public String getEvents(@WebParam(name = "username") String username) {
        
        List<Eventschedule> events = new ArrayList<>();
        
        DBUtils.connect();
        List<String[]> data = DBUtils.getEvents();
        for (String[] event : data) {
            Date date = new Date(event[2] + " " + event[3]);
            Date toDate = new Date(event[2] + " " + event[3]);
            Eventschedule eventschedule = new Eventschedule();
            eventschedule.setDate(event[2]);
            eventschedule.setFromtime(date.getTime());
            eventschedule.setLn(Double.parseDouble(event[6]));
            eventschedule.setLt(Double.parseDouble(event[7]));
            eventschedule.setPlace(event[4]);
            eventschedule.setTitle(event[0]);
            eventschedule.setTotime(toDate.getTime());
            
            
            events.add(eventschedule);
        }
        
        return new Gson().toJson(events);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProfile")
    public String getProfile(@WebParam(name = "userid") String userid) {
        System.out.println(userid);
        DBUtils.connect();
        String[] user = DBUtils.getUserbyId(userid);
        return new Gson().toJson(user);
    }

   
}
