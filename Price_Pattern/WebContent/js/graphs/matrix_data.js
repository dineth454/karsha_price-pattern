/**
 * 
 */




var maxDates=["Dates_Maxima"];
var minDates=["Dates_Minima"];
var amNegDates=["Dates_AmNeg"];
var maxValues=["Maxima"];
var minValues=["Minima"];
var amValues=["AmNeg"];

for(var i = 0;i<maxarr.length;i++){
	
	maxDates.push((maxarr[i][maxarr[i].length-1]['maximaDate']));
	maxValues.push(3);
	
}

for(var i = 0;i<minarr.length;i++){
	
	minDates.push((minarr[i][minarr[i].length-1]['minimaDate']));
	minValues.push(1);
}

for(var i = 0;i<amNegarr.length;i++){
	
	var startTime = new Date(amNegarr[i][0]['Date']);
	var endTime = new Date(amNegarr[i][amNegarr[i].length-1]['Date']);
	
    
    var middleTime = new Date((startTime.getTime() + endTime.getTime()) / 2);
    //console.log(middleTime);
    
    var currentDate = new Date(middleTime);
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();
    var middleTimew=year + "-" + month + "-" + day;
    
    amNegDates.push(middleTimew);
    amValues.push(2);
}




var chart = c3.generate({
	bindto: document.getElementById('matrix'),
    data: {
        xs: {
            Maxima: 'Dates_Maxima',
            AmNeg: 'Dates_AmNeg',
           Minima : 'Dates_Minima',
        },
        
       // json: arr,
        // iris data from R
       columns: [
			maxDates,
			minDates,
			amNegDates,
			maxValues,
			minValues,
			amValues
			
			
			
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
	      values: [0,1, 2, 3]
	    }
        }
    },
zoom: {
  enabled: true,
  rescale: true
},

size:{
	 width:1000,
	 height: 200
},
tooltip: {
	   show: 'dataToHide'
	},
	
	
grid: {
        y: {
            lines: [
					{value: 0.8, text: 'Minima'},
					{value: 1.8, text: 'AmNeg'},
					{value: 2.8, text: 'Maxima'}
                    ]

        }
    }
});



