$(function() { 
   
   
    $('.select-pizza').on('change', function() {
       
       $('.selected').prop('checked', false);
       $('.selected').removeClass('selected');
       $(this).addClass('selected');
       
       $.ajax({
          url: 'UpdateServlet?cible=garniture',
          type: 'GET',
          dataType: 'JSON',
          data: {'pizza-id':$(this).val()},
          success: function(json) {
              
              $.each(json, function(key, garniture)
              {
                  $('#topping-'+garniture.id).addClass('selected');
              });
              
          }
       });
       
    });
    
});