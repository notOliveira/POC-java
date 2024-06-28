-- Tabela Teacher
CREATE TABLE Teacher (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela Student
CREATE TABLE Student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela Classroom
CREATE TABLE Classroom (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    teacher_id BIGINT,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(id)
);

-- Tabela para relacionar Classroom e Student (relacionamento muitos-para-muitos)
CREATE TABLE Classroom_Student (
    classroom_id BIGINT,
    student_id BIGINT,
    PRIMARY KEY (classroom_id, student_id),
    FOREIGN KEY (classroom_id) REFERENCES Classroom(id),
    FOREIGN KEY (student_id) REFERENCES Student(id)
);

-- INSERTS

-- Inserir professores
INSERT INTO Teacher (name, email) VALUES ('Professor A', 'prof.a@example.com');
INSERT INTO Teacher (name, email) VALUES ('Professor B', 'prof.b@example.com');
INSERT INTO Teacher (name, email) VALUES ('Professor C', 'prof.c@example.com');

-- Inserir estudantes
INSERT INTO Student (name, email) VALUES ('Student A', 'student.a@example.com');
INSERT INTO Student (name, email) VALUES ('Student B', 'student.b@example.com');
INSERT INTO Student (name, email) VALUES ('Student C', 'student.c@example.com');
INSERT INTO Student (name, email) VALUES ('Student D', 'student.d@example.com');
INSERT INTO Student (name, email) VALUES ('Student E', 'student.e@example.com');
INSERT INTO Student (name, email) VALUES ('Student F', 'student.f@example.com');
INSERT INTO Student (name, email) VALUES ('Student G', 'student.g@example.com');
INSERT INTO Student (name, email) VALUES ('Student H', 'student.h@example.com');
INSERT INTO Student (name, email) VALUES ('Student I', 'student.i@example.com');
INSERT INTO Student (name, email) VALUES ('Student J', 'student.j@example.com');
INSERT INTO Student (name, email) VALUES ('Student K', 'student.k@example.com');
INSERT INTO Student (name, email) VALUES ('Student L', 'student.l@example.com');


-- Inserir sala de aula
INSERT INTO Classroom (name, teacher_id) VALUES ('Math', 1);

INSERT INTO Classroom (name, teacher_id) VALUES ('Geography', 2);
INSERT INTO Classroom (name, teacher_id) VALUES ('Chemical', 2);

INSERT INTO Classroom (name, teacher_id) VALUES ('History', 3);
INSERT INTO Classroom (name, teacher_id) VALUES ('English', 3);
INSERT INTO Classroom (name, teacher_id) VALUES ('Physics', 3);

-- Relacionar estudantes à sala
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (1, 1);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (1, 2);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (2, 3);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (2, 4);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (3, 5);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (3, 6);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (4, 1);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (4, 2);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (5, 3);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (5, 4);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (6, 5);
INSERT INTO Classroom_Student (classroom_id, student_id) VALUES (6, 6);

