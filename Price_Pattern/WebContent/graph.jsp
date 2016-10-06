<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>graph</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

  <script src="js/jquery-1.7.2.min.js"></script> 
  <script src="js/bootstrap.js"></script> 
</head>

<body>
 <h2>price pattern graph</h2>
 
 <div id="chart"></div>
 
 <script>
 setTimeout(function () {
	    chart = c3.generate({
	        data: {
	        	url: '/Price_Pattern/json',
	            mimeType: 'json',
	            keys: {
	                x: 'Date', // it's possible to specify 'x' when category axis
	                value: ['PRC', 'Pseudo_PRC'],
	            }
	        },
	        axis: {
	            x: {
	                type: 'timeseries',
	                tick: {format: '%Y-%m-%d'}
	            }
	        }
	    });
	}, 1000);
 </script>
 
</body>

</html>