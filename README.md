# Library Management System Using Spring Boot and MySQL
- This project is Built by using Spring boot and use the database as MySQL.
- It consists of two roles Admin and users.
- Admin can add the categories , author and Books .Also he has all the authorities to edit these details.
- User can only view the Books by categories or By Authors.
- I have use JWT Token for Authorization which makes ease of role authorization.
- Also I have add swagger UI for API Documentaion.

## How to run this
- First you have to install and download MySQL workbenach ,server ,shell of latest versions.
- Setup your credentials in MySQL.
- Then Clone this repository to your local machine by using the command
  ```
  git clone https://github.com/Baskar-3010/Library-Management-System
  ```
- Open Spring Tool Suite and import the downloaded repository
- Run as Spring Boot app
- Then you have to create roles in repository by following command in your mysql workbench
```
insert into lms.roles values (1,"ROLE_ADMIN");
insert into lms.roles values (2,"ROLE_USER";
```
- Then create user with role as "USER_ROLE" and admin with "ROLE_ADMIN"
- Then signin and get the Authorization token JWT and attach it to the header of the postman request for every request and then you can hit the APIs
