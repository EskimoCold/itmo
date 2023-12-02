import sqlite3
from sqlite3 import Error


create_database_query = "CREATE DATABASE university"

create_students_table = """
CREATE TABLE IF NOT EXISTS students (
    isu INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    gender TEXT NOT NULL
);
"""

create_ege_table = """
CREATE TABLE IF NOT EXISTS ege(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    russian INTEGER NOT NULL,
    math INTEGER NOT NULL,
    informatics INTEGER NOT NULL,
    student_isu INTEGER NOT NULL,
    FOREIGN KEY (student_isu) REFERENCES students (isu)
);
"""

create_programming_table = """
CREATE TABLE IF NOT EXISTS programming (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    lab1 FLOAT,
    lab2 FLOAT,
    lab3 FLOAT,
    lab4 FLOAT,
    test FLOAT,
    student_isu INTEGER NOT NULL,
    FOREIGN KEY (student_isu) REFERENCES students (isu)
);
"""

create_opd_table = """
CREATE TABLE IF NOT EXISTS opd (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    lab1 FLOAT,
    lab2 FLOAT,
    test FLOAT,
    student_isu INTEGER NOT NULL,
    FOREIGN KEY (student_isu) REFERENCES students (isu)
);
"""

create_students = """
INSERT INTO
    students (name, age, gender)
VALUES
    ('Egor', 18, 'male'),
    ('Maria', 18, 'female'),
    ('Petr', 18, 'male'),
    ('Edward', 17, 'male'),
    ('Natasha', 21, 'female');
"""

create_ege = """
INSERT INTO
    ege (russian, math, informatics, student_isu)
VALUES
    (71, 66, 87, 1),
    (78, 89, 64, 2),
    (98, 96, 97, 3),
    (75, 84, 93, 4),
    (86, 77, 100, 5);
"""

create_programming = """
INSERT INTO
    programming (lab1, lab2, lab3, lab4, test, student_isu)
VALUES
    (10, 9, 8, 9, 10, 1),
    (7, 10, 8, 7, 14, 2),
    (2, 5, 8, 10, 18, 3),
    (10, 10, 10, 10, 20, 4),
    (8, 9, 6, 10, 15, 5);
"""

create_opd = """
INSERT INTO
    opd (lab1, lab2, test, student_isu)
VALUES
    (9, 8, 10, 1),
    (6, 10, 14, 2),
    (3, 6, 18, 3),
    (10, 10, 20, 4),
    (7, 9, 15, 5);
"""

select_students = "SELECT * from students"

select_ege = "SELECT * FROM ege"

select_students_ege = """
SELECT
    students.isu,
    students.name,
    ege.russian,
    ege.math,
    ege.informatics
FROM ege
    INNER JOIN students ON students.isu = ege.student_isu
"""

select_students_programming_opd = """
SELECT
    programming.test,
    opd.test,
    name
FROM students
    INNER JOIN programming ON programming.student_isu = students.isu
    INNER JOIN opd ON opd.student_isu = students.isu
"""

select_math_over_70 = """
SELECT name
FROM students
WHERE isu IN (SELECT student_isu FROM ege WHERE math > 90);
"""

select_good_prog_labs = """
SELECT s.name, s.age
FROM students s
WHERE s.isu IN (
    SELECT p.student_isu
    FROM programming p
    WHERE p.lab1 >= 6 AND p.lab2 >= 6 AND p.lab3 >= 6 AND p.lab4 >= 6
);
"""

select_good_labs = """
SELECT s.name
FROM students s
JOIN programming p ON s.isu = p.student_isu
WHERE p.lab1 >= 6 AND p.lab2 >= 6 AND p.lab3 >= 6 AND p.lab4 >= 6
UNION
SELECT s.name
FROM students s
JOIN opd o ON s.isu = o.student_isu
WHERE o.lab1 >= 6 AND o.lab2 >= 6;
"""

select_good_ege = """
SELECT student_isu
FROM ege
WHERE russian > 80
UNION
SELECT student_isu
FROM ege
WHERE math > 80;
"""

select_distinct_age = """
SELECT DISTINCT age
FROM students;
"""

update_age = """
UPDATE students
SET age = 19
WHERE name IN ('Egor', 'Maria');
"""

select_age = """
SELECT age
FROM students;
"""

update_ege = """
UPDATE ege
SET math = 80
WHERE student_isu IN (1, 2);
"""

select_math = """
SELECT math
FROM ege;
"""

