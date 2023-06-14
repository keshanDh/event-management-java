-- eventDB

create table participant
(
    student_id int          not null,
    guest_name varchar(100) not null,
    program_id int          not null
);