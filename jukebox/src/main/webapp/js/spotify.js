$("#searchsg1").click(function() {
	var track = $('#track').val();
	$('#prova').hide();

	if (track == '') {
		alert('No puedes dejar este campo en blanco')
	};
	if (track != '') {
		$('#songs-data ').empty()
		getSpotiSongs(track)
	};

});
$("#available").click(function() {
	$('#songs-data ').empty();
	$('#prova').hide();

	getavailablesongs();
});
$("#cavailable").click(function() {
	$('#songs-data ').empty();
	$('#prova').hide();

	getavailablesongs();
});
$("#cplaylist").click(function() {


	window.location = 'Playlist.html'
});


$(document).ready(function() {
	getavailablesongs();
});



$("#prova2").click(function() {
	$('#prova').show();
	$('#track').show();
	$('#prova2').hide();
	$('#songs-data').empty();

});

$("#a").click(function() {
	alert(this.val); // id of clicked li by directly accessing DOMElement property

});