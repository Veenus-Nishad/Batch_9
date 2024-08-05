/* Shopping Cart System 
   - Create a class `Product` with properties `productName`, `price`, and `quantity`.
   - Implement a method `totalCost()` in the `Product` class to return the total cost (price * quantity).
   - Create an array of `Product` objects representing a shopping cart.
   - Use a loop to calculate and print the total cost of all products in the cart.
   - **Bonus:** Add a method `displayProductInfo()` to print the product's details 
*/

fun main(){
  var shoppingCart=arrayOf(
    Product("Aloo",20,5),
    Product("kela",12,12),
    Product("Muli",18,4)
  )
  var totalCost=0
  for (product in shoppingCart){
    totalCost+=product.totalCost()
  }
  println("Your Total Bill is $:${totalCost}")
}

class Product(val productName:String,val price :Int,val quantity:Int){
  fun totalCost():Int{
    return price*quantity
  }
}