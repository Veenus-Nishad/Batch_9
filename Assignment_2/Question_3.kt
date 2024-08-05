/* 3. Employee Salary Calculator
   - Create a base class `Employee` with properties `name`, `id`, and `salary`.
   - Create a subclass `Manager` that inherits from `Employee` and adds a property `bonus`.
   - Implement a method `calculateTotalSalary()` in the `Manager` class to return the total salary
 including the bonus.
   - Create an array of `Manager` objects and use a loop to print the total salary of each manager.
   - **Bonus:** Add a method `displayEmployeeDetails()` to print all details of the employee. */

   fun main(){
    var managers=arrayOf(
      Manager("Poonam",34,50000,15000),
      Manager("Veenus",35,60000,15000),
      Manager("Ashwini",36,50000,15000)
    )
   }

   open class Employee( val name:String,val id:Int,val salary:Int)

   class Manager(val name1:String,val id1:Int,val salary1:Int, val bonus:Int) : Employee(name1,id1,salary1){
    fun calculateTotalSalary():Int{
      return salary+bonus
    }
   }