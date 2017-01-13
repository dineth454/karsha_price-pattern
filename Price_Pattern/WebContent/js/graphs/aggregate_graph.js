function daily_aggrigation(ss) {
console.log(ss);
	var chart = c3.generate({
		 bindto: '#matrix',
		data : {
			url : '/Price_Pattern/getDetails/aggregateController/'+ss,
			mimeType : 'json',
			x : 'x',
			type : 'bar',
			keys : {
				x : 'date', // it's possible to specify 'x' when category axis
				value : [ 'peak_gain', 'peak_loss' ,'diff_gain', 'diff_loss','max_count','min_count' ],
			},
			groups : [ [ 'peak_gain', 'peak_loss' ,'diff_gain', 'diff_loss' ,'max_count','min_count'  ] ],
			colors: {
				peak_gain: '#00ff00',
				peak_loss: '#ff0f0f',
				diff_gain: ' #0000ff',
				diff_loss: ' #006666'
	        },

		},

		zoom : {
			enabled : true,
			rescale : true
		},
		axis : {
			x : {
				type : 'timeseries',
				label : {
					text : 'date',
					position : 'outer-end'
				},
				tick : {
					format : '%Y-%m-%d'
				}
			},

			y : {
				label : {
					text : 'Gain / Loss $',
					position : 'outer-top'
				},
				tick: {
	                format: d3.format("$s")
	            }
			},
			
			  y2: {
			  
				  show: true,
				  center: 0,
				  max: 200,
			      min: -200,
			     padding: {top: 0, bottom: 0},
                  label:'Countxs'
              }
		},
		tooltip : {
			format : {
				value: function (value, ratio, id) {
		        	   var formatMeA = id === 'peak_gain' || id==='peak_loss' || id==='diff_gain' || id==='diff_loss' ? value = (value/1000000000).toFixed(2)+" B$" : value;
		        	   return value;
		           }
			}

		},
	});
}