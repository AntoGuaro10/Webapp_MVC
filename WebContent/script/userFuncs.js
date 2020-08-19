$(document).ready(function(){
    $('fieldset').removeAttr('hidden');
    $('fieldset').fadeIn('slow');
    
    if(history.length>0){
        history.forward();
    }
    
    // Display the update form
    $('#update').click(function () {
        
        $('#updateresult').removeAttr('hidden');
        $('#updateresult').fadeIn('slow');        
    });
    
    // Check the update form
    $('#updateform').validate({
       rules: {
           oldpssw: {
               required: true,
               minlength: 5,
               maxlength: 16
           },
           password: {
               required: true,
               minlength: 5,
               maxlength: 16,
               notEqual: '#oldpssw'
           }
       },
       
       messages: {
           oldpssw: {
               required: '<span class="error">This field is required</span>',
               minlength: '<span class="error">The password must be more than 5 chars</span>',
               maxlength: '<span class="error">The password must be less than 16 chars</span>'
           },
           password: {
               required: '<span class="error">This field is required</span>',
               minlength: '<span class="error">The password must be more than 5 chars</span>',
               maxlength: '<span class="error">The password must be less than 16 chars</span>',
               notEqual: '<span class="error">New password must be different from the previous</span>'
           }
       },
       
       submitHandler: function(form){
           form.submit();
       }
    });
    
    $("#ordForm").validate({
       rules: {
           menu: {
               required: true
           },
           quantity: {
               required: true,
               min: 1,
               max: 5
           }
       },
       messages: {
           menu: {
               required: '<span class="error">This field is required</span>'
           },
           quantity: {
               required: '<span class="error">You must insert a quantity</span>',
               min: '<span class="error">At least one product must be choose</span>',
               max: '<span class="error">The max quantity is five products</span>'
           }
       },
       submitHandler: function(form){
           form.submit();
       }
    });
    
    $("#order").click(function(){
        $("#ordDiv").removeAttr('hidden');
        $("#ordDiv").fadeIn('slow');
    });
    
    
    $("#showPrice").click(function(){
        var m = $("#menu").val();
        console.log(m);
        
        var q = $("#quantity").val();
        var tot = 0;
        if(m == 'sandwich'){
            tot = 1.5 * q;
            console.log(tot);
        }else if(m == 'breadfries'){
            tot = 2.5 * q;
            console.log(tot);
        }else if(m == 'fries'){
            tot = 1 * q;
            console.log(tot);
        }else if(m == 'coca'){
            tot = 1.5 * q;
            console.log(tot);
        }else if(m == 'coffee'){
            tot = 1 * q;
            console.log(tot);
        }else{
            tot = 0.5 * q;
            console.log(tot);
        }
        
        $("#submit").removeAttr('hidden');
        $("#price").attr('value', tot);  
    });       
    
    $("#check").click(function(){
        $("#chechDiv").removeAttr("hidden");
        $("#checkDiv").show('slow');
        getOrder();
        
        setInterval(getOrder, 5 * 1000);
    });
    
    function getOrder(){
        $.ajax({
            url : './CheckOrder',
            async: true,
            success : function(results) {  
                $('#checkDiv').html(results);
            } 
        });
    }
    
});