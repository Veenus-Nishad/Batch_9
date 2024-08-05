import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter the first number: ")
    val a = scanner.nextInt()

    print("Enter the second number: ")
    val b = scanner.nextInt()

    print("Enter the third number: ")
    val c = scanner.nextInt()

    scanner.close()

    if (a > b && a > c) {
        println("$a is the largest")
    } else if (b > c) {
        println("$b is the largest")
    } else {
        println("$c is the largest")
    }
}
