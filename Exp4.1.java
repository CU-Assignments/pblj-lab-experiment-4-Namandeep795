Experiment 4.1: Employee Management System
  
import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class Main {
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        displayAllEmployees();

        addEmployee(101, "Anish", 50000);
        addEmployee(102, "Bobby", 60000);

        displayAllEmployees();
    }

    public static void addEmployee(int id, String name, double salary) {
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added: " + name);
    }

    public static void updateEmployee(int id, String newName, double newSalary) {
        Employee emp = findEmployeeById(id);
        if (emp != null) {
            emp.name = newName;
            emp.salary = newSalary;
            System.out.println("Employee updated: " + newName);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void removeEmployee(int id) {
        Employee emp = findEmployeeById(id);
        if (emp != null) {
            employees.remove(emp);
            System.out.println("Employee removed: " + emp.name);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void searchEmployee(String query) {
        Employee emp = findEmployeeById(Integer.parseInt(query));
        if (emp != null) {
            System.out.println("Employee found: " + emp);
        } else {
            emp = findEmployeeByName(query);
            if (emp != null) {
                System.out.println("Employee found: " + emp);
            } else {
                System.out.println("Employee not found.");
            }
        }
    }

    public static void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    public static Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                return emp;
            }
        }
        return null;
    }

    public static Employee findEmployeeByName(String name) {
        for (Employee emp : employees) {
            if (emp.name.equalsIgnoreCase(name)) {
                return emp;
            }
        }
        return null;
    }
}

Experiment 4.1: Employee Management System

The Employee Management System is a simple Java-based application that uses ArrayList to manage employee records. The system allows users to perform the following operations:

Add Employee → Store Employee ID, Name, and Salary.

Update Employee → Modify employee details based on their ID.

Remove Employee → Delete an employee using their ID.

Search Employee → Find employees by ID or Name.

Display All Employees → Show a complete list of employees.

Test Cases

Test Case 1: Adding Employees (No Employees Initially)
Display Employees
Expected Output:
No employees found.
  
Test Case 2: Add Employees
Input:
Add Employee (ID=101, Name="Anish", Salary=50000)
Add Employee (ID=102, Name="Bobby", Salary=60000)
Expected Output:
Employee Added: ID=101, Name=Anish, Salary=50000
Employee Added: ID=102, Name=Bobby, Salary=60000

Test Case 3: Update Employee Salary
Input:
Update Employee (ID=101, New Salary=55000)
Expected Output:
Employee ID 101 updated successfully.

Test Case 4: Search Employee by ID
Input:
Search Employee by ID=102
Expected Output:
Employee Found: ID=102, Name=Bobby, Salary=60000

Test Case 5: Remove Employee
Input:
Remove Employee (ID=101)
Expected Output:
Employee ID 101 removed successfully.

Test Case 6: Display All Employees
Input:
Display Employees
Expected Output:
ID: 102, Name: Bobby, Salary: 60000

Test Case 7: Adding Duplicate Employee ID
Input:
Add Employee (ID=101, Name="Charlie", Salary=70000)
Expected Output:
Error: Employee with ID 101 already exists.


