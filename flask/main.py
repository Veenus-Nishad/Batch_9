from flask import Flask, jsonify, request
from database.createTableOperation import createTable
from database.addOperation import createUser,addProduct,placeOrder,vendorStock
from database.readOperation import getAllUsers,getAllProducts,getSpecificUser,getSpecificProduct,getSpecificUsersOrders,getSpecificProductsOrders
from database.auth import user_auth
from database.updateOperation import updateUserAllFields,consumedStock,updateOrderAllFields,addToStock,updateStockAddAtAdmin,updateStockConsumedAtAdmin
from database.deleteOperation import delete_all_users,delete_Specific_User
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

@app.route('/login', methods=['POST'])
def login():
    email = request.form['email']
    password = request.form['password']
    data = user_auth(email, password)
    return jsonify({"user_id":data[1]})

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

@app.route("/getSpecificUser",methods=["POST"]) # kyunkii pehele user server ko bataenge gi kiska lana hai 
def getSpecificUserMain():
    try:
        userID = request.form['userID']
        data = getSpecificUser(userID)
        return data 
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    

@app.route("/updateUserName", methods=["PATCH"])
def updateUserNameMain():
    try:
        userID = request.form['userID']
        allFields=request.form.items()
        updateUser={}

        for key , value in allFields:
            if key != "userID":
                updateUser[key]=value
        updateUserAllFields(userID=userID,**updateUser)

    
        return jsonify({"status ":200,"message": "Updated"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/getSpecificProduct",methods=["GET"])
def getSpecificProductMain():
    try:
        productID=request.form["productID"]
        data = getSpecificProduct(productID)
        return data
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/placeOrder",methods=["POST"])
def placeOrderMain():
    try:
        userID = request.form['userID']
        productID = request.form['productID']
        quantity = request.form['quantity']
        placeOrder(user_id=userID,product_id=productID,quantity=quantity)
        return jsonify({"status ":200,"message": "Order is Placed"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})

@app.route("/userOrders",methods=["GET"])
def userOrdersMain():
    try:
        userID = request.form['userID']
        data=getSpecificUsersOrders(userID)
        return jsonify({"status ":200,"message": f"Order's Placed by {userID} {data}"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/productOrders",methods=["GET"])
def productOrdersMain():
    try:
        productID = request.form['productID']
        data=getSpecificProductsOrders(productID)
        return jsonify({"status ":200,"message": f"Order's of Product {data}"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})

@app.route("/updateOrder",methods=["PATCH"])
def updateOrderMain():
    try:
        orderID = request.form['orderID']
        allFields=request.form.items()
        updateUser={}

        for key , value in allFields:
            if key != "orderID":
                updateUser[key]=value
        updateUserAllFields(orderID=orderID,**updateUser)

    
        return jsonify({"status ":200,"message": "Updated"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})


@app.route("/vendorStock",methods=["POST"]) 
def vendorStockMain():
    try:
        vendorID = request.form['vendorID']
        productID = request.form['productID']
        stock = request.form['stock']
        vendorStock(vendor_id=vendorID,product_id=productID,stock=stock)
        return jsonify({"status ":200,"message": "Stock Confirmed"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/consumedStock",methods=["PATCH"])
def CustomerBoughtMain():
    try:
        vendorID = request.form['vendorID']
        productID = request.form['productID']
        quantity = request.form['quantity']
        consumedStock(vendorID=vendorID,productID=productID,consumed=quantity)
        return jsonify({"status ":200,"message": "Stock Updated"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/addToStock",methods=["PATCH"])
def vendorAddToStockMain():
    try:
        vendorID = request.form['vendorID']
        productID = request.form['productID']
        quantity = request.form['quantity']
        addToStock(vendorID=vendorID,productID=productID,add=quantity)
        return jsonify({"status ":200,"message": "Stock Updated"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/updateStockConsumed",methods=["PATCH"])
def adminStockConsumedMain():
    try:
        vendorID = request.form['vendorID']
        productId=request.form['productID']
        quantity = request.form['quantity']
        updateStockConsumedAtAdmin(vendorID=vendorID,productID=productId,consumed=quantity)
        return jsonify({"status ":200,"message": "Stock Updated"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("/updateStockAdd",methods=["PATCH"])
def adminStockAddMain():
    try:
        vendorID = request.form['vendorID']
        productId=request.form['productID']
        quantity = request.form['quantity']
        updateStockAddAtAdmin(vendorID=vendorID,productID=productId,add=quantity)
        return jsonify({"status ":200,"message": "Stock Updated"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
        

@app.route("/deleteAllUsers",methods=["DELETE"])
def deleteAllUsersMain():
    try:
        delete_all_users()
        return jsonify({"status ":200,"message": "All Users Deleted"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
@app.route("deleteSpecificUser",methods=["DELETE"])
def deleteSpecificUserMain():
    try:
        userID = request.form['userID']
        delete_Specific_User(userID)
        return jsonify({"status ":200,"message": "User Deleted"})
    except Exception as e:
        return jsonify({"status ":400,"message": str(e)})
    
if __name__ == "__main__":
    createTable() 
    app.run(debug=True)
 