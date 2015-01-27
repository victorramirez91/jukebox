$( document ).ready(function() {
var smoker = getURLParameter('id');
$('#back_button').hide();
getSpotiSong(smoker);
});


function getURLParameter(name) {
  return decodeURI(
   (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
   );
}

$("#key_button").click(function(){
	var idpet = getURLParameter('id');
	var keyins = $('#key_input').val();
	if (keyins  == '') { alert ('No puedes dejar este campo en blanco')}; 
	if (keyins  != '') { check_key (idpet,keyins)}; 
	
	
	});

$("#back_button").click(function(){
	var url      = window.location.href;
	$(location).attr('href',url);
	});