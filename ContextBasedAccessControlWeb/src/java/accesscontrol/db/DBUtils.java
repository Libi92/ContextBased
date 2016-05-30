/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesscontrol.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class DBUtils {
    private static String connectionUrl = "jdbc:mysql://localhost:3306/accesscontrol?user=root&password=mysql";
    private static Connection con;
    
    public static boolean connect() {
        try {
            if(con==null) {
                Class.forName(com.mysql.jdbc.Driver.class.getCanonicalName());
                con = DriverManager.getConnection(connectionUrl);

                if(con!=null)
                    return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    

    public static boolean addRegister(String userName,String password,String emailId,String phone,String address,String usertype, String image) {
        
        try {
            Statement stmt = con.createStatement();
            
            
            String sql = "INSERT INTO USERS (USERNAME,PASSWORD,EMAILID,PHONE,ADDRESS,USERTYPE,APPROVAL, IMAGE) VALUES "
                    + "('"+userName+"','"+password+"','"+emailId+"','"+phone+"','"+address+"','"+usertype+"','waiting', '" + image + "')";
            
            if(stmt.executeUpdate(sql)>0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean updateUser(String userId, String userName, String emailId,String phone,String address,String usertype) {
        
        try {
            Statement stmt = con.createStatement();
            
            
            String sql = "UPDATE USERS SET USERNAME='" + userName + "', EMAILID='" + emailId + "', PHONE='" + phone + "', ADDRESS='" + address + "', USERTYPE='" + usertype + "' WHERE USERID=" + userId;
            
            if(stmt.executeUpdate(sql)>0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static void deleteUser(String userId){
        try {
            Statement statement = con.createStatement();
            String sql = "DELETE FROM USERS WHERE USERID=" + userId;
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean login(String userName,String password) {
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM USERS WHERE USERNAME='"+userName+"' AND PASSWORD='"+password+"'";
            
            if(stmt.executeQuery(sql).next())
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<String[]>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM USERS";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while(rs.next()) {
               String[] user = new String[rsmd.getColumnCount()];
               
               for(int i=0;i<user.length;i++) {
                  user[i] = rs.getString(rsmd.getColumnName(i+1));
               }
                       
               users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    public static String[] getUserbyId(String id){
        String[] data = new String[]{};
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM USERS WHERE USERNAME='" + id + "'";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                data = new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9)};
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
   
    public static String getEmailID(String userName) {
        try {
            String sql = "SELECT EMAILID FROM USERS WHERE USERNAME='"+userName+"'";
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                return rs.getString("EMAILID");
            } else
                return "";
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return null;
    }
    
    
 
    public static String getUserType(String userName) {
        try {
            String sql = "SELECT USERTYPE FROM USERS WHERE USERNAME='"+userName+"'";
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                return rs.getString("USERTYPE");
            } else
                return "";
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static List<String[]> getWifis() {
        List<String[]> wifis = new ArrayList<String[]>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM WIFIS";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while(rs.next()) {
               String[] wifi = new String[rsmd.getColumnCount()];
               
               for(int i=0;i<wifi.length;i++) {
                  wifi[i] = rs.getString(rsmd.getColumnName(i+1));
               }
                       
               wifis.add(wifi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return wifis;
    }

    public static List<String[]> getRooms() {
        List<String[]> rooms = new ArrayList<String[]>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM ROOMS";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while(rs.next()) {
               String[] room = new String[rsmd.getColumnCount()];
               
               for(int i=0;i<room.length;i++) {
                  room[i] = rs.getString(rsmd.getColumnName(i+1));
               }
                       
               rooms.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rooms;
    }
    
    public static boolean addWifi(String wifiid) {
        try {
            String sql = "INSERT INTO WIFIS (WIFINAME) VALUES('"+wifiid+"')";
            
            Statement stmt = con.createStatement();
            
            if(stmt.executeUpdate(sql)>0) {
                return true;
            } else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static void deleteWiFi(String wifiId){
        try {
            String sql = "DELETE FROM WIFIS WHERE WIFIID=" + wifiId;
            con.createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean addRoom(String roomName, String wifiid) {
        try {
            String sql = "INSERT INTO ROOMS (ROOMNAME,WIFINAME) VALUES('"+roomName+"','"+wifiid+"')";
            
            Statement stmt = con.createStatement();
            
            if(stmt.executeUpdate(sql)>0) {
                return true;
            } else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static void deleteRoom(String roomId){
        try {
            String sql = "DELETE FROM ROOMS WHERE ROOMID=" + roomId;
            con.createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<String[]> getPermissions() {
        List<String[]> permissions = new ArrayList<String[]>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT * FROM PERMISSIONS";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while(rs.next()) {
               String[] permission = new String[rsmd.getColumnCount()];
               
               for(int i=0;i<permission.length;i++) {
                  permission[i] = rs.getString(rsmd.getColumnName(i+1));
               }
                       
               permissions.add(permission);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return permissions;
    }

    public static boolean addPermission(String userName,String roomName, String microphone, String camera, String bluetooth, String time, String latitude, String longitude) {

        try {
            Statement stmt = con.createStatement();
            
            
            String sql = "INSERT INTO PERMISSIONS (USERNAME,ROOMNAME,MICROPHONE,CAMERA,BLUETOOTH,TIME, LATITUDE, LONGITUDE) VALUES "
                    + "('"+userName+"','"+roomName+"','"+microphone+"','"+camera+"','"+bluetooth+"','"+time+"', '" + latitude + "', '" + longitude + "')";
            
            if(stmt.executeUpdate(sql)>0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public static boolean updatePermission(String id, String userName,String roomName, String microphone, String camera, String bluetooth, String time, String latitude, String longitude) {

        try {
            Statement stmt = con.createStatement();
            
            
            String sql = "UPDATE PERMISSIONS SET USERNAME='" + userName + "', ROOMNAME='" + roomName + "', MICROPHONE='" + microphone + "', CAMERA='" + camera + "', BLUETOOTH='" + bluetooth + "', TIME='" + time + "', LATITUDE='" + latitude + "', LONGITUDE='" + longitude + "' WHERE PERMISSIONID=" + id;
            
            if(stmt.executeUpdate(sql)>0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public static void deletePermission(String id){
        try {
            String sql = "DELETE FROM permissions WHERE PERMISSIONID=" + id;
            Statement statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean addEvent(String event, String details, String date, String time, String location, String latitude, String longitude){
        
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO events (event, details, date, time, location, latitude, longitude) VALUES ('" + event + "', '" + details + "', '" + date + "', '" + time + "', '" + location + "', '" + latitude + "', '" + longitude + "')";
            
            if(stmt.executeUpdate(sql)>0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean updateEvent(String eventId, String event, String details, String date, String time, String location, String latitude, String longitude){
        
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE events SET event='" + event + "', details='" + details + "', date='" + date + "', time='" + time + "', location='" + location + "', latitude='" + latitude + "', longitude='" + longitude + "' WHERE eventid=" + eventId;
            
            if(stmt.executeUpdate(sql)>0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static void deleteEvent(String eventId){
        try {
            String sql = "DELETE FROM events WHERE eventid=" + eventId;
            con.createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<String[]> getEvents(){
        List<String[]> events = new ArrayList<>();
        try {
            
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM events";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {                
                String[] event = new String[]{rs.getString("event"), rs.getString("details"), rs.getString("date"), rs.getString("time"), rs.getString("location"), rs.getString("eventid"), rs.getString("latitude"), rs.getString("longitude")};
                events.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return events;
    }
    
    public static String getPermissionLocation(String username){
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT LATITUDE, LONGITUDE FROM permissions WHERE USERNAME='" + username + "'";
            
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1) + "," + rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "-1";
    }
   
 }
