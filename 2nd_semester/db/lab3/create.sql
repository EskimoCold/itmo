-- Внешне на "Дискавери" ничего не изменилось. Все системы работали нормально, карусель медленно вращалась на своей оси,
-- создавая искусственное тяготение, спящие члены экипажа покоились без сновидений в своих саркофагах,
-- а корабль летел навстречу цели, от которой его ничто не могло отклонить, если не считать невообразимо
-- малой вероятности столкновения с астероидом. За пределами орбиты Юпитера действительно было очень мало астероидов...


create table conditions (id_condition serial primary key,
                         name_of_condition varchar(100),
                        stated_at date);

create table professions (id_professions serial primary key,
                          name_of_professions varchar(100),
                        difficulty integer,
                        salary float);

create table crew_members (id_crew_members serial primary key,
                           name varchar(100),
                           surname varchar(100),
                           birthdate date,
                           condition int,
                           profession int,
                           FOREIGN KEY (condition) REFERENCES conditions (id_condition),
                           FOREIGN KEY (profession) REFERENCES professions (id_professions));

create table type_of_system (id_type_of_system serial primary key,
                             name varchar(100),
                            version integer);

create table type_of_dang (id_type_of_dang serial primary key,
                           name varchar(100),
                            level integer);

create table type_of_hp (id_type_of_hp serial primary key,
                         name varchar(100),
                        percentage float);

create table systems (id_system serial primary key,
                      type_of_system int,
                      dang_of_ast int,
                      hp int,
                      built_at date,
                      reconstructed_at date,
                      FOREIGN KEY (type_of_system) REFERENCES type_of_system (id_type_of_system),
                      FOREIGN KEY (dang_of_ast) REFERENCES type_of_dang (id_type_of_dang),
                      FOREIGN KEY (hp) REFERENCES type_of_hp (id_type_of_hp));

create table members_to_system (id_match serial primary key,
                                id_member int,
                                id_system int,
                                FOREIGN KEY (id_member) REFERENCES crew_members (id_crew_members),
                                FOREIGN KEY (id_system) REFERENCES systems (id_system));
