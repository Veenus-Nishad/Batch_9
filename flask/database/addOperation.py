import sqlite3
from datetime import date
import uuid

def createUser(name, password, phone_number, email,pinCode, address):
    conn = sqlite3.connect("my_medical.db")
    cursor = conn.cursor()

    user_id = str(uuid.uuid4())  # Generate a unique user ID
    date_of_account_creation = date.today()  # Get the current date

    # Explicitly specify the column names in the INSERT query
    cursor.execute(""" 
    INSERT INTO Users (
        user_id, password, level, date_of_account_creation,isApproved, block, name,email, phone_number, pinCode, address
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """, (user_id, password, 1, date_of_account_creation, 0, 0, name, email,phone_number, pinCode, address))

    conn.commit()
    conn.close()

    return user_id  # Return the generated user ID


def addProduct(name,price,stock,expiry_date,category):
    conn = sqlite3.connect("my_medical.db")
    cursor = conn.cursor()

    product_id = str(uuid.uuid4())  # Generate a unique product ID
    cursor.execute(""" 
    INSERT INTO Products (
       product_id, name, price, stock, expiry_date, category
    ) VALUES (?,?, ?, ?, ?, ?)
    """, (product_id, name, price, stock, expiry_date, category))

    conn.commit()
    conn.close()

    return product_id