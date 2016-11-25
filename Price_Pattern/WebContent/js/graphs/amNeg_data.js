
/**
 * 
 * This is where AmNeg Data is created to visualize the chart in main page.
 */

//$('#amNeg').on('click', doThisn);
//function doThisn () {    
 var c;
 var arr;
 var amNegarr;
 $.ajax({
	    url:  '/Price_Pattern/getDetails/amNegLogic/'+ permno, //this is the url where json format is generate for graph visualization 
	    type: "GET",
	    dataType: 'html',
	    async: false,
         cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });
 

arr = JSON.parse( arr); // Do not need to pass to a another array; 
amNegarr=arr;

document.getElementById("count2").innerHTML = "AmNeg  Count  "; 
document.getElementById("badges2").innerHTML = arr.length;
var p1=0,p2=0,p3=0,p4=0;
if(arr.length>0){
for(c=0;c<arr.length;c++){
	  $('#parent2').append('<div class="col-md-4"><div class="page-header" align="center">AmNeg-'+(c+1)+'</br><div align="center">(pattern -'+arr[c][0]['pattern']+')</div><div id="circle" style="background-color:'+arr[c][0]['color']+';width:30px;height:30px;"></div></div><div id="demoneg'+ c +'"></div></div>');
	  	
	  switch(arr[c][0]['pattern']) {
      case 'MmaxToMmin' :
     	p1=p1+1;
         break;
      case 'MmaxToMin' :
     	p2=p2+1;
         break;
      case 'MaxToMmin' :
     	p3=p3+1;
         break;
      case 'MaxToMin' :
     	 p4=p4+1;
         break;
		 }
	  
}
/*  Show distinct pattern counts out of total AmNEg count*/
$('#p1').append(p1+'/'+arr.length);
$('#p2').append(p2+'/'+arr.length);
$('#p3').append(p3+'/'+arr.length);
$('#p4').append(p4+'/'+arr.length);


var i;
 for(i=0;i<arr.length;i++){
var chart=c3.generate({
	bindto: document.getElementById('demoneg'+i),
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
	                format: d3.format("$.2f,")
	               // format: d3.format('.2f')
//	                format: function (d) { return "$" + d; }
	            },
	            label: {
	                text: 'PRC/PsedoPRC in $',
	                position: 'outer-middle'
	               
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
	
	alert(" No Data Found"); //show alert when  data missing..
}
//$('#amNeg').off('click',doThisn);
 //}
