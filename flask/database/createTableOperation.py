import sqlite3

def createTable():
    conn = sqlite3.connect("my_medical.db")
    cursor = conn.cursor()
    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Users(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id VARCHAR(255),
        password VARCHAR(255),
        level INT,
        date_of_account_creation DATE,
        isApproved BOOLEAN,
        block BOOLEAN,
        name VARCHAR(255),
        email VARCHAR(255),
        phone_number VARCHAR(255),
        pinCode VARCHAR(255),
        address VARCHAR(255)
    );
    ''')
    conn.commit()  # Don't forget to commit the transaction
    conn.close()
 