/**
 * 
 * 
 */
/**method for get the url variable to print --*/
function getQueryVariable(variable) {
	var query = window.location.search.substring(1)
	//console.log(query);
	var vars = query.split("&");
	console.log(vars);
	for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=")
	    console.log(pair);
	    if(pair[0] == variable){
	        if(pair[1].indexOf('%20') != -1){
	            console.log(pair[1].indexOf('%20'))
	            var fullName = pair[1].split('%20')
	            console.log(fullName)
	            return fullName[0] + ' ' + fullName[1]
	        }
	        else {
	            return pair[1];
	        }
	    }
	}
	return(false)
}

var permno=getQueryVariable('Key');
var comName=getQueryVariable('company');
var Naics=getQueryVariable('naics');
var tsymbol=getQueryVariable('symbol');




