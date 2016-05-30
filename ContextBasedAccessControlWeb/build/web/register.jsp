<%@page import="accesscontrol.db.DBUtils"%>
<%@page import="java.util.List"%>
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


    <!--- features--->
    <div class="container">
        <div class="row">
            <div class="content-feature-grids" id="features">
                
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Register Employee</h3>
                        </div>
                        <div class="panel-body">
                            <form method="post" action="RegisterServlet" enctype="multipart/form-data" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-3" for="usertype">User Type</label>
                                    <div class="col-md-3">
                                        <select id="usertype" name="usertype" class="form-control">
                                            <option value="manager">Manager</option>
                                            <option value="teamleader">Team Leader</option>
                                            <option value="employee">Employee</option>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="username">Username</label>
                                    <div class="col-md-3">
                                        <input type="text" id="username" name="username" class="form-control" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="password">Password</label>
                                    <div class="col-md-3">
                                        <input type="password" id="password" name="password" class="form-control" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="emailid">Email ID</label>
                                    <div class="col-md-3">
                                        <input type="text" id="emailid" name="emailid" class="form-control" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="phone">Phone</label>
                                    <div class="col-md-3">
                                        <input type="text" id="phone" name="phone" class="form-control" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="address">Address</label>
                                    <div class="col-md-3">
                                        <textarea id="address" name="address" class="form-control"></textarea>
                                        
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="photo">Photo</label>
                                    <div class="col-md-3">
                                        <input type="file" id="photo" name="photo" class="form-control" />
                                        
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    
                                    <div class="col-md-offset-3 col-md-3">
                                        <input type="submit" class="btn btn-primary" value="Register" />
                                    </div>
                                </div>
                            </form>
                            <table class="table table-hover">
                                <th>User Type</th>
                                <th>Username</th>
                                <th>Email ID</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th></th>
                                <th></th>
                                <%
                                    DBUtils.connect();
                                    List<String[]> allUsers = DBUtils.getAllUsers();
                                    
                                    for(String[] user : allUsers){
                                %>
                                
                                <tr>
                                    <td><%= user[6] %></td>
                                    <td><%= user[1] %></td>
                                    <td><%= user[3] %></td>
                                    <td><%= user[4] %></td>
                                    <td><%= user[5] %></td>
                                    <td>
                                        <a href="editusers.jsp?id=<%= user[0] %>" class="btn btn-primary">edit</a>
                                    </td>
                                    <td>
                                        <a href="deleteUser.jsp?id=<%= user[0] %>" class="btn btn-danger">delete</a>
                                    </td>
                                </tr>
                                
                                <%
                                    }
                                %>
                            </table>
                        </div>
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


