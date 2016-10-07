<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


  <title>Stock Price Graph</title>
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
   <script src="js/predict.js"></script> 

<link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

  <script src="js/jquery-1.7.2.min.js"></script> 
    <script src="js/predict.js"></script> 
  
</head>

<body>
 
 
 <div id="chart"></div>
 
 <script>
 
 setTimeout(function () {
        chart = c3.generate({
            data: {
                url: '/Price_Pattern/pricecontroller?Key='+permno,	
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