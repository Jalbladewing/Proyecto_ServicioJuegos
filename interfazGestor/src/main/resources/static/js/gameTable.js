/*$(document).ready(function () {

	$('.star i').on('click', function () {
		$(this).
    });

    $('.ckbox label').on('click', function () {
      $(this).parents('tr').toggleClass('selected');
    });

    $('.btn-filter').on('click', function () {
      var $target = $(this).data('target');
      if ($target != 'all') {
        $('.table tr').css('display', 'none');
        $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
      } else {
        $('.table tr').css('display', 'none').fadeIn('slow');
      }
    });

 });*/

function deletePlayer(playerId)
{
	 $.ajax({
		    url: 'http://localhost:8082/players/' + playerId,
		    method: 'DELETE',
		    success: function(result) {
		    	$("#delete" +  playerId).parents('tr').remove();
		    	document.getElementById("mensajeEliminacion").innerHTML = "Jugador con ID <strong>" + playerId + "</strong> eliminado con éxito.";
		    	document.getElementById("mensajeEliminacion").style.display = "block"; 
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
		    	document.getElementById("mensajeEliminacion").innerHTML = "Juego con ID <strong>" + gameId + "</strong> eliminado con éxito.";
		    	document.getElementById("mensajeEliminacion").style.display = "block"; 
		    }
		});
    	
};

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