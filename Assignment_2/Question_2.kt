/*  Library Book System
   - Create a class `Book` with properties `title`, `author`, and `isAvailable` (a boolean).
   - Implement methods `borrowBook()` and `returnBook()` to change the `isAvailable` status.
   - Create an array of `Book` objects representing a library inventory.
   - Use a loop to print the titles and availability status of all books.
   - **Bonus:** Add a method `displayBookInfo()` to print the book's details. */

    fun main(){
      var Books=arrayOf(
        Book("A","XX",true),
        Book("B","XX",true),
        Book("C","XY",false), 
        Book("D","XYZ",false)
      )

      for (book in Books){
        println("Book name : ${book.title} , isAvaiable : ${book.isAvailable}")
        println()
      }

      for (book in Books){
        book.displayBookInfo()
      }
    }

   class Book(val title:String,val author:String,var isAvailable:Boolean=true){
    fun borrowBook(){
      isAvailable=!isAvailable
      println("You Borrowed The Book")
    }

    fun returnBook(){
      isAvailable=!isAvailable
      println("You Returned The Book")
    }

    fun displayBookInfo(){
      println(title)
      println(author)
      println("Book is available : ${isAvailable}")
    }
   }