var app = new Vue({
	el: '#app',
	data: {
		address: $('#address').val(),
		destination: $('#destination').val(),
		distance: null,
		distanceText: null,
		amount: null
	},
	methods: {
		getDistance(){
			console.log(this.address)
			console.log(this.destination)

			service = new google.maps.DistanceMatrixService()
			service.getDistanceMatrix(
				{
					origins: [this.address],
					destinations: [this.destination],
					travelMode: 'DRIVING'
				},

				this.callback
			)
		},

		callback(response, status) {
		  if (status == 'OK') {
		    var origins = response.originAddresses
		    var destinations = response.destinationAddresses

		    for (var i = 0; i < origins.length; i++) {
		      var results = response.rows[i].elements
		      for (var j = 0; j < results.length; j++) {
		        var element = results[j]
		        this.distanceText = element.distance.text
		        this.distance = element.distance.value
		        this.amount = this.distance * 2.75
		      }
		    }
		  }
		}
	},
	mounted(){
		this.getDistance()
	}
})
