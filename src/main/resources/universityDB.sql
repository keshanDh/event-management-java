-- universityDB

create table if not exists program
(
    program_id                   int auto_increment primary key,
    program_name                 varchar(100) not null,
    program_responsible_staff_id int          not null
);

INSERT INTO universitydb.program (program_id, program_name, program_responsible_staff_id) VALUES (1, 'Engineering', 1);
INSERT INTO universitydb.program (program_id, program_name, program_responsible_staff_id) VALUES (2, 'Management', 2);
INSERT INTO universitydb.program (program_id, program_name, program_responsible_staff_id) VALUES (3, 'Medicine', 3);
INSERT INTO universitydb.program (program_id, program_name, program_responsible_staff_id) VALUES (4, 'Arts', 4);


create table if not exists student
(
    student_id         int auto_increment primary key,
    student_name       varchar(100) not null,
    student_program_id int          not null
);

INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (1, 'Alan', 1);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (2, 'Brian', 1);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (3, 'Claire', 2);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (4, 'Daisy', 2);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (5, 'Erick', 2);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (6, 'Freddy', 3);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (7, 'Greg', 3);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (8, 'Hailey', 3);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (9, 'Ian', 4);
INSERT INTO universitydb.student (student_id, student_name, student_program_id) VALUES (10, 'John', 4);


create table if not exists staff
(
    staff_id         int auto_increment primary key,
    staff_name       varchar(100) not null,
    staff_program_id int          not null
);

INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (1, 'Ken', 1);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (2, 'Linda', 1);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (3, 'Mickey', 2);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (4, 'Nelly', 2);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (5, 'Obama', 3);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (6, 'Patrick', 3);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (7, 'Quincy', 4);
INSERT INTO universitydb.staff (staff_id, staff_name, staff_program_id) VALUES (8, 'Rihanna', 4);


create table if not exists program_responsibles
(
    pr_id        int auto_increment primary key,
    pr_name      varchar(100) not null,
    pr_progam_id int          not null
);

INSERT INTO universitydb.program_responsibles (pr_id, pr_name, pr_progam_id) VALUES (1, 'Stewart', 1);
INSERT INTO universitydb.program_responsibles (pr_id, pr_name, pr_progam_id) VALUES (2, 'Tom', 2);
INSERT INTO universitydb.program_responsibles (pr_id, pr_name, pr_progam_id) VALUES (3, 'Uma', 3);
INSERT INTO universitydb.program_responsibles (pr_id, pr_name, pr_progam_id) VALUES (4, 'Victor', 4);
