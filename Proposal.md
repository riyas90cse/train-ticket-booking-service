### Current Design & Feature Enhancements to Scale the Application
 
Current Design & Implementation Details

- Designed Restful Endpoints to expose the necessary functionality to book train ticket
- Handled Custom Exception to Provide Specific Exception with Proper Error Format
- Added Other End Points such as to perform CRUD for User, Train, Section, Seat for testing purpose 
- Only Exposed Endpoints in Swagger UI related to Booking 
- Added Lombok and Mapstruct to reduce the boilerplate code
- Current Booking System Supports only to book one seat at the time. 
- Current Seat Allocation is Random but Specific to Section 
- Modify Seat Only on the same section 

##### Enhancements
- Security is Necessary Need to be Implemented as Authentication and Authorization
- Sending Email Notification After Booking Ticket
- Enhance the booking system to book multiple seats in a ticket
- Enhance Modify Seat to Book regardless of any section on the train.
- Current Seat Allocation feasibility to select the preferred seat based on the availability.
- Feasibility to enhance multiple platform booking such flight, train, bus etc., 

The above are some of the enhancements for the future application, but we can think of many thing since booking is one of major enterprise application.


