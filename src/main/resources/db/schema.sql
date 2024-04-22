USE fin_db;


DROP TABLE IF EXISTS user_info, user, account, budget, expense, income, expense_category, income_category, authority_role, role, authority;


CREATE TABLE role
(
    role_id   BINARY(16)  NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (role_id)
);


CREATE TABLE authority
(
    authority_id   BINARY(16)  NOT NULL,
    authority_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (authority_id)
);


CREATE TABLE authority_role
(
    authority_id BINARY(16) NOT NULL,
    role_id      BINARY(16) NOT NULL,
    PRIMARY KEY (authority_id, role_id),
    FOREIGN KEY (authority_id) REFERENCES authority (authority_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);


CREATE TABLE user
(
    user_id           BINARY(16)  NOT NULL,
    first_name        VARCHAR(50) NOT NULL,
    last_name         VARCHAR(50) NOT NULL,
    date_of_birth     DATE        NOT NULL,
    registration_date DATE        NOT NULL,
    user_info_id      BINARY(16) UNIQUE,
    PRIMARY KEY (user_id)
);


CREATE TABLE user_info
(
    user_info_id BINARY(16)  NOT NULL,
    user_name    VARCHAR(50) NOT NULL,
    email        VARCHAR(50) NOT NULL,
    password     VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50),
    role_id      BINARY(16),
    PRIMARY KEY (user_info_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);


CREATE TABLE account
(
    account_id   BINARY(16)                                                                                                                   NOT NULL,
    account_name VARCHAR(50)                                                                                                                  NOT NULL,
    balance      DECIMAL(19, 4)                                                                                                               NOT NULL,
    currency     ENUM ('USD', 'EUR', 'JPY', 'GBP', 'AUD', 'PLN', 'UAH', 'CAD', 'CNY', 'CHF', 'INR', 'BRL', 'SEK', 'NZD', 'SGD', 'MXN', 'ZAR') NOT NULL,
    budget_id    BINARY(16) UNIQUE,
    user_info_id BINARY(16),
    PRIMARY KEY (account_id),
    FOREIGN KEY (user_info_id) REFERENCES user_info (user_info_id)
);


CREATE TABLE budget
(
    budget_id        BINARY(16)     NOT NULL,
    start_date       DATE           NOT NULL,
    end_date         DATE           NOT NULL,
    planned_income   DECIMAL(19, 4) NOT NULL,
    planned_expenses DECIMAL(19, 4) NOT NULL,
    account_id       BINARY(16) UNIQUE,
    PRIMARY KEY (budget_id),
    FOREIGN KEY (account_id) REFERENCES account (account_id)
);


CREATE TABLE expense_category
(
    expense_category_id   BINARY(16)  NOT NULL,
    expense_category_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (expense_category_id)
);


CREATE TABLE expense
(
    expense_id                      BINARY(16)     NOT NULL,
    expense_amount                  DECIMAL(19, 4) NOT NULL,
    expense_date                    DATE           NOT NULL,
    expense_transaction_description VARCHAR(128),
    expense_category_id             BINARY(16),
    account_id                      BINARY(16),
    PRIMARY KEY (expense_id),
    FOREIGN KEY (expense_category_id) REFERENCES expense_category (expense_category_id),
    FOREIGN KEY (account_id) REFERENCES account (account_id)
);


CREATE TABLE income_category
(
    income_category_id   BINARY(16)  NOT NULL,
    income_category_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (income_category_id)
);


CREATE TABLE income
(
    income_id                      BINARY(16)     NOT NULL,
    income_amount                  DECIMAL(19, 4) NOT NULL,
    income_date                    DATE           NOT NULL,
    income_transaction_description VARCHAR(128),
    income_category_id             BINARY(16),
    account_id                     BINARY(16),
    PRIMARY KEY (income_id),
    FOREIGN KEY (income_category_id) REFERENCES income_category (income_category_id),
    FOREIGN KEY (account_id) REFERENCES account (account_id)
);
