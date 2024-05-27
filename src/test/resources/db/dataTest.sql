INSERT INTO roles (role_id, role_name)
VALUES (X'76e6a7e7ec0a4129b1c010b6c5aa0304', 'PremiumUser'),
       (X'aeead63a55b84f569c94855322fdefb9', 'FreeUser');

INSERT INTO authorities (authority_id, authority_name)
VALUES (X'a9872ecf4b23488faacb1e4e8e9cd8ea', 'deleting_expense_category');

INSERT INTO authority_role (authority_id, role_id)
VALUES (X'a9872ecf4b23488faacb1e4e8e9cd8ea', X'76e6a7e7ec0a4129b1c010b6c5aa0304');

INSERT INTO users_info (user_info_id, user_name, email, password, phone_number, role_id)
VALUES (X'456def23321445ec8c1a2ff297ae8f3d', 'johnsmith', 'john.smith@example.com', 'password123', '1234567890',
        X'76e6a7e7ec0a4129b1c010b6c5aa0304');

INSERT INTO users (user_id, first_name, last_name, date_of_birth, registration_date, user_info_id)
VALUES (X'12345678123456781234567812345678', 'John', 'Smith', '1990-01-01', '2020-01-01',
        X'456def23321445ec8c1a2ff297ae8f3d');

INSERT INTO accounts (account_id, account_name, balance, currency, user_info_id)
VALUES (X'888a5b08573c4f8396e8319ec975a111', 'Savings Account', 15000.00, 'USD', X'456def23321445ec8c1a2ff297ae8f3d'),
       (X'777a4b09473c4f8396e8319ec975a222', 'Checking Account', 3000.00, 'EUR', X'456def23321445ec8c1a2ff297ae8f3d');

INSERT INTO budget (budget_id, start_date, end_date, planned_income, planned_expenses, account_id)
VALUES (X'999a6b10683c4f8396e8319ec975a333', '2023-01-01', '2023-12-31', 20000.00, 5000.00,
        X'888a5b08573c4f8396e8319ec975a111');

INSERT INTO expense_category (expense_category_id, expense_category_name)
VALUES (X'111a7b11793c4f8396e8319ec975a444', 'Groceries'),
       (X'222a8c12813c4f8396e8319ec975a555', 'Utilities'),
       (X'333a8c12812c4f8396e8319ec975a666', 'Transport'),
       (X'444a8c12283c4f8396e8319ec975a777', 'Housing'),
       (X'555a8c12433c4f8396e8319ec975a888', 'Health'),
       (X'666a8c12533c4f8396e8319ec975a999', 'Personal Expenses'),
       (X'777a8c12113c4f8396e8319ec975a000', 'Education'),
       (X'888a8c12841c4f8396e8319ec975a111', 'Entertainment');

INSERT INTO expense (expense_id, expense_amount, expense_date, expense_transaction_description, expense_category_id,
                     account_id)
VALUES (X'333a9d13833c4f8396e8319ec975a666', 150.00, '2023-01-15', 'Walmart Purchase',
        X'111a7b11793c4f8396e8319ec975a444', X'888a5b08573c4f8396e8319ec975a111');

INSERT INTO income_category (income_category_id, income_category_name)
VALUES (X'444baf24843c4f8396e8319ec975a777', 'Salary'),
       (X'555cbf25853c4f8396e8319ec975a888', 'Gifts');

INSERT INTO income (income_id, income_amount, income_date, income_transaction_description, income_category_id,
                    account_id)
VALUES (X'666dcf26863c4f8396e8319ec975a999', 3000.00, '2023-01-31', 'Monthly Salary',
        X'444baf24843c4f8396e8319ec975a777', X'888a5b08573c4f8396e8319ec975a111');
