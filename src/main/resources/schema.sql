drop table employee;
drop table department;

create table if not exists department
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null,
    constraint UNIQUE_DEPARTMENT_NAME
        unique (name)
);


create table if not exists employee
(
    id               bigint auto_increment
        primary key,
    salary           bigint       not null,
    name             varchar(255) not null,
    fk_department_id bigint       not null,
    constraint FK_EMPLOYEE_DEPARTMENT_ID
        foreign key (fk_department_id) references department (id)
);


