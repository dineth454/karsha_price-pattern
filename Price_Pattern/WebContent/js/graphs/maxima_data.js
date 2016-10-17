/**
 * 
 * This is where graph data is found and visualize graph in web page
 */


 $('#add').on('click', function() {	 
 var c;

 

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
 

arr = JSON.parse( arr); // Do not need to pass to a array; 
for(c=0;c<arr.length;c++){
	  $('#parent').append('<div class="col-md-4"><div id="demo'+ c +'"></div></div>');
	  }

//document.write(arr);
//console.log(arr.length);
var i;
 for(i=0;i<arr.length;i++){
var chart=c3.generate({
	bindto: document.getElementById('demo'+i),
	    data: {                
	        
	        json: arr[i],
	   
	     hide: ['Date'],
	        keys: {
	            x: 'Date',
	            value: ['PRC','Pseudo_PRC']
	        }
	    },
	    
	    axis: {
	        x: {
	        	 type: 'timeseries',
              	tick: {format: '%Y-%m-%d'},
				    label: {
				        text: 'Dates',
				        position: 'Right',
				     }
	        }
	    },
	    grid: {
	        x: {
	            lines: [
	                {value: arr[i][9]['Date'], text: 'Maxima',position: 'middle'}
	               
	            ]
	        }
	    },
	    size:{
	    	 width:300
	    },
	    zoom: {
	    	enabled: true,
	  		rescale: true
			},
			regions: [
			          {axis: 'x', start:  arr[i][19]['leftDate'], end:  arr[i][19]['rightDate'], class: 'regionX'},
			         
			          
			      ],
	    bar: {
	        width: {
	            ratio: 0.5
	        }
	    }
	    
	});
 }
 });
