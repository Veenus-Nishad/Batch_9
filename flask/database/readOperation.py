import sqlite3
import json

def getAllUsers():
  conn = sqlite3.connect("my_medical.db")
  cursor = conn.cursor()
  cursor.execute("SELECT * FROM Users") #Fetch query
  users = cursor.fetchall()
  conn.close()

  userJson=[]

  for user in users:
    tempUser={
      "id": user[0],
      "user_id": user[1],
      "password": user[2],
      "level": user[3],
      "date_of_account_creation": user[4],
      "isApproved": user[5],
      "block": user[6],
      "name": user[7],
      "email": user[8],
      "phone_number": user[9],
      "pinCode": user[10],
      "address": user[11]
    }
    userJson.append(tempUser)
  return (json.dumps(userJson))

def getAllProducts():
  conn = sqlite3.connect("my_medical.db")
  cursor = conn.cursor()
  cursor.execute("SELECT * FROM Products") #Fetch query
  products = cursor.fetchall()
  conn.close()

  productJson=[]

  for product in products:
    tempProduct={
      "product_id": product[0],
      "name": product[1],
      "price": product[2],
      "stock": product[3],
      "expiry_date": product[4],
      "category": product[5],
    }
    productJson.append(tempProduct)
  return (json.dumps(productJson))

def getSpecificUser(userID):
  conn=sqlite3.connect("my_medical.db")
  cursor=conn.cursor()
  cursor.execute("SELECT * FROM Users WHERE user_id = ?",(userID,)) #Fetch query
  users=cursor.fetchall()
  conn.close()

  userJson=[]

  for user in users:
    tempUser1={
      "id": user[0],
      "user_id": user[1],
      "password": user[2],
      "level": user[3],
      "date_of_account_creation": user[4],
      "isApproved": user[5],
      "block": user[6],
      "name": user[7],
      "email": user[8],
      "phone_number": user[9],
      "pinCode": user[10],
      "address": user[11]
    }
    userJson.append(tempUser1)
  
  return json.dumps(tempUser1)
