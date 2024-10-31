CREATE DATABASE Wishlist;
USE Wishlist;

CREATE TABLE  users (
                        userId INT AUTO_INCREMENT PRIMARY KEY,
                        firstName VARCHAR(25),
                        lastName VARCHAR(255),
                        age INT,
                        email VARCHAR(255) UNIQUE,
                        password VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wishlists (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           userId INT,
                           name VARCHAR(255),
                           FOREIGN KEY (userId) REFERENCES users(userId) ON DELETE CASCADE
);

CREATE TABLE items (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       wishlistId INT,
                       name VARCHAR(255),
                       description VARCHAR(255),
                       price DOUBLE,
                       link VARCHAR(255),
                       imgUrl VARCHAR(255),
                       FOREIGN KEY (wishlistId) REFERENCES wishlists(id) ON DELETE CASCADE
);
