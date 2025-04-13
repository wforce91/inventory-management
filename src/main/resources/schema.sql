DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

-- Sample data
INSERT INTO products (name, quantity) VALUES 
('Laptop', 10),
('Monitor', 15),
('Keyboard', 30),
('Mouse', 50),
('USB Drive', 100);
