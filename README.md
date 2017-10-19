# Knockout ToDo List with Bootstrap
Simple todo list app that was made with java rest api and knockout.js

Which libs used:
Backend side: Java Rest Api (Jersey)
Frontend side: Knockout.js, Jquery and Bootstrap

What you need:
1) Create a database that includes Users and Issues table on mysql
Users => Id int, FirstName varchar, LastName varchar, Email varchar, Password varchar
Issues => Id int, Title varchar, Content varchar, AssignUserId int (FK from user), StatusId int
(Actually you don't need to create user table but I have added it maybe I am going to use later)

2) Create tomcat server (Was used tomcat 9 on this sample)

3) Create a new user using postman or you can write own login ui to register. 
   There is a service to create a new user, AccountService.java
   
4) Don't forget to set mysql connection configs in services.

5) Then It will work :)

Note: This is just a sample todo list so of course there are many missing things. 
I'm going to try to improve it on my free times...
