<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <link href="css/themes/green/pace-theme-center-simple.css" rel="stylesheet" />

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


  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

  <script src="js/jquery-1.7.2.min.js"></script> 
    <script src="js/predict.js"></script> 
  
</head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
       <a class="brand" href="index.html">Karsha - Price Pattern </a>
      </div>
    </div>
  </div>
 
 
    
<div class="container">
<div class="row">
			
			  <div class="col-md-12">
			 			 <div id="chart"></div>  <!-- Graph Representation -->
			 </div>
	 </div>
 </div>
 
 
 
 <script>
 
 /*var par =[ [{"PRC":29.48,"PERMNO":38703,"Date":"2008-12-31","Pseudo_PRC":70.11},{"PRC":28.8,"PERMNO":38703,"Date":"2008-12-30","Pseudo_PRC":68.5},{"PRC":27.83,"PERMNO":38703,"Date":"2008-12-29","Pseudo_PRC":66.19},{"PRC":27.51,"PERMNO":38703,"Date":"2008-12-26","Pseudo_PRC":65.43},{"PRC":27.48,"PERMNO":38703,"Date":"2008-12-24","Pseudo_PRC":65.36},{"PRC":26.99,"PERMNO":38703,"Date":"2008-12-23","Pseudo_PRC":64.19},{"PRC":27.42,"PERMNO":38703,"Date":"2008-12-22","Pseudo_PRC":65.22},{"PRC":29.36,"PERMNO":38703,"Date":"2008-12-19","Pseudo_PRC":69.83},{"PRC":29.65,"PERMNO":38703,"Date":"2008-12-18","Pseudo_PRC":70.52},{"PRC":30,"PERMNO":38703,"Date":"2009-01-02","Pseudo_PRC":71.35},{"PRC":28.06,"PERMNO":38703,"Date":"2009-01-05","Pseudo_PRC":66.74},{"PRC":27.54,"PERMNO":38703,"Date":"2009-01-06","Pseudo_PRC":65.5},{"PRC":25.87,"PERMNO":38703,"Date":"2009-01-07","Pseudo_PRC":61.53},{"PRC":25.72,"PERMNO":38703,"Date":"2009-01-08","Pseudo_PRC":61.17},{"PRC":25.14,"PERMNO":38703,"Date":"2009-01-09","Pseudo_PRC":59.79},{"PRC":23.8,"PERMNO":38703,"Date":"2009-01-12","Pseudo_PRC":56.61},{"PRC":24.38,"PERMNO":38703,"Date":"2009-01-13","Pseudo_PRC":57.98},{"PRC":23.07,"PERMNO":38703,"Date":"2009-01-14","Pseudo_PRC":54.87},{"PRC":20.16,"PERMNO":38703,"Date":"2009-01-15","Pseudo_PRC":47.95},{"PRC":18.68,"PERMNO":38703,"Date":"2009-01-16","Pseudo_PRC":44.43}],
            [{"PRC":31.89,"PERMNO":38703,"Date":"2011-01-13","Pseudo_PRC":78.3},{"PRC":32.01,"PERMNO":38703,"Date":"2011-01-12","Pseudo_PRC":78.59},{"PRC":31.4,"PERMNO":38703,"Date":"2011-01-11","Pseudo_PRC":77.09},{"PRC":31.2,"PERMNO":38703,"Date":"2011-01-10","Pseudo_PRC":76.6},{"PRC":31.5,"PERMNO":38703,"Date":"2011-01-07","Pseudo_PRC":77.34},{"PRC":32.15,"PERMNO":38703,"Date":"2011-01-06","Pseudo_PRC":78.93},{"PRC":32.37,"PERMNO":38703,"Date":"2011-01-05","Pseudo_PRC":79.47},{"PRC":31.65,"PERMNO":38703,"Date":"2011-01-04","Pseudo_PRC":77.71},{"PRC":31.58,"PERMNO":38703,"Date":"2011-01-03","Pseudo_PRC":77.53},{"PRC":32.75,"PERMNO":38703,"Date":"2011-01-14","Pseudo_PRC":80.41},{"PRC":32.49,"PERMNO":38703,"Date":"2011-01-18","Pseudo_PRC":79.77},{"PRC":31.81,"PERMNO":38703,"Date":"2011-01-19","Pseudo_PRC":78.1},{"PRC":31.89,"PERMNO":38703,"Date":"2011-01-20","Pseudo_PRC":78.3},{"PRC":32.51,"PERMNO":38703,"Date":"2011-01-21","Pseudo_PRC":79.82},{"PRC":32.7,"PERMNO":38703,"Date":"2011-01-24","Pseudo_PRC":80.28},{"PRC":32.7,"PERMNO":38703,"Date":"2011-01-25","Pseudo_PRC":80.28},{"PRC":32.45,"PERMNO":38703,"Date":"2011-01-26","Pseudo_PRC":79.67},{"PRC":32.5,"PERMNO":38703,"Date":"2011-01-27","Pseudo_PRC":79.79},{"PRC":31.84,"PERMNO":38703,"Date":"2011-01-28","Pseudo_PRC":78.17},{"PRC":32.42,"PERMNO":38703,"Date":"2011-01-31","Pseudo_PRC":79.6}]];
 
 
 var chart = c3.generate({
	    data: {                
	        
	        json: par[1],
	        keys: {
	            x: 'Date',
	            value: ['PRC']
	        }
	    },
	    size: {
	        width: 800,
	    },
	    axis: {
	        x: {
	        	 type: 'timeseries',
                 tick: {format: '%Y-%m-%d'}
	        }
	    },
	    bar: {
	        width: {
	            ratio: 0.5
	        }
	    }
	});
 */
 
 var arr;
 $.ajax({
	    url:  '/Price_Pattern/getDetails/maximaLogic/'+ 38703,
	    type: "GET",
	    dataType: 'html',
	    //contentType: "application/json; charset=utf-8",
	    async: false,
         cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });
 
 //arr = arr.replace(/'/g, '"');
 arr = JSON.parse(arr);
// var array = JSON.parse( arr);
 document.write(arr);
 /*var chart = c3.generate({
	    data: {                
	        
	        json: arr[0],
	        keys: {
	            x: 'Date',
	            value: ['PRC']
	        }
	    },
	    size: {
	        width: 800,
	    },
	    axis: {
	        x: {
	        	 type: 'timeseries',
              tick: {format: '%Y-%m-%d'}
	        }
	    },
	    bar: {
	        width: {
	            ratio: 0.5
	        }
	    }
	});
 /*setTimeout(function () {
        chart = c3.generate({
        
            data: {
                url: '/Price_Pattern/getDetails/CompanyPriceController/'+ 38703,
            

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
    }, 500); */
 </script>
  <script src="js/pace.js"></script>
</body>

</html>