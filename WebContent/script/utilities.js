$(document).ready(function(){
    //Start animation
    $('#header').fadeIn('slow');
    $("#mainimg").slideToggle('5000');
    $('#description').slideToggle('slow');
                
    // Function that displays the registration form
    $('#regBtn').click(function(){
        var toggle = $('#regpopup').attr('hidden');
        if(toggle){
            $("#mainDiv").css('filter', 'blur(10px)');
            $('#regpopup').fadeIn('slow');
            $('#regpopup').removeAttr('hidden');
        }else{
            $('#regpopup').fadeOut('slow');
            $('#mainDiv').css('filter', 'blur(0)');
            $('#regpopup').attr('hidden','');
        }        
    });
    
    //Function that displays the login form
    $('#popup').click(function(){
        var toggle = $('#logDiv').attr('hidden');
        if(toggle){
            $('#logDiv').slideDown('slow');
            $('#logDiv').removeAttr('hidden');
        }else{
            $('#logDiv').slideUp('slow');
            $('#logDiv').attr('hidden','');
        }
    });
                
    
    //Check the validity for LOGIN
    $('#logForm').validate({
       rules: {
           email: {
               required: true,
               email: true
           },
           pssw: {
               required: true,
               minlength: 5,
               maxlength: 16
           }
       },
       messages: {
           email: {
               required: '<span class="error">Field email is required</span>',
               email: '<span class="error">Insert a valid e-mail</span>'
           },
           pssw: {
               required: '<span class="error">Field password is required</span>',
               minlength: '<span class="error">The password must be more than 5 chars</span>',
               maxlength: '<span class="error">The password must be less than 16 chars</span>'
           }
       },
       submitHandler: function(logForm){
           logForm.submit();
       }
    });
    
    // Check the validity for REGISTRATION    
        $("#form").validate({
            rules: {
                username: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5,
                    maxlength: 16
                },
                repassword: {
                    required: true,
                    minlength: 5,
                    maxlength: 16,
                    equalTo: "#password"
                }
            },
            messages: {
                username: {
                    required: '<span class="error">Field email is required</span>',
                    email: '<span class="error">Insert a valid e-mail</span>'
                },
                password: {
                    required: '<span class="error">Field password is required</span>',
                    minlength: '<span class="error">The password must be more than 5 chars</span>',
                    maxlength: '<span class="error">The password must be less than 16 chars</span>'
                },
                repassword: {
                    required: '<span class="error">Field password is required</span>',
                    minlength: '<span class="error">The password must be more than 5 chars</span>',
                    maxlength: '<span class="error">The password must be less than 16 chars</span>',
                    equalTo: '<span class="error">The password must be the same</span>'
                }
            },
            submitHandler: function(form){
                form.submit();
            }
        });
});