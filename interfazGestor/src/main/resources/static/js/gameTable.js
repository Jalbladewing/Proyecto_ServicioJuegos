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
		    	location.reload();
		    }
		});
    	
};

function deleteGame(gameId)
{
	 $.ajax({
		    url: 'http://localhost:8081/games/' + gameId,
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