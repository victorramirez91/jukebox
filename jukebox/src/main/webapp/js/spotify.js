$("#prova").click(function(){
	var track = $('#track').val();
	alert(track)
	$('#songs-data > tbody').empty();
	getSpotiSongs (track);
	});