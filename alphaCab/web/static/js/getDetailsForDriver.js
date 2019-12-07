var app = new Vue({
	el: '#app',
	data: {
		destination: $('#destination').val(),
		destinationText: $('#destination').val()
	},
	methods: {
		getDetails(){
			var destination = this.destination.split(', ')

			if(destination.length === 2){
				// Create dummy origin since places cant be queried using LatLng
				var origin = new google.maps.LatLng(4.175555, 73.509125)
				destination = new google.maps.LatLng(parseFloat(destination[0]), parseFloat(destination[1]))
				// console.log(destination)
				console.log(isNaN(destination.lat()))
				console.log(isNaN(destination.lng()))
				if(!(isNaN(destination.lat()) || isNaN(destination.lng()))){
					console.log("Im hitting this")
					service = new google.maps.DistanceMatrixService()
					service.getDistanceMatrix(
						{
							origins: [origin],
							destinations: [destination],
							travelMode: 'DRIVING'
						},

						this.callback
					) 
				}
			}
		},

		callback(response, status) {
		  if (status == 'OK') {
		    // var origins = response.originAddresses
		    var destinations = response.destinationAddresses
		    // this.addressText = response.originAddresses[0]
		    this.destinationText = response.destinationAddresses[0]

		   	console.log(response)

		    // for (var i = 0; i < destinations.length; i++) {
		    //   var results = response.rows[i].elements
		    //   for (var j = 0; j < results.length; j++) {
		    //     var element = results[j]
		    //     this.distanceText = element.distance.text
		    //     this.distance = element.distance.value
		    //     // this.amount = this.distance * 2.75
		    //   }
		    // }
		  }
		}
	},
	mounted(){
		this.getDetails()
	}
});
