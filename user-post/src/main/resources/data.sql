INSERT INTO users (user_name, email, mobile_number, date_of_birth)
VALUES ('Rohit', 'rohit@example.com', '9876543210', '1995-05-15');

INSERT INTO users (user_name, email, mobile_number, date_of_birth)
VALUES ('Amit', 'amit@example.com', '9123456789', '1997-08-20');

--initializing post data with some data to test service methods

INSERT INTO post (title, content, user_id)
VALUES
('Spring Boot Basics', 'Learn the basics of Spring Boot.', 1),
('Java Streams', 'Deep dive into Java Streams and functional programming.', 1),
('REST API Design', 'How to design REST APIs effectively.', 2),
('PostgreSQL Tips', 'Advanced queries and indexing in PostgreSQL.', 2),
('Unit Testing', 'Best practices for testing Spring Boot applications.', 1),
('Exception Handling', 'Global exception handling in Spring Boot.', 2);


