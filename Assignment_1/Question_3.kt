fun main(){
  var arr = arrayOf(34,35,69)
  var sum=SumOfArrayElements(arr)
  println(sum)
}

fun SumOfArrayElements(arr:Array<Int>):Int{
  var sum=0
  for(i in arr){
    sum+=i
  }
  return sum
}