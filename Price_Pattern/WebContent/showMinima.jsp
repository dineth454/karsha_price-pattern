<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
	rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">

<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Load c3.css -->
<link href="css/c3.css" rel="stylesheet" type="text/css">

<!-- Load d3.js and c3.js -->
<script src="js/d3.min.js" charset="utf-8"></script>
<script src="js/c3.js"></script>

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/predict.js"></script>
</head>

<body>

<div class="container"></div>
<button id="createButtons">CreateElements</button>


<!--  <div class="container">
<button id="createButtons">CreateElements</button>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
				<div class="panel-body">
				<div id="chart"></div>
				<!-- Graph Representation 
				</div>
				</div>
			</div>
		</div>
	</div>-->
	
<script>

//onclick function
$("#createButtons").click(function () { 
	
	//read json url and assign it to a data string
	 $.getJSON('/Price_Pattern/getDetails/CompanyMinimaController/38703', function(data) {
		     var jsonArr = data;
		     
		     //generate maxima chart divs  
		     for(var i = 0; i < data.length; i++){
		    	 $(".container").append('<div class="row"><div class="col-md-6"><div class="panel panel-default"><div class="panel-body"><div id="chart'+ i +'"></div></div></div></div></div>');
		     }

					
		     //pass json data to the c3 graph
					for (var i = 0; i < data.length; i++) {
						var divId = "#chart" + i.toString();
						//console.log(identifier);
						chart = c3.generate({
							bindto : divId,
							data : {
								json : jsonArr[i],
								keys : {
									x : 'Date', // it's possible to specify 'x' when category axis
									value : [ 'PRC', 'Pseudo_PRC' ],
								}
							},
							zoom : {
								enabled : true
							},
							axis : {
								x : {
									type : 'timeseries',
									tick : {
										format : '%Y-%m-%d'
									}
								}
							},
							size : {
								width : 500,
								height : 300
							}
						});
					}
				});
	 });
</script>

</body>
</html>