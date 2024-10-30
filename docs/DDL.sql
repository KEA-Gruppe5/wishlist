CREATE DATABASE IF NOT EXISTS Wishlist;
USE Wishlist;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(25),
    lastName VARCHAR(255),
    age INT,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS wishlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    wishlistId int,
    name VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE,
    link VARCHAR(255),
    imgUrl VARCHAR(255),
    FOREIGN KEY (wishlistId) REFERENCES wishlist(id)
);
