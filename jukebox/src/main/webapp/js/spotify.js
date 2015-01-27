$("#prova").click(function(){
	var track = $('#track').val();
	alert(track)
	$('#songs-data ').empty();
	$('#prova').hide();
	$('#track').hide();
	getSpotiSongs (track);
	});
$("#available").click(function(){
	
	alert("HOLA!")
	$('#songs-data ').empty();
	$('#prova').hide();
	$('#track').hide();
	getavailablesongs();
	});
$("#cavailable").click(function(){
	
	alert("HOLA!")
	$('#songs-data ').empty();
	$('#prova').hide();
	$('#track').hide();
	getavailablesongs();
	});
$("#cplaylist").click(function(){
	
	alert("HOLA!")
	window.location='Playlist.html'
	});


$( document ).ready(function() {
	
	
	getavailablesongs();
	});




$("#prova2").click(function(){
	$('#prova').show();
	$('#track').show();
	$('#prova2').hide();
	$('#songs-data').empty();
	
	});

$("#a").click(function() {
    alert(this.val); // id of clicked li by directly accessing DOMElement property
    
});
