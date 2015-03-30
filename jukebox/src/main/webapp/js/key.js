$( document ).ready(function() {
var smoker = getURLParameter('id');
$('#back_button').hide();
getSpotiSong(smoker);
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

function getURLParameter(name) {
  return decodeURI(
   (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
   );
}
$("#cplaylist").click(function(){
	
	
	window.location='Playlist.html'
	});
$("#key_button").click(function(){
	var idpet = getURLParameter('id');
	var keyins = $('#icon_prefix').val();
	if (keyins  == '') { alert ('No puedes dejar este campo en blanco')}; 
	if (keyins  != '') { check_key (idpet,keyins)}; 
	
	
	});
$("#insertkey").click(function(){
	var idpet = getURLParameter('id');
	var keyins = $('#icon_prefix').val();
	if (keyins  == '') { alert ('No puedes dejar este campo en blanco')}; 
	if (keyins  != '') { check_key (idpet,keyins)}; 
	
	
	});


$("#back_button").click(function(){
	var url      = window.location.href;
	$(location).attr('href',url);
	});
$("#cavailable").click(function(){
	
	window.location='SearchSong.html'
		
	});
$("#goback").click(function(){
	
	window.location='SearchSong.html'
		
	});