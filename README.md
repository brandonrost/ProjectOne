# Expense Reimbursement System (ERS) - JWA v3.1
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement. 

## Technologies Used

* Java - version 1.8
* Javalin - version 3.13.5
* LogBack/SLF4J - version 1.2.3
* JacksonDataBind - version 2.12.2
* Mockito - version 3.9
* Hibernate - 5.4.3
* JUnit 4 - version 4.13.2
* MariaDB/JDBC - version 2.7.2
* JavaScript - ECMAScript 2020
* HTML 5
* CSS/Bootstrap

## Working Environment
* Spring Tool Suites - version 4.10 (for creating/editing Java-based backend application)
* Visual Studio Code - version 1.55 (for creating/editing HTML, CSS, and JavaScript files)
* Postman - version 8.5.1 (for testing HTTP requests sent to RESTful API on the backend)
* DBWeaver - version 21.0.5 (for viewing database)
* GitHub/GitBash - used for version-controll
* SYSTEM: Windows 10

## Features
Here is a list of features that this application offers. Additional features are listed at the bottom.

Two types of Users:
1. Employee
2. Finance Manager

* Register as an Employee
* Register as a Finance Manager - provided the user is given the required link to access this functionality (only Finance Managers)
* Log-in as an Employee (provided the correct credentials are given for specified User)
* Log-in as a Finance Manager (provided the correct credentials are given for specified User)
* Employees are able (once logged in) to add reimbursements to their unique reimbursement list
  - Those reimbursements must include amount, type of reimbursement (FOOD, TRAVEL, LODGING, OTHER) - chosen by dropdown list
  - The added reimbursement automatically gets assigned to the User who added the reimbursement
  - The added reimbursement automatically assigns the current system time at the time the reimbursement is added
  - The added reimbursement MAY contain a description as well
* Employees are able (once logged in) to delete reimbursements from their unique reimbursement list
* Employees are able to view the status of their own reimbursements
* If logged in as Finance Manager, when viewing the 'reimbursements' page, ALL reimbursements for ALL users are displayed
* If logged in as Finance Manager, when viewing the 'reimbursements' page, the Finance Manager is able to sort reimbursements by status
* If logged in as Finance Manager, while viewing reimbursements, the Finance Manager is able to 'approve' or 'deny' the reimbursement
* Once a reimbursement status is changed, those changes should be updated and displayed to the User's appropriately
* Logged-in users can log out, and the view should change accordingly
* If not logged-in, the Reimbursement list should be empty
* If not logged-in, the login tab should be displayed in the navigation bar
* If logged-in, the logout button should be displayed in the navigation bar

To-Do List:
* Add functionality to sort reimbursements by 'amount'
* Add functionality to allow Employees to upload a photo of their 'reimbursement' along with other information
* Add functionality to allow Finance Managers to view photos that were uploaded with a reimbursement
* Add Selenium tests
* Redirect the user to the login page if they attempt to access 'reimbursements' while not logged in
* Deploy as an EC2 instance on AWS
* Deploy database onto AWS

Appearance To-Do List: 
* Add more dynamic layout to the home-page
* Fix color scheme to be more appealing

## Getting Started
You can clone this repository with git clone <url> and utilize it for your own needs. For example, if you want to clone this repository do the following commands:
  
  
> `$ git clone https://github.com/brandonrost/ProjectOne.git`
> 
> This creates a directory named ProjectOne, initializes a .git directory inside it, pulls down all the data for that repository, and checks out a working copy of the latest version. If you go into the new ProjectOne directory that was just created, you’ll see the project files in there, ready to be worked on or used.

> If you want to clone the repository into a directory named something other than ProjectOne, you can specify the new directory name as an additional argument:
> 
> `$ git clone  https://github.com/brandonrost/ProjectOne.git newProject`
> 
> That command does the same thing as the previous one, but the target directory is called newProject.

- After you have cloned the repository, youll have to configure a database for the application to map to. 
- Next, set the Hibernate `mode` to 'create'
- Once you have done the above two steps, run your application
- Now that you have "created" your database tables, set the Hibernate `mode` to `validate`
- From now on, you should be able to run the application without changing hibernate mode 
   
## Usage
  
> Users should utilize this application as a bare-bones reimbursement system that may be used to track and change current and past reimbursements that Employees have submitted, and Finance Managers have the ability to change. The users (employees and finance managers, alike) should use this application to register, login, and view current and past reimbursements. Below are some pictures to help you get started. 
> 
> ### **Landing Page/Home Sceen**
> 
> ![image](https://user-images.githubusercontent.com/81986564/119172684-3f6b6280-ba34-11eb-90d1-742994b71526.png)
>
> ### **Click Register**
>
> ![register button](https://user-images.githubusercontent.com/81986564/119173738-a8070f00-ba35-11eb-9633-e685df35db17.png)
> 
> ### **Register As An Employee** --> fill out the form to register as a new user
>
> ![register screen](https://user-images.githubusercontent.com/81986564/119174283-632fa800-ba36-11eb-8a81-34d8505ad7bb.png)
> 
> ### **Login To Account ** - automatically redirected to sign-in page
> 
> ![image](https://user-images.githubusercontent.com/81986564/119174551-b6095f80-ba36-11eb-8469-b1795bb4f074.png)
>
> ### **Navigate to the Reimbursements Page** - utilize the navigation tabs
>
> ![reimb page](https://user-images.githubusercontent.com/81986564/119175361-b7875780-ba37-11eb-9ddf-8aa2d66d410d.png)
>
> ### **Add Some Reimbursements**
>
> ![add reimb](https://user-images.githubusercontent.com/81986564/119175409-cc63eb00-ba37-11eb-86b9-4c97f1b5e516.png)
> ![add reimb model](https://user-images.githubusercontent.com/81986564/119175454-d8e84380-ba37-11eb-8099-7b52a6492973.png)
>
> ### **Logout**
>
> ![logout](https://user-images.githubusercontent.com/81986564/119180530-662e9680-ba3e-11eb-9458-634fc260ada3.png)
>
> ### **Register As Finance Manager** - provided the link to this page, not accessible from application. Follow the same login steps as an Employee
>
> ![image](https://user-images.githubusercontent.com/81986564/119180325-2798dc00-ba3e-11eb-9ac5-1bc87e64d7e0.png)
> * **Note:** `/registerFM.html` must be after the root directory in order to register as a Finance Manager - not an Employee
>
> ### **Navigate to Reimbursements Page** - utilize the navigation bar
>
> ![fm reimb page](https://user-images.githubusercontent.com/81986564/119183023-83189900-ba41-11eb-993f-b4ed6f1a9822.png)
> **NOTE:** The Finance Manager has sorting functionality, approve and deny abilities, and has access to more information than an Emplpoyee
>
> See Below:
> ####**General Overview of Finance Manager Reimbursement Page**
> 
> ![fm_reimb_diagram](https://user-images.githubusercontent.com/81986564/119183583-46996d00-ba42-11eb-898c-a8696a5e612f.png)
>
> #### **Filtering**
> 
> |Before|After|
> |------|-----|
> |![image](https://user-images.githubusercontent.com/81986564/119183796-92e4ad00-ba42-11eb-8ce0-167dc0965186.png)|![image](https://user-images.githubusercontent.com/81986564/119183873-aabc3100-ba42-11eb-96de-6b2c7eaedba9.png)|
> 




