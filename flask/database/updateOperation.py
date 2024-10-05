import sqlite3

def updateUserAllFields (userID,**keyword):
  conn= sqlite3.connect("my_medical.db")
  cursor=conn.cursor()

  for key , value in keyword.items():
    if key =="name":
      cursor.execute("UPDATE Users SET name = ? WHERE user_id = ?",(value,userID))
    elif key =="password":
      cursor.execute("UPDATE Users SET password = ? WHERE user_id = ?",(value,userID))
    elif key =="email":
      cursor.execute("UPDATE Users SET email = ? WHERE user_id = ?",(value,userID))
    elif key =="phone_number": 
      cursor.execute("UPDATE Users SET phone_number = ? WHERE user_id = ?",(value,userID))
    elif key =="pinCode":
      cursor.execute("UPDATE Users SET pinCode = ? WHERE user_id = ?",(value,userID))
    elif key =="address":
      cursor.execute("UPDATE Users SET address = ? WHERE user_id = ?",(value,userID))
    elif key == "level":
      cursor.execute("UPDATE Users SET level = ? WHERE user_id = ?",(value,userID))
    elif key == "isApproved":
      cursor.execute("UPDATE Users SET isApproved = ? WHERE user_id = ?",(value,userID))
    elif key == "block":
      cursor.execute("UPDATE Users SET block = ? WHERE user_id = ?",(value,userID))
  conn.commit()
  conn.close()

def updateOrderAllFields (orderID,**keyword):
  conn= sqlite3.connect("my_medical.db")
  cursor=conn.cursor()

  for key , value in keyword.items():
    if key == "quantity":
      cursor.execute("UPDATE Orders SET quantity = ? WHERE order_id = ?",(value,orderID))
    



