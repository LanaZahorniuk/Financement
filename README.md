## Financement (Backend) ##

**Project Description:**

The Financial Management Application is a web-based solution designed for tracking and managing users' expenses and incomes. It provides tools for effective budget planning, financial transaction tracking, and financial analysis.

### Key Features and Capabilities:

1. **User Management and Data Tracking:**
    - User registration with basic information (name, surname, date of birth).
    - Authentication via unique username and secure password.

2. **Financial Account Management:**
    - Creation and management of financial accounts of various types (e.g., savings accounts, checking accounts).
    - Tracking of current balance and currency for each account.

3. **Budget Planning:**
    - Setting and monitoring budget periods with specified incomes and expenses.
    - Linking budgets to specific financial accounts for financial transaction accounting and analysis.

4. **Expense and Income Management:**
    - Recording and tracking of all financial transactions.
    - Categorization of expenses and incomes for convenient analysis.


### Technology Stack:

The project is implemented in Java programming language using the Spring Boot framework for web application development. MySQL database is used as the data store, ensuring reliable storage and fast access to users' financial data. Docker is employed for easy deployment and scalability of the application.

**Database Structure**

The project utilizes MySQL for data storage. Below is a description of the main tables and their relationships.

### Tables

**roles**
- **role_id**: unique identifier for a role (UUID)
- **role_name**: name of the role

**authorities**
- **authority_id**: unique identifier for an authority (UUID)
- **authority_name**: name of the authority

**authority_role**
- **authority_id**: identifier for an authority (linked to authorities.authority_id)
- **role_id**: identifier for a role (linked to roles.role_id)
- **Primary Key**: combined key of authority_id and role_id

**users**
- **user_id**: unique identifier for a user (UUID)
- **first_name**: user's first name
- **last_name**: user's last name
- **date_of_birth**: user's date of birth
- **registration_date**: date when the user registered
- **user_info_id**: identifier for user information (linked to users_info.user_info_id)
- **Primary Key**: user_id

**users_info**
- **user_info_id**: unique identifier for user information (UUID)
- **user_name**: username for authentication
- **email**: user's email address
- **password**: hashed password
- **phone_number**: user's phone number
- **role_id**: identifier for user role (linked to roles.role_id)
- **Primary Key**: user_info_id

**accounts**
- **account_id**: unique identifier for an account (UUID)
- **account_name**: name of the account
- **balance**: current balance of the account
- **currency**: currency type of the account
- **budget_id**: identifier for a budget (linked to budget.budget_id)
- **user_info_id**: identifier for the user (linked to users_info.user_info_id)
- **Primary Key**: account_id

**budget**
- **budget_id**: unique identifier for a budget (UUID)
- **start_date**: start date of the budget period
- **end_date**: end date of the budget period
- **planned_income**: planned income for the budget period
- **planned_expenses**: planned expenses for the budget period
- **account_id**: identifier for an account (linked to accounts.account_id)
- **Primary Key**: budget_id

**expense_category**
- **expense_category_id**: unique identifier for an expense category (UUID)
- **expense_category_name**: name of the expense category
- **Primary Key**: expense_category_id

**expense**
- **expense_id**: unique identifier for an expense (UUID)
- **expense_amount**: amount of the expense
- **expense_date**: date of the expense
- **expense_transaction_description**: description of the expense transaction
- **expense_category_id**: identifier for an expense category (linked to expense_category.expense_category_id)
- **account_id**: identifier for an account (linked to accounts.account_id)
- **Primary Key**: expense_id

**income_category**
- **income_category_id**: unique identifier for an income category (UUID)
- **income_category_name**: name of the income category
- **Primary Key**: income_category_id

**income**
- **income_id**: unique identifier for an income (UUID)
- **income_amount**: amount of the income
- **income_date**: date of the income
- **income_transaction_description**: description of the income transaction
- **income_category_id**: identifier for an income category (linked to income_category.income_category_id)
- **account_id**: identifier for an account (linked to accounts.account_id)
- **Primary Key**: income_id

### Relationships

- **roles** and **authorities** are linked through the **authority_role** table to define authority-role relationships.
- **users** and **users_info** are linked via **user_info_id** to separate authentication logic from user core information.
- **accounts**, **budget**, **expense**, **income**, and other tables are used to track financial operations and planning.

This structured database schema enables efficient management and tracking of users' financial activities, ensuring data integrity and providing comprehensive insights into budgeting, expenses, and incomes.

### Used Technologies

**Programming Language:** Java

**Frameworks and Libraries:**
- **Spring Framework:**
    - **Spring Boot:** Simplifies Spring application setup and development.
    - **Spring Security:** Manages authentication and authorization.
    - **Spring Data JPA (Hibernate):** Implements the persistence layer for interacting with MySQL.
    - **SpringDoc OpenAPI:** Generates and serves OpenAPI documentation.
    - **Lombok:** Reduces boilerplate code in Java classes.
    - **MapStruct:** Facilitates mapping between Java bean types.
    - **Swagger:** Annotations for generating API documentation.
    - **jBCrypt:** Library for secure password hashing.
    - **Liquibase:** Handles database schema changes via migrations.

**Database:**
- **MySQL:** Main database for storing application data.
- **H2:** In-memory database used for testing purposes.

**Build and Testing Tools:**
- **Maven:** Build automation tool and dependency management.
- **Jacoco:** Tool for measuring code coverage during testing.

**API:**
- **RESTful API:** Facilitates communication between the client and server.

**Validators:**
- **@UuidFormatChecker** — custom annotation for checking UUID format

These technologies were chosen to ensure robustness, security, and scalability in the Financial Management Application, providing efficient management of users' financial activities and enhancing user experience through modern software development practices.

### Conclusion:

The Financial Management Application is designed to streamline personal finance management, offering an intuitive interface and robust features for controlling incomes and expenditures. The project prioritizes data security and accuracy of financial operations, helping users achieve their financial goals and effectively manage their finances.

### Contact Information

**Lana Zahorniuk**

- **Email:** lanazahorniuk@gmail.com
- **GitHub:** [github.com/LanaZahorniuk](https://github.com/LanaZahorniuk)

Feel free to reach out via email or connect with me on GitHub!
