/* Function with Return Type: Write a Kotlin function that takes a string and returns its length. */

fun main(){
  var str="Poonam"
  var length=returnLength(str)
  print(length)
}

fun returnLength(str:String):Int{
  return str.length
}