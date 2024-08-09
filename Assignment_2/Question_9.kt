/*  Library Inventory System
   - Create a class `Library` with properties `books` (an array of `Book` objects) and `librarianName`.
   - Implement methods `addBook(book: Book)` and `removeBook(book: Book)` to manage the
 books array.
   - Use a loop to print all books' details and the librarian's name.
   - **Bonus:** Add a method `displayLibraryInfo()` to print the library's details */

  data class Book(val Title :String)
   class Library(var books:MutableList<Book>,val librarianName:String){
    fun addBook(book:Book){
      books.add(book)
    }
    fun removeBook(book:Book){
      books.remove(book)
    }
   }

   fun main(){
      var kitab=arrayOf(
        Library(mutableListOf(Book("A"),Book("B")),"Poonam"),
        Library(mutableListOf(Book("C"),Book("D")),"Ashwini"),
      )
      for( library in kitab){
        println("Librarian: ${library.librarianName}")
        println("Put Books in the library:")
        println(library.books)
      }
   }