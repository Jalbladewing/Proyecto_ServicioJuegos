$(function() {
  $('#myForm').submit(function(){
    $.post('http://localhost:8082/players', $("#myForm").serialize());
  });
});