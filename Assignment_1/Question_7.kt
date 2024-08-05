fun main(){
  var strings=arrayOf<String>("Alice", "Bob", "Charlie")
  for(i in strings.indices){
    println("Element at index $i: ${strings[i]}")
  }
}