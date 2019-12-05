var app = new Vue({
	el: '#app',
	data: {
		address: $('#address').val(),
		destination: $('#destination').val(),
		distance: null,
		distanceText: null,
		amount: null,
		addressText: null,
		destinationText: null
	},
	methods: {
		getDistance(){
			console.log(this.address)
			console.log(this.destination)

			this.address = this.address.split(", ")
			origin = new google.maps.LatLng(parseFloat(this.address[0]), parseFloat(this.address[1]))

			this.destination = this.destination.split(", ")
			destination = new google.maps.LatLng(parseFloat(this.destination[0]), parseFloat(this.destination[1]))

			service = new google.maps.DistanceMatrixService()
			service.getDistanceMatrix(
				{
					origins: [origin],
					destinations: [destination],
					travelMode: 'DRIVING'
				},

				this.callback
			)
		},

		callback(response, status) {
		  if (status == 'OK') {
		    var origins = response.originAddresses
		    var destinations = response.destinationAddresses
		    this.addressText = response.originAddresses[0]
		    this.destinationText = response.destinationAddresses[0]

		   	console.log(response)

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
	mounted() {
		this.getDistance()
	}
})
