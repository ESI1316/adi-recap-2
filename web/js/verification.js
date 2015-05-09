$(function() {
    
    $('#form-nouveau').validate({
        rules: {
            nom: {
                required: true
            },
            email: {
                required: true,
                mail: true
            },
            adresse: {
                required: true
            }
        },
        messages: {
            nom: {
                required: 'Le nom est requis'
            },
            email: {
                required: 'L\'email est requis',
                mail: 'Adresse email invalide'
            },
            adresse: {
                required: 'Adresse requise'
            }
        }
    });
    
      $('#form-connexion-id').validate({
        rules: {
            id: {
                required: true,
                number: true
            }
        },
        messages: {
            id: {
                required: 'L\'ID est requis',
                number: 'L\'ID doit être un nombre'
            }
        }
    });
    
        $('#form-connexion-email').validate({
        rules: {
            email: {
                required: true,
                mail: true
            }
        },
        messages: {
            email: {
                required: 'L\'Email est requis',
                mail: 'Email invalide'
            }
        }
    });
    
  
});