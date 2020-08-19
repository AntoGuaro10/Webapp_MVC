$(document).ready(function(){
    function getOrders(){
        $.ajax({
            url : './CookOrders',
            async: true,
            success : function(results) {  
                $('#results').html(results);
            } 
        });
    }    
    
    getOrders();
    setInterval(getOrders, 2 *1000);
    
    function confirm(){
       if(confirm('Complete this order?')){
            $('input').submit();
        }
    }
    
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