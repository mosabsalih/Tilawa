function getVerses(request) {

	console.log(request);
	console.log("getting verses");

	var suratId = request.suratId;
	var verseId = request.verseId;
	var verseCount = request.verseCount;

	// Doing a request for a certin verse count
	if (suratId != null && verseId != null && verseCount != null) {

		console.log("getting x verses from Surat");
		$.ajax({
			type : "GET",
			accept : {json:"application/json"},
			contentType : "application/json",
			dataType : "json",
			crossDomain : true,
			url : "http://localhost:8888/v1/verse/" + suratId + "/" + verseId + "/" + verseCount
		}).done(function(data) {
			
			console.log("Y: " + data);
			return data;
			// TODO

		}).fail(function(data) {

			console.log("F: " + data);
			return data;
			// TODO
		});

		// Doing a request for all verses for a certin Surat
	} else if (suratId != null && verseId != null) {

		console.log("getting all verses for a Surat");
		
		$.getJSON("http://localhost:8888/v1/verse/" + suratId + "/" + verseId + "?callback=?", function(data) {
			
			return data;
		});

		// Doing a request for a certin verse
	} else if (suratId != null) {

		console.log("getting a certin verse");
		// Not a valid request
	} else {

		console.log("Not a valid request");
		//TODO
	}

}
