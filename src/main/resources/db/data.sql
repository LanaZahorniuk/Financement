INSERT INTO currency (currency_code, symbol)
VALUES ('USD', '$'),
       ('EUR', '€'),
       ('JPY', '¥'),
       ('GBP', '£'),
       ('AUD', 'A$'),
       ('PLN', 'zł'),
       ('UAH', '₴'),
       ('CAD', 'C$'),
       ('CNY', '¥'),
       ('CHF', 'CHF'),
       ('INR', '₹'),
       ('BRL', 'R$'),
       ('SEK', 'kr'),
       ('NZD', 'NZ$'),
       ('SGD', 'S$'),
       ('MXN', '$'),
       ('ZAR', 'R');

INSERT INTO expense_category (expense_category_id, expense_category_name)
VALUES (UUID_TO_BIN(UUID()), 'Food'),
       (UUID_TO_BIN(UUID()), 'Transportation');

INSERT INTO income_category (income_category_id, income_category_name)
VALUES (UUID_TO_BIN(UUID()), 'Salary'),
       (UUID_TO_BIN(UUID()), 'Gifts');

INSERT INTO roles (role_id, role_name)
VALUES (UUID_TO_BIN(UUID()), 'PremiumUser'),
       (UUID_TO_BIN(UUID()), 'FreeUser');

INSERT INTO authorities (authority_id, authority_name)
VALUES (UUID_TO_BIN(UUID()), 'CreateCategory'),
       (UUID_TO_BIN(UUID()), 'CreateBudget'),
       (UUID_TO_BIN(UUID()), 'AddUnlimitedAccounts');

INSERT INTO authority_role (authority_id, role_id)
VALUES ((SELECT authority_id FROM authorities WHERE authority_name = 'CreateCategory'),
        (SELECT role_id FROM roles WHERE role_name = 'PremiumUser')),
       ((SELECT authority_id FROM authorities WHERE authority_name = 'CreateBudget'),
        (SELECT role_id FROM roles WHERE role_name = 'PremiumUser')),
       ((SELECT authority_id FROM authorities WHERE authority_name = 'AddUnlimitedAccounts'),
        (SELECT role_id FROM roles WHERE role_name = 'PremiumUser'));

INSERT INTO users (user_id, first_name, last_name, date_of_birth, registration_date, user_info_id)
VALUES (UUID_TO_BIN(UUID()), 'John', 'Doe', '1990-01-01', '2022-01-01', UUID_TO_BIN(UUID())),
       (UUID_TO_BIN(UUID()), 'Jane', 'Doe', '1995-02-01', '2022-02-01', UUID_TO_BIN(UUID()));


INSERT INTO users_info (user_info_id, user_name, email, password, phone_number, user_id, role_id)
VALUES (UUID_TO_BIN(UUID()), 'johndoe', 'john@example.com', 'password123', '1234567890',
        (SELECT user_id FROM users WHERE first_name = 'John'),
        (SELECT role_id FROM roles WHERE role_name = 'PremiumUser')),
       (UUID_TO_BIN(UUID()), 'janedoe', 'jane@example.com', 'password123', '0987654321',
        (SELECT user_id FROM users WHERE first_name = 'Jane'),
        (SELECT role_id FROM roles WHERE role_name = 'FreeUser'));


INSERT INTO accounts (account_id, account_name, balance, currency_code, user_info_id)
VALUES (UUID_TO_BIN(UUID()), 'Checking Account', 1500.00, 'USD',
        (SELECT user_info_id FROM users_info WHERE user_name = 'johndoe')),
       (UUID_TO_BIN(UUID()), 'Savings Account', 3000.00, 'EUR',
        (SELECT user_info_id FROM users_info WHERE user_name = 'janedoe'));


INSERT INTO budget (budget_id, start_date, end_date, planned_income, planned_expenses, account_id)
VALUES (UUID_TO_BIN(UUID()), '2023-01-01', '2023-12-31', 12000.00, 11000.00,
        (SELECT account_id FROM accounts WHERE account_name = 'Checking Account'));


INSERT INTO expense (expense_id, expense_amount, expense_date, expense_transaction_description, expense_category_id,
                     account_id)
VALUES (UUID_TO_BIN(UUID()), 200.00, '2023-04-01', 'Groceries',
        (SELECT expense_category_id FROM expense_category WHERE expense_category_name = 'Food'),
        (SELECT account_id FROM accounts WHERE account_name = 'Checking Account'));

INSERT INTO income (income_id, income_amount, income_date, income_transaction_description, income_category_id,
                    account_id)
VALUES (UUID_TO_BIN(UUID()), 500.00, '2023-04-03', 'Salary',
        (SELECT income_category_id FROM income_category WHERE income_category_name = 'Salary'),
        (SELECT account_id FROM accounts WHERE account_name = 'Checking Account'));

