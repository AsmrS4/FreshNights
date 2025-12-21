INSERT INTO users(id, first_name, last_name, email, role, hash_password, is_blocked, account_create_time)
VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'John', 'Doe', 'john.doe@example.com', 'USER', 'password123', false, '2023-10-01 12:00:00'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Jane', 'Smith', 'jane.smith@example.com', 'ADMIN', 'password123', false, '2023-10-02 13:00:00'),
('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'Alice', 'Johnson', 'alice.johnson@example.com', 'USER', 'password123', true, '2023-10-03 14:00:00');