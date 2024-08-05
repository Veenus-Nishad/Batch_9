/* Create a Kotlin program using a do-while loop that prompts the user to enter a number until they
 enter a negative number. Afterward, the program should print the sum of all entered positive numbers */

 import java.util.*
 
 fun main(){
  val scanner=Scanner(System.`in`)
  var sum=0
  do{
    var a = scanner.nextInt()
    sum+=a
  }while(a>0)

  print(sum)
 }