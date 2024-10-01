from flask import Flask, jsonify, request
from database.createTableOperation import createTable
from database.addOperation import createUser

app = Flask(__name__)

@app.route("/", methods=["GET"])
def home(): 
    return "Jai Ho API ki"

@app.route('/signUp', methods=['POST'])
def signUp():
    name=request.form['name']
    password = request.form['password']  # You need to extract password from the form
    email = request.form['email']
    phone_number = request.form['phone_number']
    pinCode = request.form['pinCode']
    address = request.form['address']

        # Assuming createUser returns some result or success message in string format
    data = createUser(name=name,password= password, email=email,phone_number= phone_number, pinCode=pinCode, address=address)
    return data

if __name__ == "__main__":
    createTable() 
    app.run(debug=True)
