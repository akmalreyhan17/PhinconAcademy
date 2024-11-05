-- Create Car Table
CREATE TABLE Car (
    id SERIAL PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

-- Create Customer Table
CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
	credit_score INT
);

-- Create Sales Table
CREATE TABLE Sales (
    id SERIAL PRIMARY KEY,
    car_id INT NOT NULL,
    customer_id INT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    FOREIGN KEY (car_id) REFERENCES Car(id),
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);
