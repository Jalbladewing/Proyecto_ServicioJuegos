function deletePlayer(playerId)
{
	 $.ajax({
		    url: 'http://localhost:8082/players/' + playerId,
		    method: 'DELETE',
		    success: function(result) {
		    	$("#delete" +  playerId).parents('tr').remove();
		    	showCorrectMessage("Jugador con ID <strong>" + playerId + "</strong> eliminado con éxito.");
		    },
		    error: function(result) {
		    	showIncorrectMessage("Jugador con ID <strong>" + playerId + "</strong> no encontrado en la base de datos.");
		    }
		});
    	
};

function deleteGame(gameId)
{
	 $.ajax({
		    url: 'http://localhost:8081/games/' + gameId,
		    method: 'DELETE',
		    success: function(result) {
		    	$("#delete" +  gameId).parents('tr').remove();
		    	showCorrectMessage("Juego con ID <strong>" + gameId + "</strong> eliminado con éxito.");
		    },
		    error: function(result) {
		    	showIncorrectMessage("Juego con ID <strong>" + gameId + "</strong> no encontrado en la base de datos.");
		    }
		});
    	
};

function showCorrectMessage(message)
{
	document.getElementById("mensajeError").style.display = "none"; 
	document.getElementById("mensajeEliminacion").innerHTML = message;
	document.getElementById("mensajeEliminacion").style.display = "block"; 
}

function showIncorrectMessage(message)
{
	document.getElementById("mensajeEliminacion").style.display = "none"; 
	document.getElementById("mensajeError").innerHTML = message;
	document.getElementById("mensajeError").style.display = "block"; 
}

$(document).ready(function () {
    $('.btn-filter').on('click', function () {
      var $target = $(this).data('target');
      if ($target != 'all') {
        $('.table tr').css('display', 'none');
        $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
      } else {
        $('.table tr').css('display', 'none').fadeIn('slow');
      }
    });

 });