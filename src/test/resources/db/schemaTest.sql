

DROP TABLE IF EXISTS users_info, users, accounts, budget, expense, income, expense_category, income_category, authority_role, roles, authorities;


CREATE TABLE roles
(
    role_id   BINARY(16)  NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (role_id)
);


CREATE TABLE authorities
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
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);


CREATE TABLE users
(
    user_id           BINARY(16)  NOT NULL,
    first_name        VARCHAR(50) NOT NULL,
    last_name         VARCHAR(50) NOT NULL,
    date_of_birth     DATE        NOT NULL,
    registration_date DATE        NOT NULL,
    user_info_id      BINARY(16) UNIQUE,
    PRIMARY KEY (user_id)
);


CREATE TABLE users_info
(
    user_info_id BINARY(16)  NOT NULL,
    user_name    VARCHAR(50) NOT NULL,
    email        VARCHAR(50) NOT NULL,
    password     VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50),
    role_id      BINARY(16),
    PRIMARY KEY (user_info_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);


CREATE TABLE accounts
(
    account_id   BINARY(16)                                                                                                                   NOT NULL,
    account_name VARCHAR(50)                                                                                                                  NOT NULL,
    balance      DECIMAL(19, 4)                                                                                                               NOT NULL,
    currency     ENUM ('USD', 'EUR', 'JPY', 'GBP', 'AUD', 'PLN', 'UAH', 'CAD', 'CNY', 'CHF', 'INR', 'BRL', 'SEK', 'NZD', 'SGD', 'MXN', 'ZAR') NOT NULL,
    budget_id    BINARY(16) UNIQUE,
    user_info_id BINARY(16),
    PRIMARY KEY (account_id),
    FOREIGN KEY (user_info_id) REFERENCES users_info (user_info_id)
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
    FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);

ALTER TABLE budget
    ADD CONSTRAINT FK_account_budget
        FOREIGN KEY (account_id)
            REFERENCES accounts(account_id)
            ON DELETE CASCADE;

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
    FOREIGN KEY (account_id) REFERENCES accounts (account_id)
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
    FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);
