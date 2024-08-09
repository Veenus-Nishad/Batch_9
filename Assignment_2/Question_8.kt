/* Bank Account Management
   - Create a class `BankAccount` with properties `accountNumber`, `accountHolderName`, and
 `balance`.
   - Implement methods `deposit(amount: Double)` and `withdraw(amount: Double)` to update the
 balance.
   - Create an array of `BankAccount` objects representing different accounts.
   - Use a loop to perform some deposits and withdrawals, and print the final balance of each
 account.
   - **Bonus:** Add a method `displayAccountInfo()` to print the account's details. */

   class BankAccount(val accountNumber:Int,val accountHolderName:String,var balance:Double){
    fun deposit( amount:Double){
      balance+=amount
    }
    fun withdraw( amount:Double){
      balance-=amount
    }
   }

   fun main(){
    var bankAccount=arrayOf(
      BankAccount(34,"Poonam",50000.0),
      BankAccount(35,"Veenus",100000.0),
      BankAccount(36,"Ashwini",60000.0),
      BankAccount(37,"Dragon",150000.0),
    )
    for(balance in bankAccount){
      if(balance.accountNumber==34 || balance.accountNumber==36){
        balance.deposit(5000.0)
      }
      else{
        balance.withdraw(5000.0)
      }
      println(" ${balance.accountHolderName} has Balance : ${balance.balance}")
    }

   }