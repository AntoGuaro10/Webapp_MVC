# Webapp_MVC
This is a simple webapp developed in JavaEE and Apache Tomcat, using MVC approach, Servlet, JSP and jQuery framework; read the "SPECIFIC" file for a complete overview.


*** RUN of the project ***

The system will boot up showing the index.html page
At the top right it will be possible to register or log in using the appropriate forms.
Once logged in, the system will automatically infer the type of user,
redirecting the latter to the corresponding JSP

GENERIC USER:
STEPS TO FOLLOW: Login -> Book your place -> Order food & drink -> Check your orders -> Leave your seat and exit

You will see buttons and links that allow you to perform certain actions.
You can "buy" items by selecting them from the drop-down menu, adding them
the desired quantity (Max 5) and finally by clicking on the appropriate button you can view the price of the order,
clicking on OK the request will actually be started.
With the appropriate button you can view your orders, the system will make AJAX calls
to periodically update the summary table.
You can change your password.
By clicking on the link to book a deckchair, you will be redirected to the JSP where it will be possible
click only on the free deckchairs.
If you try to occupy two deckchairs, the system will show an alert; To free the deckchair and exit just click on the appropriate link.

LIFEGUARD USER:
STEPS TO FOLLOW: Login -> Click on any deckchair -> view deckchair info

This view is quite simple, it allows the "Lifeguard" employee to monitor at constant time,
through AJAX requests, the status of the deckchairs and to view the information.

USER COOK:
STEPS TO FOLLOW: Login -> Wait for any orders to be completed -> Complete orders by clicking on the order ID

This view is only concerned with displaying at constant time, through AJAX requests,
the status of orders and to complete them, updating the status of clients' orders.

CASHIER USER:
STEPS TO FOLLOW: Login -> Wait for any orders from customers -> View a table containing the order history

This view is purely informative, the cashier in fact does not interact with the view, but merely keeps track of orders and total cash flow.

ADMIN USER:
STEPS TO FOLLOW: Login -> Click for add a new employee -> Fill in the form -> Show all employees -> If necessary, click on a User ID to delete it

Admin takes care of adding new employees, deleting existing users and viewing their information.
