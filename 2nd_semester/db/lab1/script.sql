create table conditions (id_condition int primary key,
                         name_of_condition varchar(100));

create table professions (id_professions int primary key,
                          name_of_professions varchar(100));

create table crew_members (id_crew_members int primary key,
                           name varchar(100),
                           surname varchar(100),
                           age int,
                           condition int,
                           profession int,
                           FOREIGN KEY (condition) REFERENCES conditions (id_condition),
                           FOREIGN KEY (profession) REFERENCES professions (id_professions));

create table type_of_system (id_type_of_system int primary key,
                             name varchar(100));

create table type_of_dang (id_type_of_dang int primary key,
                           name varchar(100));

create table type_of_hp (id_type_of_hp int primary key,
                         name varchar(100));

create table systems (id_system int primary key,
                      type_of_system int,
                      dang_of_ast int,
                      hp int,
                      FOREIGN KEY (type_of_system) REFERENCES type_of_system (id_type_of_system),
                      FOREIGN KEY (dang_of_ast) REFERENCES type_of_dang (id_type_of_dang),
                      FOREIGN KEY (hp) REFERENCES type_of_hp (id_type_of_hp));

create table members_to_system (id_match int primary key,
                                id_member int,
                                id_system int,
                                FOREIGN KEY (id_member) REFERENCES crew_members (id_crew_members),
                                FOREIGN KEY (id_system) REFERENCES systems (id_system));

insert into conditions values (1, 'sleep');

insert into conditions values (2, 'awake');

insert into professions values (1, 'astronaut');

insert into professions values (2, 'electric');

insert into crew_members values (1, 'Ivan', 'Ivanov', 41, 1, 1);

insert into crew_members values (2, 'Egor', 'Egorov', 49, 2, 2);

insert into type_of_system values (1, 'Discovery');

insert into type_of_dang values (1, 'low');

insert into type_of_hp values (1, 'full');

insert into systems values (1, 1, 1, 1);

insert into members_to_system values (1, 1, 1);

insert into members_to_system values (2, 2, 1);
