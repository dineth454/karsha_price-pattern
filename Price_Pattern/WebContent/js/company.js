$ ("#search").keyup(function(){
          var searchfield =$("#search").val();
            var myExp = new RegExp(searchfield,"i"); 
 					  console.log(myExp);
 				   $.getJSON('cmp.json',function(data){
 						//console.log(data);
              
 						     var tmp=''; 
                for (var i=0; i<data.RECORDS.length; i++) 
                  if((data.RECORDS[i].COMNAM.search(myExp) != -1) && ($("#search").val()!="")) {

                tmp+=data.RECORDS[i].COMNAM +'<br>'; 
                document.getElementById('suggest').innerHTML=tmp; 
              }
 						
 					});	
 					
 				});


