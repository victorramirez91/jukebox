	var IP = "http://localhost:8080/jukebox"
var API_BASE_URL = IP+"/rest/api/";


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


function check_key (idpet, key_input) {
	
	var url = API_BASE_URL + 'queque_song/'+idpet+'/'+ key_input;
	alert(key_input)
	
			$.ajax({
				url : url,
				type : 'GET',
				
				crossDomain : true,
				dataType : 'text',
				success : function(data, status, jqxhr) {
					
					alert(data)
					$('#songs-data ').empty();
					$('#key_input').hide();
					$('#key_button').hide();
					$('#back_button').show();
					$('#key_label').hide();
					$("#response").html(data);

					
				},
				error : function(jqXHR, options, error) {
					alert(jqXHR + error + options);
				}
			});
			

			
		  
			
			
			
			
		
	
}



function getSpotiSongs(track) {
	
	//var url = API_BASE_URL + 'search_song/'+track;
	var url = API_BASE_URL + 'search_song/'+track;

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			var response = data;
			$('#prova2').show();
			 $.each(response, function (index, value) {
 		
 	 	$('#songs-data').append("<a href='Insertkey.html?id="+value.id+"' ><div class='cancion' id = '"+value.id+"' > <div class='contenedorImagen'>"+
    				"<img src="+IP	+"/images/"+ value.image +" class='imagenCancion' alt=''>"+
    			"</div>"+
    			"<div class='contenedorTexto'>"+
					"<div class='contenedorCancion'>"+ value.Name + "</div>"+
					"<div class='contenedorAlbum'>"  + value.Artist +"</div>"+
					"<div class='contenedortiempo'>"+ value.duration +"</div>"+

				"</div>"+
				"<div class='clear'></div>"+
    		"</div></a>");
 	 
			 });
			 },
				error : function(jqXHR, options, error) {
					alert(jqXHR + error + options);
				}
			});
}


function getSpotiSong(track) {
	
	var url = API_BASE_URL + 'get_song/'+track;
	

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			var response = data;
			
			
			 
 		
 	 	$('#songs-data').append("<a href='#' ><div class='cancion' id = "+data.id+" > <div class='contenedorImagen'>"+
    				"<img src="+IP	+"/images/"+ data.image +" ' class='imagenCancion' alt=''>"+
    			"</div>"+
    			"<div class='contenedorTexto'>"+
					"<div class='contenedorCancion'>"+ data.Name + "</div>"+
					"<div class='contenedorAlbum'>"  + data.Artist +"</div>"+
					"<div class='contenedortiempo'>"+ data.duration +"</div>"+

				"</div>"+
				"<div class='clear'></div>"+
    		"</div></a>");
 	 
			 
			 },
				error : function(jqXHR, options, error) {
					alert(jqXHR + error + options);
				}
			});
}







