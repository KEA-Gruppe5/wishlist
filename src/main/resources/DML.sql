INSERT INTO users (first_name, last_name, age, email, password, is_enabled) VALUES
                                                                  ('Alice', 'Smith', 25, 'alice.smith@example.com', 'password123', true),
                                                                  ('Bob', 'Johnson', 32, 'bob.johnson@example.com', 'password456', true),
                                                                  ('Catherine', 'Davis', 28, 'catherine.davis@example.com', 'password789', true),
                                                                  ('David', 'Martinez', 35, 'david.martinez@example.com', 'password101', true),
                                                                  ('Eva', 'Garcia', 22, 'eva.garcia@example.com', 'password202', true);

INSERT INTO wishlists (user_id, name) VALUES
                                         (2, 'Bobs Wishlist'),
                                         (4, 'Davids Wishlist'),
                                         (4, 'David''s 2nd wishlist');

INSERT INTO items (wishlist_id,name, description, price, link, img_url, reserve_item) VALUES
                                                                          (1,'Noise Cancelling Headphones', 'High-quality wireless headphones with noise cancellation', 299.99, 'http://example.com/headphones', 'http://example.com/image/headphones.jpg', false),
                                                                          (1,'Smartwatch', 'Latest smartwatch with fitness tracking and notifications', 199.99, 'http://example.com/smartwatch', 'http://example.com/image/smartwatch.jpg', true),
                                                                          (2,'Something', 'Latest smartwatch with fitness tracking and notifications', 199.99, 'http://example.com/smartwatch', 'http://example.com/image/smartwatch.jpg', false);
