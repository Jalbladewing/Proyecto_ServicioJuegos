function sendForm(evt) 
{ 
	evt.preventDefault();
	
	if(!checkValues()) return;

	$.ajax({
	    url: 'http://localhost:8082/checkDNI/' + document.getElementById("playerDNI").value,
	    data: $("#myFormEdit").serialize(),
	    method: 'GET',
	    success: function(result) {

	    	if(result)
	    	{
	    		executeForm();
	    	}else
	    	{
	    		showIncorrectMessage("DNI incorrecto");
	    	}
	    }
	});
}

function executeForm()
{
	var playerId = document.getElementById("playerId").value;
	
	if(playerId == 0)
	{
		$.post('http://localhost:8082/players', $("#myFormAdd").serialize(), function(result, status, request) {

			if(request.status == 201)
			{
				showCorrectMessage("Jugador <strong>" + document.getElementById("nameInput").value + "</strong> creado con éxito.");
			}else
			{
				showIncorrectMessage("Error al crear el Jugador, ya existe un jugador con el DNI <strong>" + document.getElementById("playerDNI").value + "</strong>");
			}
		});
	}else
	{
		$.ajax({
		    url: 'http://localhost:8082/players/' + document.getElementById("playerId").value,
		    data: $("#myFormEdit").serialize(),
		    method: 'PUT',
		    success: function(result) {
				showCorrectMessage("Jugador <strong>" + document.getElementById("nameInput").value + "</strong> editado con éxito.");

		    },
		    error: function(result)
		    {
		    	showIncorrectMessage("Error al editar el Jugador, no se ha encontrado el Jugador en la Base de Datos o el DNI utilizado ya está en uso por otro Jugador.");
		    }
		});
	}
}

function checkValues()
{
	if(document.getElementById("nameInput").value == "")
	{
		showIncorrectMessage("Nombre es un campo obligatorio");
    	return false;
	}
	
	if(document.getElementById("playerLastname").value == "")
	{
		showIncorrectMessage("Apellido es un campo obligatorio");
    	return false;
	}
	
	if(document.getElementById("playerDNI").value == "")
	{
		showIncorrectMessage("DNI es un campo obligatorio");
    	return false;
	}
	
	return true;
}

function showCorrectMessage(message)
{
	document.getElementById("mensajeError").style.display = "none"; 
	document.getElementById("mensajeEdicion").innerHTML = message;
	document.getElementById("mensajeEdicion").style.display = "block"; 
}

function showIncorrectMessage(message)
{
	document.getElementById("mensajeEdicion").style.display = "none"; 
	document.getElementById("mensajeError").innerHTML = message;
	document.getElementById("mensajeError").style.display = "block"; 
}