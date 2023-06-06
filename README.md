# Online_Moving_Booking
- Basic CRUD operations - add, list, find by ID, find by name is implemented for both Movie and Screen.
- All these operations can be performed using appropriate HttpRequest to the server.
- Duplicate entries with same name are not allowed and will generate appropriate error messages.
- All the responses coming from the server are of the form of an HttpResponse.
---
- Added Login System for admin
- All admin operation excluding /login and /add require authorization token in the request header to perform operations.
- The JSON web token is generated when an admin logs in. Any future communication of admin with server must use this token to access admin privilege.
