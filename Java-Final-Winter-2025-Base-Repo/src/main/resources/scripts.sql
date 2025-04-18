-- Drop existing tables if they exist to prevent conflicts
DROP TABLE IF EXISTS workout_classes;
DROP TABLE IF EXISTS memberships;
DROP TABLE IF EXISTS users;

-- === USERS TABLE ===
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    user_password TEXT NOT NULL,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_phone VARCHAR(20),
    user_address TEXT,
    user_role VARCHAR(20) NOT NULL CHECK (user_role IN ('Admin', 'Trainer', 'Member')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- === MEMBERSHIPS TABLE ===
CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50) NOT NULL,
    membership_price DECIMAL(10, 2) NOT NULL,
    membership_description TEXT,
    date_purchased DATE DEFAULT CURRENT_DATE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- === WORKOUT CLASSES TABLE ===
CREATE TABLE workout_classes (
    workout_class_id SERIAL PRIMARY KEY,
    workout_class_type VARCHAR(50) NOT NULL,
    workout_class_description TEXT,
    trainer_id INT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- === INDEXES FOR PERFORMANCE (OPTIONAL) ===
CREATE INDEX idx_membership_user_id ON memberships(user_id);
CREATE INDEX idx_class_trainer_id ON workout_classes(trainer_id);

-- === REPORT: Monthly Revenue from Memberships ===
-- Use this to get monthly revenue totals
-- Example Usage: For Admin dashboard
SELECT
    TO_CHAR(date_purchased, 'YYYY-MM') AS month,
    SUM(membership_price) AS total_revenue
FROM memberships
GROUP BY month
ORDER BY month;

-- === OPTIONAL SAMPLE INSERTS (WITH PLACEHOLDER PASSWORDS) ===
-- Passwords should be hashed using BCrypt in production

-- INSERT INTO users (username, user_password, user_email, user_phone, user_address, user_role)
-- VALUES
-- ('admin1', '$2a$10$replace_with_hash', 'admin@example.com', '1234567890', '123 Admin St', 'Admin'),
-- ('trainer1', '$2a$10$replace_with_hash', 'trainer@example.com', '2345678901', '456 Trainer Rd', 'Trainer'),
-- ('member1', '$2a$10$replace_with_hash', 'member@example.com', '3456789012', '789 Member Ave', 'Member');
