<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Naics Graph Visualization</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
  <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
          rel="stylesheet">
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  <link href="css/dashboard.css" rel="stylesheet">
  
  <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/bootstrap.min.js"></script>
   

  <script src="js/pace.js"></script>
  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

  <script src="js/jquery-2.1.4.min.js"></script> 
    
</head>

<style>

</style>
<body>


<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
       <a class="brand" href="index.html">Karsha - Equity Price Features </a>
      </div>
    </div>
  </div>
  
  <%
    int naicskey = Integer.parseInt(request.getParameter("naics"));
%>
<script>
var naicskey = "<%=naicskey%>";
var arr;
$.ajax({
	    url:  '/Price_Pattern/getDetails/naicsData/'+naicskey , //this is the url where json format is generate for graph visualization 
	    type: "GET",
	    dataType: 'html',
	    async: false,
        cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });


arr = JSON.parse( arr); // Do not need to pass to a another array; 
document.write(arr.length);

</script>
 
<div class="container">
<div class="row">
<div class="col-md-12">
<div id="chart"></div>

</div>

</div>
</div>
<script type="text/javascript">
var chart = c3.generate({
	bindto: document.getElementById('chart'),
    data: {
        xs: {
            Maxima: 'Dates_Maxima',
            AmNeg: 'Dates_AmNeg',
           Minima : 'Dates_Minima'
        },
        
       // json: arr,
        // iris data from R
       columns: [
			arr[0],
			arr[1],
			arr[2],
			arr[3],
			arr[4],
			arr[5]
			
			
        ],
        type: 'scatter'
    },
    axis: {
        x: {
        	type : 'timeseries',
            label: 'Time',
           tick: {
                format: '%Y-%m-%d'
            }
        },
        y: {
        	 show: false,
            label: 'Permno',
            tick: {
	      values: [1, 2, 3, 4,5,6]
	    }
        }
    },
zoom: {
  enabled: true,
  rescale: true
},
subchart: {
  show: true
},
size:{
	 width:1200,
	 height: 800
},
grid: {
        y: {
            lines: arr[6]
        }
    }
});

</script>
</body>
</html>