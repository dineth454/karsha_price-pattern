/**
 * 
 */

var searchfield = location.search.split('com=')[1]; //get the company name from url // this is wrong if space that not valid 
					document.write(searchfield);
					console.log(searchfield);
				
					if(searchfield){
						if(searchfield.length>1){
					  var myExp = new RegExp(searchfield,"i"); 
					  
 					  //console.log(myExp);
 				   $.getJSON('cmp.json',function(data){
 						//console.log(data);
              
 					  
 					   
 					   	var tmp=''; 
                for (var i=0; i<data.RECORDS.length; i++) 
                  if(data.RECORDS[i].COMNAM.search(myExp) != -1) {
                	  	var eachRecord =data.RECORDS[i].COMNAM;
                	  	
                	  	var permno=data.RECORDS[i].PERMNO;
                	  	var tsym=data.RECORDS[i].TSYMBOL;
                	  	var naics=data.RECORDS[i].NAICS;
                	  	
                	  	tmp += '<a  href="page2.html?Key='+permno+'&symbol='+tsym+'&naics='+naics+'" >'+eachRecord+'</a><br>'; //get the each permno and symbol
                	  	console.log(tmp);
                	  	document.getElementById('suggest').innerHTML=tmp + "test";
                  }
 				   
 				  });	
					}
					}
				