function sendForm(evt) 
{ 
	evt.preventDefault();
	var playerId = document.getElementById("gameId").value;
	var formSerialized =  "name=" + document.getElementById("nameInput").value + "&" + "url=" + document.getElementById("urlInput").value + "&" + "description=" + document.getElementById("descriptionInput").value;
	
	if(playerId == 0)
	{
		$.post('http://localhost:8081/games', formSerialized, function() {
			document.getElementById("mensajeEdicion").innerHTML = "Juego <strong>" + document.getElementById("nameInput").value + "</strong> creado con éxito.";
	    	document.getElementById("mensajeEdicion").style.display = "block"; 
		});
	}else
	{
		$.ajax({
		    url: 'http://localhost:8081/games/' + document.getElementById("gameId").value,
		    data: formSerialized,
		    method: 'PUT',
		    success: function(result) {
		    	document.getElementById("mensajeEdicion").innerHTML = "Juego <strong>" + document.getElementById("nameInput").value + "</strong> editado con éxito.";
		    	document.getElementById("mensajeEdicion").style.display = "block"; 
		    }
		});
	}
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