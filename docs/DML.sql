USE Wishlist;
INSERT INTO users (firstName, lastName, age, email, password) VALUES
                                                                  ('Alice', 'Smith', 25, 'alice.smith@example.com', 'password123'),
                                                                  ('Bob', 'Johnson', 32, 'bob.johnson@example.com', 'password456'),
                                                                  ('Catherine', 'Davis', 28, 'catherine.davis@example.com', 'password789'),
                                                                  ('David', 'Martinez', 35, 'david.martinez@example.com', 'password101'),
                                                                  ('Eva', 'Garcia', 22, 'eva.garcia@example.com', 'password202');

INSERT INTO wishlist (id, name) VALUES
                                    (2, 'Bob''s Wishlist'),
                                    (4, 'David''s Wishlist');

INSERT INTO items (wishlistId,name, description, price, link, imgUrl) VALUES
                                                                          (2,'Noise Cancelling Headphones', 'High-quality wireless headphones with noise cancellation', 299.99, 'http://example.com/headphones', 'http://example.com/image/headphones.jpg'),
                                                                          (2,'Smartwatch', 'Latest smartwatch with fitness tracking and notifications', 199.99, 'http://example.com/smartwatch', 'http://example.com/image/smartwatch.jpg'),
                                                                          (4,'Something', 'Latest smartwatch with fitness tracking and notifications', 199.99, 'http://example.com/smartwatch', 'http://example.com/image/smartwatch.jpg');
