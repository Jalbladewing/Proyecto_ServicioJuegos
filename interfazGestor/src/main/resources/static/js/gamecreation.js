function sendForm(evt) 
{ 
	evt.preventDefault();
	if(!checkValues()) return;
	
	var gameId = document.getElementById("gameId").value;
	var formSerialized =  "name=" + document.getElementById("nameInput").value + "&" + "url=" + document.getElementById("urlInput").value + "&" + "description=" + document.getElementById("descriptionInput").value;
	
	if(gameId == 0)
	{
		$.post('http://localhost:8081/games', formSerialized, function(result, status, request) {
			if(request.status == 201)
			{
				showCorrectMessage("Juego <strong>" + document.getElementById("nameInput").value + "</strong> creado con éxito.");
			}else
			{
				showIncorrectMessage("Error al crear el Juego, ya existe un juego con el Título <strong>" + document.getElementById("nameInput").value + "</strong>");
			}
		});
	}else
	{
		$.ajax({
		    url: 'http://localhost:8081/games/' + document.getElementById("gameId").value,
		    data: formSerialized,
		    method: 'PUT',
		    success: function(result) {
		    	showCorrectMessage("Juego <strong>" + document.getElementById("nameInput").value + "</strong> editado con éxito.");
		    },
		    error: function(result)
		    {
		    	showIncorrectMessage("Error al editar el Juego, no se ha encontrado el Juego en la Base de Datos o el Título introducido ya existe.");
		    }
		});
	}
}

function checkValues()
{
	if(document.getElementById("nameInput").value == "")
	{
		showIncorrectMessage("Título es un campo obligatorio");
    	return false;
	}
	
	if(document.getElementById("urlInput").value == "")
	{
		showIncorrectMessage("El juego debe de tener una imagen.");
    	return false;
	}
	
	if(document.getElementById("descriptionInput").value == "")
	{
		showIncorrectMessage("Descripción es un campo obligatorio");
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

$(document).ready( function() {
    	$(document).on('change', '.btn-file :file', function() {
		var input = $(this),
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		input.trigger('fileselect', [label]);
		});

		$('.btn-file :file').on('fileselect', function(event, label) {
		    
		    var input = $(this).parents('.input-group').find(':text'),
		        log = label;
		    
		    if( input.length ) {
		        input.val(log);
		    } else {
		        if( log ) alert(log);
		    }
	    
		});
		function readURL(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        
		        reader.onload = function (e) {
		            $('#img-upload').attr('src', e.target.result);
		        }
		        
		        reader.readAsDataURL(input.files[0]);
		    }
		}

		$("#imgInp").change(function(){
		    readURL(this);
		}); 	
		
	});