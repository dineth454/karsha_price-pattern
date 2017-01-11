/**
 * 
 */

setTimeout(function () {
        chart = c3.generate({
            data: {
                url: '/Price_Pattern/getDetails/CompanyPriceController/'+ permno,	
                mimeType: 'json',
                keys: {
                    x: 'Date', // it's possible to specify 'x' when category axis
                    value: ['PRC', 'Pseudo_PRC','Turnover'],
                },
                axes: {
                	Turnover: 'y2'
                }
            },
            zoom: {
    	    	enabled: true,
    	  		rescale: true
    			},
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {format: '%Y-%m-%d'}
                },
                y: {
    	        	//tick: {
    	              //  format: d3.format("$,")
//    	                format: function (d) { return "$" + d; }
    	            //},
    	            label: {
    	                text: 'PRC/PsedoPRC in $',
    	                position: 'outer-middle'
    	            }
    	        },
    	        y2: {
    	        	show: true,
    	        	label: 'Turnover'
    	        }
            },
            tooltip: {
                format: {
                    value: function (value, ratio, id) {
                        var format = id === 'Turnover' ? d3.format(',.4f') : d3.format('$.4f');
                        return format(value);
                    }
                }
            }
        });
    }, 500);