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
    	        	tick: {
    	                format: d3.format("$,")
//    	                format: function (d) { return "$" + d; }
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
            }
        });
    }, 500);