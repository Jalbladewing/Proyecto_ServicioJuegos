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

function updateFavourite(checkIdName, gameId)
{
	 if($('#' + checkIdName + gameId).is(':checked'))
         $.post('http://localhost:8083/players/1/games/?id=' + gameId);
     else
    	 $.ajax({
    		    url: 'http://localhost:8083/players/1/games/' + gameId,
    		    method: 'DELETE',
    		    success: function(result) {
    		        // Do something with the result
    		    }
    		});
};

function updateFollow(checkIdName, playerId)
{
	 if($('#' + checkIdName + playerId).is(':checked'))
         $.post('http://localhost:8084/players/1/followers/?id=' + playerId);
     else
    	 $.ajax({
    		    url: 'http://localhost:8084/players/1/followers/' + playerId,
    		    method: 'DELETE',
    		    success: function(result) {
    		        // Do something with the result
    		    }
    		});
};

function deletePlayer(playerId)
{
	 $.ajax({
		    url: 'http://localhost:8082/players/' + playerId,
		    method: 'DELETE',
		    success: function(result) {
		    	location.reload();
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