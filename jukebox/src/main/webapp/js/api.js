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
 		
 	 		$('#songs-data').append("<a href='Insertkey.html?id="+value.id+"' ><div class='cancion' id = "+value.id+" > <div class='imagen'>"+
    				"<img src="+ value.image +" ' class='img-responsive' alt=''>"+
    			"</div>"+
    			"<div class='textoCancion'>"+
					"<div class='nameAutor'>"+
					"<span class='nameSong'>"+ value.Name + "</span>"+
					"<span class='separacion'>-</span>"+
					"<span class='autor'>"+value.Artist+"</span>"+

					"</div>"+


					"<div class='album'>"  + value.Artist +"</div>"+
					"<div class='duracion'>"+ value.duration +"</div>"+

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

function getavailablesongs() {
	
	//var url = API_BASE_URL + 'search_song/'+track;
	var url = API_BASE_URL + 'getsongs/';

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			var response = data;
			$('#prova2').show();
			 $.each(response, function (index, value) {
 		
 	 		$('#songs-data').append("<a href='Insertkey.html?id="+value.id+"' ><div class='cancion' id = "+value.id+" > <div class='imagen'>"+
    				"<img src="+IP	+"/images/"+ value.image +" ' class='img-responsive' alt=''>"+
    			"</div>"+
    			"<div class='textoCancion'>"+
					"<div class='nameAutor'>"+
					"<span class='nameSong'>"+ value.Name + "</span>"+
					"<span class='separacion'>-</span>"+
					"<span class='autor'>"+value.Artist+"</span>"+

					"</div>"+


					"<div class='album'>"  + value.Artist +"</div>"+
					"<div class='duracion'>"+ value.duration +"</div>"+

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

function getplaylist() {
	
	//var url = API_BASE_URL + 'search_song/'+track;
	var url = API_BASE_URL + 'getplaylist/';

	$.ajax({
		url : url,
		type : 'GET',
		
		crossDomain : true,
		dataType : 'json',
		success : function(data, status, jqxhr) {
			var response = data;
			$('#prova2').show();
			 $.each(response, function (index, value) {
				 if(index ==0){
 		
 	 		$('#songs-data').append("<a  ><div class='cancion' id = "+value.id+" > <div class='imagen'>"+
    				"<img src="+IP	+"/images/"+ value.image +" ' class='img-responsive' alt=''>"+
    			"</div>"+
    			"<div class='textoCancion'>"+
					"<div class='nameAutor'>"+
					"<span class='nameSong'>"+ value.Name + "</span>"+
					"<span class='separacion'>-</span>"+
					"<span class='autor'>"+value.Artist+"</span>"+

					"</div>"+


					"<div class='album'>"  + index +"</div>"+
					"<div class='duracion'>"+ value.duration +"</div>"+

				"</div>"+
				"<div class='clear'></div>"+
    		"</div></a>");
			 }
				 if(index!=0)
					 {
					 $('#songs-data').append("<a  ><div class='cancion' id = "+value.id+" > <div class='imagen'>"+
			    				"<img  ' class='img-responsive' alt=''>"+
			    			"</div>"+
			    			
			    			"<div class='textoCancion'>"+
							"<div class='nameAutor'>"+
							"<span class='nameSong'>"+ value.Name + "</span>"+
							"<span class='separacion'>-</span>"+
							"<span class='autor'>"+value.Artist+"</span>"+

							"</div>"+

								"<div class='album'>"  + index +"</div>"+
								"<div class='duracion'>"+ value.duration +"</div>"+

							"</div>"+
							"<div class='clear'></div>"+
			    		"</div></a>");
					 }
 	 
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
			
			
			
			 
 		
 	 	$('#songs-data').append("<a href='#' ><div class='cancion' id = "+data.id+" > <div class='imagen'>"+
    				"<img src="+IP	+"/images/"+ data.image +" ' class='img-responsive' alt=''>"+
    			"</div>"+
    			"<div class='textoCancion'>"+
					"<div class='nameAutor'>"+
					"<span class='nameSong'>"+ data.Name + "</span>"+
					"<span class='separacion'>-</span>"+
					"<span class='autor'>"+data.Artist+"</span>"+

					"</div>"+


					"<div class='album'>"  + data.Artist +"</div>"+
					"<div class='duracion'>"+ data.duration +"</div>"+

				"</div>"+
				"<div class='clear'></div>"+
    		"</div></a>");
 	 
			 
			 },
				error : function(jqXHR, options, error) {
					alert(jqXHR + error + options);
				}
			});
}







