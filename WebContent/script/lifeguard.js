$(document).ready(function(){
    function request(){
        $.ajax({
            url : './SeatState',
            async: true,
            success : function(results) {  
                $('#result').html(results);
            } 
        });
    }
    
    request();
    
    setInterval(request, 60 * 1000);
    
});

function back(){
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