import mysql.connector
from mysql.connector import Error


create_users_table = """
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT,
  name TEXT NOT NULL,
  age INT,
  gender TEXT,
  nationality TEXT,
  PRIMARY KEY (id)
) ENGINE = InnoDB
"""

create_posts_table = """
CREATE TABLE IF NOT EXISTS posts (
  id INT AUTO_INCREMENT,
  title TEXT NOT NULL,
  description TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY fk_user_id (user_id) REFERENCES users(id), PRIMARY KEY (id)
) ENGINE = InnoDB
"""

create_comments_table = """
CREATE TABLE IF NOT EXISTS comments (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  text TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  post_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id) FOREIGN KEY (post_id) REFERENCES posts (id)
);
"""

create_likes_table = """
CREATE TABLE IF NOT EXISTS likes (
id INTEGER PRIMARY KEY AUTOINCREMENT,
user_id INTEGER NOT NULL,
post_id integer NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id) FOREIGN KEY (post_id)
REFERENCES posts (id)
);
"""

create_users = """
INSERT INTO
`users` (`name`, `age`, `gender`, `nationality`) VALUES
  ('James', 25, 'male', 'USA'),
('Leila', 32, 'female', 'France'),
  ('Brigitte', 35, 'female', 'England'),
  ('Mike', 40, 'male', 'Denmark'),
  ('Elizabeth', 21, 'female', 'Canada');
"""

select_users = "SELECT * FROM users"


def create_connection(host_name, user_name, user_password, db_name):
    connection = None

    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            passwd=user_password,
            database=db_name
        )
        print("Connection to MySQL DB successful") 

    except Error as e:
        print(f"The error '{e}' occurred")
    
    return connection


def execute_query(connection, query):
    cursor = connection.cursor()

    try:
        cursor.execute(query)
        connection.commit()
        print("Query executed successfully")

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
    connection = create_connection("localhost", "root", "", "sm_app")

    execute_query(connection, create_users_table)
    execute_query(connection, create_posts_table)
    execute_query(connection, create_comments_table)
    execute_query(connection, create_likes_table)
    execute_query(connection, create_users)

    users = execute_read_query(connection, select_users)
    
    for user in users:
        print(user)