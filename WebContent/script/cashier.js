$(document).ready(function(){
    function getOrder(){
        $.ajax({
            url : './GetPurchases',
            async: true,
            success : function(results) {  
                $('#results').html(results);
            } 
        });
    }
    
    getOrder();
    setInterval(getOrder, 5 *1000);
    
});    

function logout(){
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