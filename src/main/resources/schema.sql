CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO student (name, email) VALUES ('Student 1', 'student1@example.com');
INSERT INTO student (name, email) VALUES ('Student 2', 'student2@example.com');
INSERT INTO student (name, email) VALUES ('Student 3', 'student3@example.com');
