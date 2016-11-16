<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <title>Price Pattern</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
  <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/dashboard.css" rel="stylesheet">
  
    <!-- CSS files for D3 Tree -->
  <link href="css/customStyle.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  
  <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/bootstrap.min.js"></script> 

  <style type="text/css">
	.logo {
	    width: 250px;
	    height: 150px;
	    border: 3px solid #73AD21; 
	}
	
	.main{
	height: auto;
	
	}
	
#suggest a:hover{
    text-decoration: none;
}
  </style> 
</head>
<body>
   <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container top">
       <a class="brand" href="index.html">Karsha - Equity Price Features </a>
      </div>
    </div>
  </div>
  <div class="subnavbar">
    <div class="subnavbar-inner navbar">
      <div class="container">
       <ul class="mainnav">
          <li> <a href="index.html"><i class="icon-home"></i><span>Home</span> </a> </li>
          <li><a href="search.html"><i class="icon-dashboard"></i><span>Search</span> </a> </li>
          
          <li><a href="help.html"><i class="icon-user"></i><span>help</span> </a></li>
            <li><a href="#"><i class="icon-cogs  "></i><span>About</span> </a></li>
        </ul>
      </div>
    </div>
  </div>


<div class="container">
  <h2>NAICS Companies</h2>
  <div class="list-group">
	  <a href="#" class="list-group-item active">
	    Naics Sectors 
	  </a>

			<%@ page import = "java.sql.*" %>
			<% Class.forName("com.mysql.jdbc.Driver"); %>
			<%
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/karshacep", "root", ""); // <== Check!
				// Connection conn =
				//    DriverManager.getConnection("jdbc:odbc:eshopODBC");  // Access
				Statement stmt = conn.createStatement();

				String sqlStr = "SELECT * FROM naics";
				
				try{
					System.out.println("Query statement is " + sqlStr);
					ResultSet rset = stmt.executeQuery(sqlStr);
				}catch(SQLException e){
					System.out.println("errrrror" + e);
				}
				// for debugging
				
			%>

			<a href="#" class="list-group-item list-group-item-action">11 | Agriculture, Forestry, Fishing and Hunting<span class="badge">12</span></a>
	  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
	  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
	  <a href="#" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
  </div>
</div>

<div class="footer">
  <div class="footer-inner">
    <div class="container">
      <div class="row">
        <div class="span12"> &copy; 2016 <a href="#">
          </a></div>
        <!-- /span12 --> 
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /footer-inner --> 
</div>
<!-- /footer --> 
</body>
</html>