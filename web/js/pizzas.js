$(function() { 
    
    var $command = $('#command');
    var $pizzaCommand = $('#pizza-command');
    var $cancelCommand = $('#cancel-command');
    
    $('.select-pizza').on('change', function() {
       
       $('.selected').prop('checked', false);
       $('.selected').removeClass('selected');
       $(this).addClass('selected');
       
       $.ajax({
          url: 'UpdateServlet?action=garniture',
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
    
    $('.command-button').on('click', function() 
    {
       var id = $(this).attr('id');
       
       if (id === 'pizza-command')
       {
           $(this).addClass('hide');
           $command.removeClass('hide');
       }
       else if (id === 'cancel-command')
       {
            $command.addClass('hide');
            $pizzaCommand.removeClass('hide');
       }
       
    });
    

    
});