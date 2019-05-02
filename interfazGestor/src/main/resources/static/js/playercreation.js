function sendForm(evt) 
{ 
	evt.preventDefault();
	var playerId = document.getElementById("playerId").value;
	
	if(playerId == 0)
	{
		$.post('http://localhost:8082/players', $("#myFormAdd").serialize(), function() {
			location.href = "/playerList";
		});
	}else
	{
		$.ajax({
		    url: 'http://localhost:8082/players/' + document.getElementById("playerId").value,
		    data: $("#myFormEdit").serialize(),
		    method: 'PUT',
		    success: function(result) {
		    	location.href = "/playerList";
		    }
		});
	}
}