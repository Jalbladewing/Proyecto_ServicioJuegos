function sendForm(evt) 
{ 
	evt.preventDefault();
	var playerId = document.getElementById("playerId").value;
	
	if(playerId == 0)
	{
		$.post('http://localhost:8082/players', $("#myFormAdd").serialize(), function() {
			document.getElementById("mensajeEdicion").innerHTML = "Jugador <strong>" + document.getElementById("nameInput").value + "</strong> creado con éxito.";
	    	document.getElementById("mensajeEdicion").style.display = "block"; 
		});
	}else
	{
		$.ajax({
		    url: 'http://localhost:8082/players/' + document.getElementById("playerId").value,
		    data: $("#myFormEdit").serialize(),
		    method: 'PUT',
		    success: function(result) {
		    	document.getElementById("mensajeEdicion").innerHTML = "Jugador <strong>" + document.getElementById("nameInput").value + "</strong> editado con éxito.";
		    	document.getElementById("mensajeEdicion").style.display = "block"; 
		    }
		});
	}
}