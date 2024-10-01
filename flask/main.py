from flask import Flask, jsonify, request
from database.createTableOperation import createTable,createProductTable
from database.addOperation import createUser,addProduct
from database.readOperation import getAllUsers,getAllProducts
from auth import user_auth

app = Flask(__name__)

@app.route("/", methods=["GET"])
def home(): 
    return "Jai Ho API ki"

@app.route('/signUp', methods=['POST'])
def signUp():
    name=request.form['name']
    password = request.form['password']  # You need to extract password from the form
    email =  request.form['email'] 
    phone_number = request.form['phone_number']
    pinCode = request.form['pinCode']
    address = request.form['address']

        # Assuming createUser returns some result or success message in string format
    data = createUser(name=name,password= password, email=email,phone_number= phone_number, pinCode=pinCode, address=address)
    return data

@app.route('/ ', methods=['GET'])
def getAllUser():
    data = getAllUsers()
    return data

@app.route('/ ', methods=['POST'])
def login():
    email = request.form['email']
    password = request.form['password']
    data = user_auth(email, password)
    return data

@app.route("/addProducts", methods=["POST"])
def addProducts():
    name=request.form['name']
    price = request.form['price']  # You need to extract price from the form
    stock=request.form['stock']
    expiry_date = request.form['expiry_date']
    category = request.form['category']

    data=addProduct(name=name,price=price,stock=stock,expiry_date=expiry_date,category=category)
    return data
 
@app.route("/products", methods=["GET"])
def getProducts():
    data = getAllProducts()
    return data

if __name__ == "__main__":
    createTable() 
    createProductTable()
    app.run(debug=True)
