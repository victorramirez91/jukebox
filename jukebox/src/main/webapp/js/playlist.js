$("#cavailable").click(function(){
	
	window.location='SearchSong.html'
		//getavailablesongs();
	});
$("#searchsg1").click(function(){
	var track = $('#track').val();	
	$('#prova').hide();
	
	if (track  == '') {
		alert ('No puedes dejar este campo en blanco')
		}; 	
	if (track  != '') {
		$('#songs-data ').empty()
		getSpotiSongs (track)}; 
	
	});
window.setInterval(function(){
	
	//$('#songs-data ').empty()/// call your function here
	getplaylist();
	}, 1000);
$( document ).ready(function() {
	
	
	getplaylist();
	});