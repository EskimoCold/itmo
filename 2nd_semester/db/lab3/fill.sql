insert into conditions values (DEFAULT, 'sleep', current_date);

insert into conditions values (DEFAULT, 'awake', current_date);

insert into professions values (DEFAULT, 'astronaut', 10, 5300);

insert into professions values (DEFAULT, 'electric', 7, 4000);

insert into crew_members values (DEFAULT, 'Ivan', 'Ivanov', '1995-06-23'::date, 1, 1);

insert into crew_members values (DEFAULT, 'Egor', 'Egorov', '1990-01-20'::date, 2, 2);

insert into type_of_system values (DEFAULT, 'Discovery', 2);

insert into type_of_dang values (DEFAULT, 'low', 2);

insert into type_of_hp values (DEFAULT, 'full', 1);

insert into systems values (DEFAULT, 1, 1, 1, '2020-01-23'::date, '2020-01-23'::date);

insert into members_to_system values (DEFAULT, 1, 1);

insert into members_to_system values (DEFAULT, 2, 1);
