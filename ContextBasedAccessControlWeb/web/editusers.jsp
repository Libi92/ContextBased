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
                            
                            <%
                                DBUtils.connect();
                                String[] user = DBUtils.getUserbyId(request.getParameter("id"));
                            %>
                            
                            <form method="post" action="UpdateUser" class="form-horizontal">
                                <input type="hidden" value="<%= user[0] %>" />
                                <div class="form-group">
                                    <label class="col-md-3" for="usertype">User Type</label>
                                    <div class="col-md-3">
                                        <input type="text" id="usertype" name="usertype" class="form-control" value="<%= user[6] %>" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="username">Username</label>
                                    <div class="col-md-3">
                                        <input type="text" id="username" name="username" class="form-control" value="<%= user[1] %>" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="emailid">Email ID</label>
                                    <div class="col-md-3">
                                        <input type="text" id="emailid" name="emailid" class="form-control" value="<%= user[3] %>" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="phone">Phone</label>
                                    <div class="col-md-3">
                                        <input type="text" id="phone" name="phone" class="form-control" value="<%= user[4] %>" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3" for="address">Address</label>
                                    <div class="col-md-3">
                                        <textarea id="address" name="address" class="form-control"><%= user[5] %></textarea>
                                        
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    
                                    <div class="col-md-offset-3 col-md-3">
                                        <input type="submit" class="btn btn-primary" value="Update" />
                                    </div>
                                </div>
                            </form>
                            
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


