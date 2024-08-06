/*  Library Membership System
   - Create a class `Member` with properties `name`, `memberId`, and `borrowedBooks` (an array of
 `Book` objects).
   - Implement methods `borrowBook(book: Book)` and `returnBook(book: Book)` to add and remove
 books from the `borrowedBooks` array.
   - Create an array of `Member` objects and use a loop to print the details of all borrowed books for
 each member.
   - **Bonus:** Add a method `displayMemberInfo()` to print the member's details */

   data class Book(val title: String)

   class Member(val name: String, val memberId: Int,var borrowedBooks:MutableList<Book>) {
   
       fun borrowBook(book: Book) {
           borrowedBooks.add(book)
       }
   
       fun returnBook(book: Book) {
           borrowedBooks.remove(book)
       }
   }
   
   fun main() {
       val members = arrayOf(
           Member("Poonam", 34, mutableListOf(Book("A"), Book("B"))),
           Member("Ashwini", 35, mutableListOf(Book("X"), Book("Y"))),
           Member("AJAY", 3, mutableListOf(Book("E"), Book("F")))
       )
   
       for (member in members) {
           println("Member: ${member.name}, Borrowed Books are: ${member.borrowedBooks}")
       }
   }
   