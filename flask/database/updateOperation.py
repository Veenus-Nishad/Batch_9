import sqlite3

def updateUserName(userID,newName):
  conn= sqlite3.connect("my_medical.db")
  cursor=conn.cursor()

  cursor.execute("UPDATE Users SET name = ? WHERE user_id = ?",(newName,userID))
  conn.commit()
  conn.close()