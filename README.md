# Online Movie Ticket Booking - ShowTime [Backend]
The **Online Movie Ticket Booking - ShowTime** is a web-based application that allows users to book movie tickets online. It provides a convenient way for users to browse available movies, view showtimes, and reserve seats for their preferred movie shows.

## Features
- **Movie Listing**: Users can view the list of available movies along with their details such as title, genre, and duration.
- **Showtimes**: Users can check the showtimes for each movie and choose a suitable timing for booking.
- **Seat Selection**: Users can select their desired seats from the available seating arrangement for the chosen show.
- **Reservation**: Users can reserve their selected seats and proceed to the payment process.
- **Payment**: Users can make online payments securely to confirm their movie ticket bookings.
- **Booking Confirmation**: Users receive a booking confirmation with all the details of their reservation.

## Technologies Used
- Java
- Spring Boot
- Hibernate
- MySQL
- HTML
- CSS

## Setup Instructions
1. Clone the repository:
   ```
   git clone https://github.com/harshitha123455/Online_Movie_Booking_BACKEND.git
   ```
2. Set up the database:
   - Create a MySQL database and update the database configuration in `src/main/resources/application.properties`.
   - Run the database migration script to create the necessary tables and seed initial data.
3. Set up email configuration:
   - Open the `src/main/resources/application.properties file`.
   - Configure the properties for email settings.
5. Build and run the application:
   - Right-click on the project in the Eclipse Package Explorer and select `Run As > Maven Build`.
   - In the Goals field, enter `package` and click Run to build the application.
   - Right-click on the project again in the Eclipse Package Explorer and select `Run As > Spring Boot App`.<br /><br />
Note: Make sure you have the necessary dependencies and plugins installed in your Eclipse IDE for Java, Spring Boot, and Maven.