delete_students = """
DELETE FROM students WHERE isu = 1;
"""

delete_ege = """
DELETE FROM ege WHERE id = 1;
"""

delete_programming = """
DELETE FROM programming WHERE id = 1;
"""

select_programming = """
SELECT * from programming
"""

delete_opd = """
DELETE FROM opd WHERE id = 1;
"""

select_opd = """
SELECT * from opd
"""


def create_connection(path):
    connection = None
    
    try:
        connection = sqlite3.connect(path)
        print("Connection to SQLite DB successful")

    except Error as e:
        print(f"The error '{e}' occurred")

    return connection


def create_database(connection, query):
    cursor = connection.cursor()

    try:
        cursor.execute(query)
        print("Database created successfully")

    except Error as e:
        print(f"The error '{e}' occurred")


def execute_query(connection, query):
    cursor = connection.cursor()

    try:
        cursor.execute(query)
        connection.commit()

    except Error as e:
        print(f"The error '{e}' occurred")


def execute_read_query(connection, query):
    cursor = connection.cursor()
    result = None

    try:
        cursor.execute(query)
        result = cursor.fetchall()
    
    except Error as e:
        print(f"The error '{e}' occurred")
    
    return result


if __name__ == "__main__":
    connection = create_connection(r"/Users/eskimo/Desktop/itmo/itmo/1st_semester/informatics/lab3/university.sqlite")

    create_database(connection, create_database_query)

    execute_query(connection, create_students_table)
    execute_query(connection, create_ege_table)
    execute_query(connection, create_programming_table)
    execute_query(connection, create_opd_table)
    execute_query(connection, create_students)
    execute_query(connection, create_ege)
    execute_query(connection, create_programming)
    execute_query(connection, create_opd)

    print("------------------------------------")

    students = execute_read_query(connection, select_students)
    for student in students:
        print(student)

    print("------------------------------------")

    ege = execute_read_query(connection, select_ege)
    for el in ege:
        print(el)

    print("------------------------------------")

    students_ege = execute_read_query(connection, select_students_ege)
    for el in students_ege:
        print(el)

    print("------------------------------------")

    stud_prog_opd = execute_read_query(connection, select_students_programming_opd)
    for el in stud_prog_opd:
        print(el)

    print("------------------------------------")

    math = execute_read_query(connection, select_math_over_70)
    for el in math:
        print(el)

    print("------------------------------------")

    good_prog = execute_read_query(connection, select_good_prog_labs)
    for el in good_prog:
        print(el)

    print("------------------------------------")

    good_labs = execute_read_query(connection, select_good_labs)
    for el in good_labs:
        print(el)

    print("------------------------------------")

    good_ege = execute_read_query(connection, select_good_ege)
    for el in good_ege:
        print(el)

    print("------------------------------------")

    distinct_age = execute_read_query(connection, select_distinct_age)
    for el in distinct_age:
        print(el)

    print("====================================")

    age = execute_read_query(connection, select_age)
    for el in age:
        print(el)

    print("------------------------------------")

    execute_query(connection, update_age)

    age = execute_read_query(connection, select_age)
    for el in age:
        print(el)

    print("------------------------------------")

    math = execute_read_query(connection, select_math)
    for el in math:
        print(el)

    print("------------------------------------")

    execute_query(connection, update_ege)

    math = execute_read_query(connection, select_math)
    for el in math:
        print(el)

    print("====================================")

    students = execute_read_query(connection, select_students)
    for student in students:
        print(student)

    print("------------------------------------")

    execute_query(connection, delete_students)

    students = execute_read_query(connection, select_students)
    for student in students:
        print(student)

    print("------------------------------------")

    ege = execute_read_query(connection, select_ege)
    for el in ege:
        print(el)

    print("------------------------------------")

    execute_query(connection, delete_ege)

    ege = execute_read_query(connection, select_ege)
    for el in ege:
        print(el)

    print("------------------------------------")

    programming = execute_read_query(connection, select_programming)
    for el in programming:
        print(el)

    print("------------------------------------")

    execute_query(connection, delete_programming)

    programming = execute_read_query(connection, select_programming)
    for el in programming:
        print(el)

    print("------------------------------------")

    opd = execute_read_query(connection, select_opd)
    for el in opd:
        print(el)

    print("------------------------------------")

    execute_query(connection, delete_opd)

    opd = execute_read_query(connection, select_opd)
    for el in opd:
        print(el)
