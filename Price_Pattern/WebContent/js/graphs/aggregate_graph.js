function daily_aggrigation() {

	var chart = c3.generate({
		data : {
			url : '/Price_Pattern/getDetails/aggregateController/',
			mimeType : 'json',
			x : 'x',
			type : 'bar',
			keys : {
				x : 'date', // it's possible to specify 'x' when category axis
				value : [ 'peak_gain', 'peak_loss' ],
			},
			groups : [ [ 'peak_gain', 'peak_loss' ] ],
			colors: {
				peak_gain: '#00ff00',
				peak_loss: '#ff0f0f'
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
					text : 'dates',
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
				}				
			}
		},
		tooltip : {
			format : {
				value : function(value, ratio, id) {
					var format = id === '' ? d3.format(',') : d3.format('$.4f');
					return format(value);
				}
			}
		},
	});
}