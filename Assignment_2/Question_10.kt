/* Employee Attendance Tracker
Assignment Batch 9
    - Create a class `Employee` with properties `name`, `employeeId`, and `attendance` (an array of
 booleans representing days of the month).
    - Implement methods `markAttendance(day: Int, present: Boolean)` and `calculateAttendance()`
 to update and calculate the attendance percentage.
    - Create an array of `Employee` objects and use a loop to print the attendance details of each
 employee.
    - **Bonus:** Add a method `displayAttendance()` to print the attendance details of the employee */

    class Employee(val name: String, val employeeId: Int) {
      // An array of booleans representing attendance for each day of the month
      private val attendance = BooleanArray(30) { false }
  
      // Method to mark attendance for a given day
      fun markAttendance(day: Int, present: Boolean) {
          if (day in 1..attendance.size) {
              attendance[day - 1] = present
          } else {
              println("Invalid day: $day")
          }
      }
  
      // Method to calculate attendance percentage
      fun calculateAttendance(): Double {
          val presentDays = attendance.count { it }
          return (presentDays.toDouble() / attendance.size) * 100
      }
  
      // Bonus method to display attendance details
      fun displayAttendance() {
          println("Employee: $name (ID: $employeeId)")
          println("Attendance: ${attendance.map { if (it) "P" else "A" }.joinToString(" ")}")
          println("Attendance Percentage: ${"%.2f".format(calculateAttendance())}%\n")
      }
  }
  
  fun main() {
      // Creating an array of Employee objects
      val employees = arrayOf(
          Employee("John Doe", 101),
          Employee("Jane Smith", 102),
          Employee("Emily Davis", 103)
      )
  
      // Marking attendance for each employee
      employees[0].markAttendance(1, true)
      employees[0].markAttendance(2, true)
      employees[0].markAttendance(3, false)
  
      employees[1].markAttendance(1, true)
      employees[1].markAttendance(2, false)
      employees[1].markAttendance(3, true)
  
      employees[2].markAttendance(1, false)
      employees[2].markAttendance(2, true)
      employees[2].markAttendance(3, true)
  
      // Printing attendance details for each employee
      for (employee in employees) {
          employee.displayAttendance()
      }
  }
  