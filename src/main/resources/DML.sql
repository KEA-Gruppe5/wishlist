INSERT INTO users (first_name, last_name, age, email, password) VALUES
                                                                  ('Alice', 'Smith', 25, 'alice.smith@example.com', 'password123'),
                                                                  ('Bob', 'Johnson', 32, 'bob.johnson@example.com', 'password456'),
                                                                  ('Catherine', 'Davis', 28, 'catherine.davis@example.com', 'password789'),
                                                                  ('David', 'Martinez', 35, 'david.martinez@example.com', 'password101'),
                                                                  ('Eva', 'Garcia', 22, 'eva.garcia@example.com', 'password202');

INSERT INTO wishlists (user_id, name) VALUES
                                         (2, 'Bobs Wishlist'),
                                         (4, 'Davids Wishlist'),
                                         (4, 'David''s 2nd wishlist');

INSERT INTO items (wishlist_id,name, description, price, link, img_url) VALUES
                                                                          (1,'Noise Cancelling Headphones', 'High-quality wireless headphones with noise cancellation', 299.99, 'http://example.com/headphones', 'http://example.com/image/headphones.jpg'),
                                                                          (1,'Smartwatch', 'Latest smartwatch with fitness tracking and notifications', 199.99, 'http://example.com/smartwatch', 'http://example.com/image/smartwatch.jpg'),
                                                                          (2,'Something', 'Latest smartwatch with fitness tracking and notifications', 199.99, 'http://example.com/smartwatch', 'http://example.com/image/smartwatch.jpg');