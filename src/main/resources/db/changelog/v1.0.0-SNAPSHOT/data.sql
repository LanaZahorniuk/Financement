INSERT INTO roles (role_id, role_name)
VALUES (UNHEX(REPLACE('76e6a7e7-ec0a-4129-b1c0-10b6c5aa0304', '-', '')), 'ROLE_PremiumUser'),
       (UNHEX(REPLACE('aeead63a-55b8-4f56-9c94-855322fdefb9', '-', '')), 'ROLE_FreeUser');


INSERT INTO authorities (authority_id, authority_name)
VALUES (UNHEX(REPLACE('a9872ecf-4b23-488f-aacb-1e4e8e9cd8ea', '-', '')), 'deleting_expense_category');

INSERT INTO authority_role (authority_id, role_id)
VALUES
-- PremiumUser:
(UNHEX(REPLACE('a9872ecf-4b23-488f-aacb-1e4e8e9cd8ea', '-', '')),
 UNHEX(REPLACE('76e6a7e7-ec0a-4129-b1c0-10b6c5aa0304', '-', '')));

-- FreeUser:


INSERT INTO users_info (user_info_id, user_name, email, password, phone_number, role_id)
VALUES (UNHEX(REPLACE('456def23-3214-45ec-8c1a-2ff297ae8f3d', '-', '')), 'johnsmith', 'john.smith@example.com',
        '$2y$10$Rh3cV7Suf6rg7V.qEhBE5OT.7aOfo32b.ox.W1myl83K/fO/fVvia', '1234567890', UNHEX(REPLACE('76e6a7e7-ec0a-4129-b1c0-10b6c5aa0304', '-', '')));


INSERT INTO users (user_id, first_name, last_name, date_of_birth, registration_date, user_info_id)
VALUES (UNHEX(REPLACE('12345678-1234-5678-1234-567812345678', '-', '')), 'John', 'Smith', '1990-01-01', '2020-01-01',
        UNHEX(REPLACE('456def23-3214-45ec-8c1a-2ff297ae8f3d', '-', '')));


INSERT INTO accounts (account_id, account_name, balance, currency, user_info_id)
VALUES (UNHEX(REPLACE('888a5b08-573c-4f83-96e8-319ec975a111', '-', '')), 'Salary', 15000.00, 'USD',
        UNHEX(REPLACE('456def23-3214-45ec-8c1a-2ff297ae8f3d', '-', ''))),
       (UNHEX(REPLACE('777a4b09-473c-4f83-96e8-319ec975a222', '-', '')), 'Cash', 3000.00, 'EUR',
        UNHEX(REPLACE('456def23-3214-45ec-8c1a-2ff297ae8f3d', '-', '')));


INSERT INTO budget (budget_id, start_date, end_date, planned_income, planned_expenses, account_id)
VALUES (UNHEX(REPLACE('999a6b10-683c-4f83-96e8-319ec975a333', '-', '')), '2023-01-01', '2023-12-31', 20000.00, 5000.00,
        UNHEX(REPLACE('888a5b08-573c-4f83-96e8-319ec975a111', '-', '')));


INSERT INTO expense_category (expense_category_id, expense_category_name)
VALUES (UNHEX(REPLACE('111a7b11-793c-4f83-96e8-319ec975a444', '-', '')), 'Groceries'),
       (UNHEX(REPLACE('222a8c12-813c-4f83-96e8-319ec975a555', '-', '')), 'Utilities'),
       (UNHEX(REPLACE('333a8c12-812c-4f83-96e8-319ec975a666', '-', '')), 'Transport'),
       (UNHEX(REPLACE('444a8c12-283c-4f83-96e8-319ec975a777', '-', '')), 'Housing'),
       (UNHEX(REPLACE('555a8c12-433c-4f83-96e8-319ec975a888', '-', '')), 'Health'),
       (UNHEX(REPLACE('666a8c12-533c-4f83-96e8-319ec975a999', '-', '')), 'Personal Expenses'),
       (UNHEX(REPLACE('777a8c12-113c-4f83-96e8-319ec975a000', '-', '')), 'Education'),
       (UNHEX(REPLACE('888a8c12-841c-4f83-96e8-319ec975a111', '-', '')), 'Entertainment');


INSERT INTO expense (expense_id, expense_amount, expense_date, expense_transaction_description, expense_category_id,
                     account_id)
VALUES (UNHEX(REPLACE('333a9d13-833c-4f83-96e8-319ec975a666', '-', '')), 150.00, '2023-01-15', 'Walmart Purchase',
        UNHEX(REPLACE('111a7b11-793c-4f83-96e8-319ec975a444', '-', '')),
        UNHEX(REPLACE('888a5b08-573c-4f83-96e8-319ec975a111', '-', '')));


INSERT INTO income_category (income_category_id, income_category_name)
VALUES (UNHEX(REPLACE('444baf24-843c-4f83-96e8-319ec975a777', '-', '')), 'Salary'),
       (UNHEX(REPLACE('555cbf25-853c-4f83-96e8-319ec975a888', '-', '')), 'Gifts');


INSERT INTO income (income_id, income_amount, income_date, income_transaction_description, income_category_id,
                    account_id)
VALUES (UNHEX(REPLACE('666dcf26-863c-4f83-96e8-319ec975a999', '-', '')), 3000.00, '2023-01-31', 'Monthly Salary',
        UNHEX(REPLACE('444baf24-843c-4f83-96e8-319ec975a777', '-', '')),
        UNHEX(REPLACE('888a5b08-573c-4f83-96e8-319ec975a111', '-', '')));
