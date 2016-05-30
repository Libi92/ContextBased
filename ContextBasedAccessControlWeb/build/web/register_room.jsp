<%@page import="java.util.List"%>
<%@page import="accesscontrol.db.DBUtils"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
    <head>
        <title>The Kebrum Website Template :: w3layouts</title>
        <link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    </script>
    <!----webfonts---->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <!----//webfonts---->
    <link href="css/theme.css" rel='stylesheet' type='text/css' />
    <script type="text/javascript" src="js/jquery.min.js"></script>		
    <!--start slider -->
    <link rel="stylesheet" href="css/fwslider.css" media="all">
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/css3-mediaqueries.js"></script>
    <script src="js/fwslider.js"></script>
    <!--end slider -->
    <!--  jquery plguin -->
    <script src="js/login.js"></script>		
    <script src="js/modernizr.custom.js"></script>


    <!-- Add fancyBox light-box -->
    <link rel="stylesheet" type="text/css" href="css/magnific-popup1.css">
    <script src="js/jquery.magnific-popup.js" type="text/javascript"></script>

    <!-- //End fancyBox light-box -->	

    <style>
        .feature-grid{
            width: 100%;
        }
    </style>
</head>
<body>

    <!----start-container----->
    <div class="header-bg" id="home">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="logo"><h1><a href="index.html">ACCESS CONTROL</a></h1></div>
                </div>
                <div class="col-md-8">					
                    <div class="h_right">
                        <div class="left">
                            <ul class="menu list-unstyled">		
                                <li class="active"><a href="admin_home.jsp" >Home</a></li>
                                <li><a href="./">Logout</a></li>

                            </ul>
                        </div>	

                        <nav class="nav">
                            <ul class="nav-list">
                                <li class="nav-item"><a href="#home">Home</a></li>
                                <li class="nav-item"><a href="#features" class="scroll">Features</a></li>
                                <li class="nav-item"><a href="#prices" class="scroll">Prices</a></li>
                                <li class="nav-item"><a href="#faq" class="scroll">Faq</a></li>
                                <li class="nav-item"><a href="#support" class="scroll">Support</a></li>				               
                                <div class="clearfix"></div>		
                            </ul>					            

                            <div class="nav-mobile"></div></nav>

                        <script type="text/javascript" src="js/responsive.menu.js"></script>
                        <!-- end smart_nav * -->
                    </div>

                </div>	      
            </div>
        </div>
    </div>	


    <%
        DBUtils.connect();
        final List<String[]> wifis = DBUtils.getWifis();
    %>

    <!--- features--->
    <div class="container">
        <div class="row">
            <div class="content-feature-grids" id="features">

                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Register Room</h3>
                        </div>
                        <div class="panel-body">
                            <form method="post" action="RegisterRoomServlet" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-3" for="roomname">Room Name</label>
                                    <div class="col-md-3">
                                        <input type="text" id="roomname" name="roomname" class="form-control" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3" for="wifiid">WiFi</label>
                                    <div class="col-md-3">
                                        <select id="wifiid" name="wifiid" class="form-control">
                                            <%
                                                for (String[] wifi : wifis) {
                                            %>
                                            <option value="<%= wifi[1]%>"><%=  wifi[1]%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">

                                    <div class="col-md-offset-3 col-md-3">
                                        <input type="submit" class="btn btn-primary" value="Register" />
                                    </div>
                                </div>
                            </form>
                        </div>
                        <table class="table table-hover">
                            <th>
                                ROOMID
                            </th>
                            <th>
                                ROOM NAME
                            </th>
                            <th>
                                WIFI NAME
                            </th>
                            <th></th>

                            <%
                                DBUtils.connect();

                                List<String[]> rooms = DBUtils.getRooms();

                                for (String[] room : rooms) {
                            %>
                            <tr>
                                <% for (String room1 : room) {

                                %>
                                <td><%= room1%></td>
                                <%
                                    }
                                %>
                                <td>
                                    <a href="deleteRoom.jsp?id=<%= room[0] %>" class="btn btn-danger">delete</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>

                <h1></h1>

            </div>


            <div class="footer">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">	
                            <div class="copy-right">
                                <p>&#169Copyright 2014 All Rights Reserved  Template <a href="http://w3layouts.com/">  w3layouts.com</a></p>	
                            </div>	
                        </div>
                    </div>
                </div>
            </div>
            <!-- scroll_top_btn -->
            <script type="text/javascript" src="js/move-top.js"></script>
            <script type="text/javascript" src="js/easing.js"></script>
            <script type="text/javascript">
                jQuery(document).ready(function ($) {
                    $(".scroll").click(function (event) {
                        event.preventDefault();
                        $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
                    });
                });
            </script>

            <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
            </body>
            </html>


