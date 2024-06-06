INSERT INTO roles (role_id, role_name)
VALUES (CAST('76e6a7e7-ec0a-4129-b1c0-10b6c5aa0304' as binary(16)), 'PremiumUser'),
       (CAST('aeead63a-55b8-4f56-9c94-855322fdefb9' as binary(16)), 'FreeUser');


INSERT INTO authorities (authority_id, authority_name)
VALUES (CAST('a9872ecf-4b23-488f-aacb-1e4e8e9cd8ea' as binary(16)), 'deleting_expense_category');

INSERT INTO authority_role (authority_id, role_id)
VALUES
-- PremiumUser:
(CAST('a9872ecf-4b23-488f-aacb-1e4e8e9cd8ea' as binary(16)),
 CAST('76e6a7e7-ec0a-4129-b1c0-10b6c5aa0304' as binary(16)));

-- FreeUser:


INSERT INTO users_info (user_info_id, user_name, email, password, phone_number, role_id)
VALUES (CAST('456def23-3214-45ec-8c1a-2ff297ae8f3d' as binary(16)), 'johnsmith', 'john.smith@example.com',
        'password123', '1234567890', CAST('76e6a7e7-ec0a-4129-b1c0-10b6c5aa0304' as binary(16)));


INSERT INTO users (user_id, first_name, last_name, date_of_birth, registration_date, user_info_id)
VALUES (CAST('12345678-1234-5678-1234-567812345678' as binary(16)), 'John', 'Smith', '1990-01-01', '2020-01-01',
        CAST('456def23-3214-45ec-8c1a-2ff297ae8f3d' as binary(16)));


INSERT INTO accounts (account_id, account_name, balance, currency, user_info_id)
VALUES (CAST('888a5b08-573c-4f83-96e8-319ec975a111' as binary(16)), 'Savings Account', 15000.00, 'USD',
        CAST('456def23-3214-45ec-8c1a-2ff297ae8f3d' as binary(16))),
       (CAST('777a4b09-473c-4f83-96e8-319ec975a222' as binary(16)), 'Checking Account', 3000.00, 'EUR',
        CAST('456def23-3214-45ec-8c1a-2ff297ae8f3d' as binary(16)));


INSERT INTO budget (budget_id, start_date, end_date, planned_income, planned_expenses, account_id)
VALUES (CAST('999a6b10-683c-4f83-96e8-319ec975a333' as binary(16)), '2023-01-01', '2023-12-31', 20000.00, 5000.00,
        CAST('888a5b08-573c-4f83-96e8-319ec975a111' as binary(16)));


INSERT INTO expense_category (expense_category_id, expense_category_name)
VALUES (CAST('111a7b11-793c-4f83-96e8-319ec975a444' as binary(16)), 'Groceries'),
       (CAST('222a8c12-813c-4f83-96e8-319ec975a555' as binary(16)), 'Utilities'),
       (CAST('333a8c12-812c-4f83-96e8-319ec975a666' as binary(16)), 'Transport'),
       (CAST('444a8c12-283c-4f83-96e8-319ec975a777' as binary(16)), 'Housing'),
       (CAST('555a8c12-433c-4f83-96e8-319ec975a888' as binary(16)), 'Health'),
       (CAST('666a8c12-533c-4f83-96e8-319ec975a999' as binary(16)), 'Personal Expenses'),
       (CAST('777a8c12-113c-4f83-96e8-319ec975a000' as binary(16)), 'Education'),
       (CAST('888a8c12-841c-4f83-96e8-319ec975a111' as binary(16)), 'Entertainment');


INSERT INTO expense (expense_id, expense_amount, expense_date, expense_transaction_description, expense_category_id,
                     account_id)
VALUES (CAST('333a9d13-833c-4f83-96e8-319ec975a666' as binary(16)), 150.00, '2023-01-15', 'Walmart Purchase',
        CAST('111a7b11-793c-4f83-96e8-319ec975a444' as binary(16)),
        CAST('888a5b08-573c-4f83-96e8-319ec975a111' as binary(16)));


INSERT INTO income_category (income_category_id, income_category_name)
VALUES (CAST('444baf24-843c-4f83-96e8-319ec975a777' as binary(16)), 'Salary'),
       (CAST('555cbf25-853c-4f83-96e8-319ec975a888' as binary(16)), 'Gifts');


INSERT INTO income (income_id, income_amount, income_date, income_transaction_description, income_category_id,
                    account_id)
VALUES (CAST('666dcf26-863c-4f83-96e8-319ec975a999' as binary(16)), 3000.00, '2023-01-31', 'Monthly Salary',
        CAST('444baf24-843c-4f83-96e8-319ec975a777' as binary(16)),
        CAST('888a5b08-573c-4f83-96e8-319ec975a111' as binary(16)));
