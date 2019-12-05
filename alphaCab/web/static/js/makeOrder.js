var addressMap;
var destinationMap;
var addressMarker = null;
var destinationMarker = null;

function mapRender(){
	addressMap = new google.maps.Map(document.getElementById('addressMap'), {
		center: {lat: 4.175835, lng: 73.509354},
		zoom: 17
	})

	destinationMap = new google.maps.Map(document.getElementById('destinationMap'), {
		center: {lat: 4.175835, lng: 73.509354},
		zoom: 17
	})

	addressMap.addListener('click', function(e){
		console.log(e.latLng);
		placeMarker(e.latLng, 'addressMarker', addressMap)
		console.log(window.addressMarker.getPosition().lat())
		$('#address').val(window.addressMarker.getPosition().lat() + ", " + window.addressMarker.getPosition().lng());
	});

	addressMap.addListener('rightclick', function(e){
		removeMarker('addressMarker');
		$('#address').val(null);
	});

	destinationMap.addListener('click', function(e){
		placeMarker(e.latLng, 'destinationMarker', destinationMap)
		console.log(window.destinationMarker.getPosition().lat());
		$('#destination').val(window.destinationMarker.getPosition().lat() + ", " + window.destinationMarker.getPosition().lng());
	});

	destinationMap.addListener('rightclick', function(e){
		removeMarker('destinationMarker');
		$('#destination').val(null);
	});

	$('#addressSearchButton').click(function(event){
		event.preventDefault();
		searchPlace($('#addressSearch'), window.addressMap);
	});

	$('#destinationSearchButton').click(function(event){
		event.preventDefault();
		searchPlace($('#destinationSearch'), window.destinationMap);
	})
}

function placeMarker(position, marker, map){
	if(window[marker] == null){
		window[marker] = new google.maps.Marker({
	        position: position,
	        map: map
	    });
	}
}

function removeMarker(marker){
	if(window[marker] != null){
		window[marker].setMap(null);
		window[marker] = null;
	}
}

function searchPlace(element, map) {
	var request = {
		query: element.val(),
		fields: ['name', 'geometry']
	};

	service = new google.maps.places.PlacesService(map);
	service.findPlaceFromQuery(request, function(results, status) {
		if(status === google.maps.places.PlacesServiceStatus.OK) {
			map.setCenter(results[0].geometry.location);
		}
	});
}
