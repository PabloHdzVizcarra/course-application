# Course Application

The idea of this project is to practice spring boot and spring security, create different
roles and that users can authenticate themselves and through those roles have permission
to certain functionalities of the application.

## Configuration

### Database First Step

The application uses MySQL as default database, you have to add the URL of your MySQL
instances through the spring boot **application.properties** configuration file.

### Run App

Once the database has been configured you can run the application with the following
command

```shell
./mvnw spring-boot:run
```

To build an artifact use the following command

```shell
./mvnw clean package
```
the generated artifact is located in target folder

### Configuration Role

The users created for the sample do not contain any established role, you will have to 
add a role to these users or a new user by modifying the database manually.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring JPA
- MySQL
- Lombok

## Expected Behaviour

- Anyone can create an account
- A list of courses that users can subscribe to
- Once the account is created, users can log in
- The application has different levels of authorization that are managed by roles that are
  ROLE_USER, ROLE_ADMIN and ROLE_MASTER

## End Points

### All Roles or Any Role

- GET /api/course : fetch all courses
- POST /api/user : save user
- POST /api/user/save-course : save one course to a user
- GET /api/user/login : login user in the app

### Admin Role

- POST /api/course : save course
- GEt /api/user : fetch all users

### Master Role
- GET /api/role : fetch all roles
- POST /api/role : create a new role
- POST /api/user/save-role : save a role to a user

