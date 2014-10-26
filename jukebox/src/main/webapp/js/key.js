
$( document ).ready(function() {
var smoker = getURLParameter('id');

getSpotiSong(smoker);
});


function getURLParameter(name) {
  return decodeURI(
   (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
   );
}

$("#key_button").click(function(){
	var idpet = getURLParameter('id');
	var track = $('#key_input').val();
	
	getSpotiSongs (track);
	});