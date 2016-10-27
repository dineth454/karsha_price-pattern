
/**
 * 
 * This is where AmNeg Data is created to visualize the chart in main page.
 */

$('#amNeg').on('click', doThis);
function doThis () {    
 var c;
 var arr;
 $.ajax({
	    url:  '/Price_Pattern/getDetails/amNegLogic/'+ permno,
	    type: "GET",
	    dataType: 'html',
	    async: false,
         cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });
 

arr = JSON.parse( arr); // Do not need to pass to a another array; 
document.getElementById("count2").innerHTML = "AmNeg  Count  "; 
document.getElementById("badges2").innerHTML = arr.length;
if(arr.length>0){
for(c=0;c<arr.length;c++){
	  $('#parent2').append('<div class="col-md-4"><div class="page-header" align="center">AmNeg-'+(c+1)+'</br><div align="center">(pattern -'+arr[c][0]['pattern']+')</div></div><div id="demot'+ c +'"></div></div>');
	  }


console.log(arr.length);
var i;
 for(i=0;i<arr.length;i++){
var chart=c3.generate({
	bindto: document.getElementById('demot'+i),
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
	        	 tick: {
	                 count: 3,
	                 format: '%Y-%m-%d'
	             },
				    label: {
				        text: 'Dates',
				        position: 'Right',
				     }
	        },
	        y: {
	        	tick: {
	                format: d3.format("$,")
//	                format: function (d) { return "$" + d; }
	            },
	            label: {
	                text: 'PRC/PsedoPRC in $',
	                position: 'outer-middle'
	                // inner-top : default
	                // inner-middle
	                // inner-bottom
	                // outer-top
	                // outer-middle
	                // outer-bottom
	            }
	        },
	    },
	    size:{
	    	 width:300,
	    	 height:275
	    },
	    zoom: {
	    	enabled: true,
	  		rescale: true
			},
			
	    bar: {
	        width: {
	            ratio: 0.5
	        }
	    }
	    
	});
 }
}
else{
	//document.write("No Maxima Found")
	alert(" No Data Found");
}
$('#minima').off('click',doThis);
 }
