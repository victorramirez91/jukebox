$("#prova").click(function(){
	var track = $('#track').val();
	alert(track)
	$('#songs-data ').empty();
	$('#prova').hide();
	$('#track').hide();
	getSpotiSongs (track);
	});

$( document ).ready(function() {
    $('#prova2').hide();
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
