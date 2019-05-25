function updateFavourite(checkIdName, gameId)
{
	var options = {
			settings: {
				duration: 2000
			}
		};
	
	 if($('#' + checkIdName + gameId).is(':checked'))
	 {
         $.post('http://localhost:8083/players/1/games/?id=' + gameId, function(result, status, request) {
        	if(request.status == 201)
 			{
        		iqwerty.toast.Toast('Juego con ID ' + gameId +  ' añadido a favoritos.', options);
 			}else
 			{
 				iqwerty.toast.Toast('Juego con ID ' + gameId +  ' no encontrado en la base de datos.', options);
 			}
         });
     }else
     {
    	 $.ajax({
    		    url: 'http://localhost:8083/players/1/games/' + gameId,
    		    method: 'DELETE',
    		    success: function(result) {
    		    	iqwerty.toast.Toast('Juego con ID ' + gameId +  ' eliminado de favoritos.', options);
    		    },
    		    error: function(result) {
    		    	iqwerty.toast.Toast('Juego con ID ' + gameId +  ' no encontrado en la base de datos.', options);
    		    }
    		});
     }
};

function updateFollow(checkIdName, playerId)
{
	var options = {
			settings: {
				duration: 2000
			}
		};
	
	if(playerId == 1)
	{
		document.getElementById(checkIdName + playerId).checked = false;
		iqwerty.toast.Toast('No puedes seguirte a ti mismo.', options);
		return;
	}
	
	 if($('#' + checkIdName + playerId).is(':checked'))
	 {
         $.post('http://localhost:8084/players/1/followers/?id=' + playerId, function(result, status, request) {
         	if(request.status == 201)
 			{
        		iqwerty.toast.Toast('Jugador con ID ' + playerId +  ' seguido.', options);
 			}else
 			{
 				iqwerty.toast.Toast('Jugador con ID ' + playerId +  ' no encontrado en la base de datos.', options);
 			}
         });
	 }else
	 {
    	 $.ajax({
    		    url: 'http://localhost:8084/players/1/followers/' + playerId,
    		    method: 'DELETE',
    		    success: function(result) {
    		    	iqwerty.toast.Toast('Jugador con ID ' + playerId +  ' dejado de seguir.', options);
    		    },
    		    error: function(result) {
    		    	iqwerty.toast.Toast('Jugador con ID ' + playerId +  ' no encontrado en la base de datos.', options);
    		    }
    		});
	 }
};

function searchPlayer(evt)
{
	evt.preventDefault();
	var prefix = $('input[name=searchRadio]:checked').val();
	var searchInput = $("#searchInput").val();
	
	if(searchInput.localeCompare("") == 0) searchInput = "null";
	
	if(prefix == "nameRadio")
	{
		location.href = "/playerList/name/" + searchInput;
		
	}else if(prefix == "lastnameRadio")
	{
		location.href = "/playerList/lastname/" + searchInput;
		
	}else if(prefix == "ageRadio")
	{
		location.href = "/playerList/age/" + searchInput;
		
	}else //DNI
	{
		location.href = "/playerList/dni/" + searchInput;
	}
}

function searchGame(evt)
{
	evt.preventDefault();
	var prefix = $('input[name=searchRadio]:checked').val();
	var searchInput = $("#searchInput").val();
	
	if(searchInput.localeCompare("") == 0) searchInput = "null";
	
	if(prefix == "nameRadio")
	{
		location.href = "/gameList/name/" + searchInput;
	}
}

//Filter by tag (following/not following OR favourite/not favourite)
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