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

Appearance To-Do List: 
* Add more dynamic layout to the home-page
* Fix color scheme to be more appealing

## Getting Started
   
(include git clone command)
(include all environment setup steps)

> Be sure to include BOTH Windows and Unix command  
> Be sure to mention if the commands only work on a specific platform (eg. AWS, GCP)

- All the `code` required to get started
- Images of what it should look like

## Usage

> Here, you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action.


