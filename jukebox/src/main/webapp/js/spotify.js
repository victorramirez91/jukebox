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



$(document).ready(main);
 
var contador = 1;
 
function main(){
	$('#prova2').hide();
	$('.incon-list2').click(function(){
		// $('nav').toggle(); 
 
		if(contador == 1){
			$('nav').animate({
				left: '0'
			});
			contador = 0;
		} else {
			contador = 1;
			$('nav').animate({
				left: '-100%'
			});
		}
 
	});
 
};




$("#prova2").click(function(){
	$('#prova').show();
	$('#track').show();
	$('#prova2').hide();
	$('#songs-data').empty();
	
	});

$("#a").click(function() {
    alert(this.val); // id of clicked li by directly accessing DOMElement property
    
});
