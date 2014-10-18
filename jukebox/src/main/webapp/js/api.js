var API_BASE_URL = "http://192.168.1.134:8080/jukebox/rest/api/";



function getticket() {
	
	var url = API_BASE_URL + 'getticket';

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			
			alert("ID de tiquet:    "+data["idticket"]+ "    Hora en que se ha registrado el tiquet: "+data["date"])
			$(".cover-heading").html(data["idticket"]);
		
		},
		error : function(jqXHR, options, error) {
			alert(jqXHR + error + options);
		}
	});
}


function check_key (key_input) {
	
	var url = API_BASE_URL + 'queque_song/111/'+ key_input;
	alert(key_input)
	
			$.ajax({
				url : url,
				type : 'GET',
				
				crossDomain : true,
				dataType : 'text',
				success : function(data, status, jqxhr) {
					
					alert(data)
				
				},
				error : function(jqXHR, options, error) {
					alert(jqXHR + error + options);
				}
			});
			

			
			
		  
			
			
			
			
		
	
}



function getSpotiSongs(track) {
	
	var url = API_BASE_URL + 'search_song/'+track;

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			var response = data;
			 $.each(response, function (index, value) {
 		
 	 	$('#songs-data > tbody').append('<tr>      <td><img src="' + value.image + '" style="width:304px;height:228px"></td>  <td><a href="#"> ' + value.Name + '</a></td><td><a href="#"> ' + value.Artist + '</a></td><td><a href="#"> ' + value.duration + '</a></td></tr>');
		
			 });
			 },
				error : function(jqXHR, options, error) {
					alert(jqXHR + error + options);
				}
			});
}









