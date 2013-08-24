function getVerses(request) {

	console.log(request);
	console.log("getting verses called...");

	var suratId = request.suratId;
	var verseId = request.verseId;
	var verseCount = request.verseCount;

	// Doing a request for a certin verse count
	if (suratId != null && verseId != null && verseCount != null) {

		console.log("getting x verses from Surat");
		$.ajax({
			type : "GET",
			contentType : "application/json",
			dataType : "jsonp",
			crossDomain : true,
			url : "http://localhost:8888/v1/surat/" + suratId + "/" + verseId + "/" + verseCount
			
		}).done(function(data) {
			
			console.log(data);
			//return data;
			// TODO

		}).fail(function(data) {

			console.log(data);
			//return data;
			// TODO
		}); 
		
		// Doing a request for all verses for a certin Surat
	} else if (suratId != null && verseId != null) {

		console.log("getting all verses for a Surat");
		
		// Doing a request for a certin verse
	} else if (suratId != null) {

		console.log("getting a certin verse");
		
	} else {

		// Handle invalid responce
		console.log("Not a valid request");
		
	}

}
