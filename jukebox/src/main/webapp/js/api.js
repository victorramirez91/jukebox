var API_BASE_URL = "http://localhost:8080/jukebox/rest/api/";



function getticket() {
	
	var url = API_BASE_URL + 'getticket';

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			//alert("i'm in");
			//alert(data["id"]);
			alert("ID de tiquet:    "+data["idticket"]+ "    Hora en que se ha registrado el tiquet: "+data["date"])
			$(".cover-heading").html(data["idticket"]);
		
		},
		error : function(jqXHR, options, error) {
			alert(jqXHR + error + options);
		}
	});
}




