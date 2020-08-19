$(document).ready(function (){
    $('#add').click(function () {
        
        $('#result').removeAttr('hidden');
        $('#result').fadeIn('slow');
    });
    
    $('#addform').validate({
       rules: {
           username: {
               required: true,
               email: true
           },
           pass: {
               required: true,
               minlength: 5,
               maxlength: 16
           }
       },
       
       messages: {
           username: {
               required: '<span class="error">This field is required</span>',
               email: '<span class="error">Insert a valid email</span>'
           },
           pass: {
               required: '<span class="error">This field is required</span>',
               minlength: '<span class="error">The password must be more than 5 chars</span>',
               maxlength: '<span class="error">The password must be less than 16 chars</span>'
           }
       },
       
       submitHandler: function(form){
           form.submit();
       }
    });
    
    function getAll(){
        $.ajax({
            url : './GetAll',
            async: true,
            success : function(results) {  
                $('#show').html(results);
            } 
        });
    }
    
    function confirm(){
       if(confirm('Are you sure you want to delete him?')){
            $('input').submit();
        }
    }
    
    $("#showall").click(function(){
        getAll();
        setInterval(getAll, 10 * 1000);
    });         
});

function exit(){
        if(confirm('Are you sure you want exit?')){
            $.ajax({
                url : './Logout',
                async: true,
                success : function() {  
                    location.replace('/guarisco');
                } 
            });
        }
}