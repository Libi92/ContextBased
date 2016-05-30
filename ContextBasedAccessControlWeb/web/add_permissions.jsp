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

        <style>
            #map-canvas{
                height: 400px;
            }
        </style>
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

        <script src="https://maps.googleapis.com/maps/api/js"></script>
        <script>
            function initialize() {
                var mapCanvas = document.getElementById('map-canvas');
                var mapOptions = {
                    center: new google.maps.LatLng(9.9940, 76.2920),
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                }
                map = new google.maps.Map(mapCanvas, mapOptions);
                google.maps.event.addListener(map, 'click', function (event) {
                    var marker = new google.maps.Marker({
                        position: event.latLng,
                        draggable: true,
                        animation: google.maps.Animation.DROP,
                        map: map
                    });

                    google.maps.event.addListener(marker, 'click', function () {
                        marker.setMap(null);
                    });
                    google.maps.event.addListener(marker, 'dragend', function (event) {
                        $("#latitude").val(event.latLng.lat());
                        $("#longitude").val(event.latLng.lng());
                        console.log(event.latLng.lat());
                    });
                    $("#latitude").val(event.latLng.lat());
                    $("#longitude").val(event.latLng.lng());
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);

        </script>

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
            final List<String[]> rooms = DBUtils.getRooms();

            final List<String[]> users = DBUtils.getAllUsers();
        %>

        <!--- features--->
        <div class="container">
            <div class="row">
                <div class="content-feature-grids" id="features">

                    <div class="row">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Add Permission</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-md-6">
                                    <form method="post" action="AddPermissionServlet" class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-6" for="username">Select User</label>
                                            <div class="col-md-6">
                                                <select id="username" name="username" class="form-control">
                                                    <%
                                                        for (String[] user : users) {
                                                    %>
                                                    <option value="<%= user[1]%>"><%= user[1]%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-6" for="password">Select Room</label>
                                            <div class="col-md-6">
                                                <select id="roomname" name="roomname" class="form-control">
                                                    <%
                                                        for (String[] room : rooms) {
                                                    %>
                                                    <option value="<%= room[1]%>"><%= room[1]%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-6" for="emailid">Microphone</label>
                                            <div class="col-md-6">
                                                <select id="microphone" name="microphone" class="form-control">
                                                    <option value="general">General</option>
                                                    <option value="silent">Silent</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-6" for="phone">Camera</label>
                                            <div class="col-md-6">
                                                <select id="camera" name="camera" class="form-control">
                                                    <option value="enabled">Enabled</option>
                                                    <option value="disabled">Disabled</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-6" for="address">Bluetooth</label>
                                            <div class="col-md-6">
                                                <select id="bluetooth" name="bluetooth" class="form-control">
                                                    <option value="enabled">Enabled</option>
                                                    <option value="disabled">Disabled</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-6" for="address">Time Range (ex: 2:00-3:00) (24 hr Format)</label>
                                            <div class="col-md-6">
                                                <input type="text" id="time" name="time" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-6" for="latitude">Latitude</label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" name="latitude" id="latitude" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6" for="longitude">Longitude</label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" name="longitude" id="longitude" />
                                            </div>
                                        </div>
                                        <div class="form-group">

                                            <div class="col-md-offset-6 col-md-6">
                                                <input type="submit" class="btn btn-primary" value="Add" />
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-6">
                                    <div id="map-canvas"></div>
                                </div>
                            </div>
                            <table class="table table-hover">
                                <th>
                                    ID
                                </th>
                                <th>
                                    USER NAME
                                </th>
                                <th>
                                    ROOM NAME
                                </th>
                                <th>
                                    MICROPHONE
                                </th>
                                <th>
                                    CAMERA
                                </th>
                                <th>
                                    BLUETOOTH
                                </th>
                                <th>
                                    TIME
                                </th>
                                <th></th>
                                <th></th>
                                <%
                                    DBUtils.connect();

                                    List<String[]> permissions = DBUtils.getPermissions();

                                    for (String[] permission : permissions) {
                                %>
                                <tr>
                                    <% for (String permission1 : permission) {

                                    %>
                                    <td><%= permission1%></td>
                                    <%
                                        }
                                    %>
                                    <td>
                                        <a href="editpermissions.jsp?id=<%= permission[0] %>" class="btn btn-primary">edit</a>
                                    </td>
                                    <td>
                                        <a href="deletepermission.jsp?id=<%= permission[0] %>" class="btn btn-danger">delete</a>
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
