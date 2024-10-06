import sqlite3

def delete_Specific_User(user_id):
  conn = sqlite3.connect("my_medical.db")
  cursor = conn.cursor()
  cursor.execute("DELETE FROM Users WHERE user_id = ?", (user_id,))
  conn.commit()
  conn.close()

def delete_all_users():
  conn = sqlite3.connect("my_medical.db")
  cursor=conn.cursor()
  cursor.execute("DELETE FROM Users")
  conn.commit()
  conn.close()