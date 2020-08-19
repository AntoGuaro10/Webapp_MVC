function check(){
        if(confirm('Do you want to take this seat?')){
            $('input').submit();
        }
    }
    
$(document).ready(function(){
    $("#back").click(function(){
       if(confirm('Go back to your page?')){
           location.replace('./userInfo.jsp');
       } 
    });
});  