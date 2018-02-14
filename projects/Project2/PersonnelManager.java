/**
 * John Naylor
 * CMSC 256 Section 2
 * Project 2 
 */

import java.util.List;
import java.io.PrintWriter;

public class PersonnelManager {

    private Employee[] employees; // Array containing all of the employee objects
    private int length; // number of employees

    /**************************************
    * Initialize a new Employee array
    * with length 1
    **************************************/   
    public PersonnelManager() {
        employees = new Employee[1];
        length = 0;
    }

    /****************************************************************************************************
     * Adds a new employee to the employees array
     * @param newEmployees An list to put the new employee 
     * along with the employees array if the user wants to keep track of the new employees they add
     * @param lastName The last name of the employee
     * @param firstName The first name of the employee
     * @param indicator Charactor indicatoring if the employee is salary or hourly
     * @param pay The new employee's pay
     * @param lineNumber Used for debugging invalid character codes
     ****************************************************************************************************/
    public void newEmployee(List<Employee> newEmployees, 
                            String lastName, 
                            String firstName, 
                            char indicator, 
                            double pay, 
                            int lineNumber) {

        boolean valid = true;

        maintainCapacity();

        if (indicator == 'h') {
            employees[length] = new HourlyEmployee(firstName, lastName, pay);
        } else if (indicator == 's') {
            employees[length] = new SalariedEmployee(firstName, lastName, pay);
        } else {
            invalidCode(lineNumber);
            valid = false;
            length--; // Since a new employee wouldn't be added, the length global variable shouldn't increment
        }

        if (newEmployees != null && valid){
            newEmployees.add(employees[length]);
        }

        length++;
    
    }


    /************************************************************************************************
    * Deletes an employee from the employees array
    * @param deletedEmployees A list to put the new employee 
    * along with the employees array if the user wants to keep track of the employees they delete
    * @param lastName The employee's last name
    *************************************************************************************************/
    public void deleteEmployee(List<Employee> deletedEmployees, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getLastName().equals(lastName)){
                deletedEmployees.add(employees[i]);
                employees[i] = employees[length-1];
                employees[length-1] = null;
                length--;
                return; // Deletes the first instance of that last name that comes up
            }
        }

        System.out.println("[!!!] Cannot delete " + lastName); // Error message
    }

    public void giveRaise(int percentage) {
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setWage(employee.getWage() * (1.0 + (1.0/percentage)));
            }
        }
    }

    public void printEmployees(PrintWriter pw) {

        for (Employee employee : employees) {
            if (employee != null) {
                pw.println(employee);
            }
        }
    }

    /**
     * 
     */
    public String employeePayroll(String lastName, int hours) {
        double pay;
        for (Employee employee : employees) {
            if (employee != null && lastName.equals(employee.getLastName())) {
                pay = employee.computePay(hours);

                return fortyCharacters(employee.getLastName() + ", " + employee.getFirstName(), String.format("$%.2f", pay));
            }
        }

        System.out.println(lastName + " is not an employee");
        return "";
    }

    public double employeeComputePay(String lastName, int hours) {
        for (Employee employee : employees) {
            if (employee != null && lastName.equals(employee.getLastName())) {
                return employee.computePay(hours);
            }
        }

        return 0;
    }

    /************************************************************
    * Ensures the global employees array isn't full
    * If the array does happen to be full it is doubled in size
    ***********************************************************/
    public void maintainCapacity() {
        if (length == employees.length-1) {
            employees = doubleArray(employees);
        }
    }

    /***********************************************************************
    * Prints an error message with the line number of the error to stdout
    * @param lineNumber The line number of the error
    ***********************************************************************/
    public static void invalidCode(int lineNumber) {
        System.out.println("Command was not recognized; <" + lineNumber + ">");
    }

    /***************************************************
    * @param smallArray The array needed to be doubled 
    * @return The original array with double the capacity                                                
    ****************************************************/
    private static Employee[] doubleArray(Employee[] smallArray) {
        Employee[] bigArray = new Employee[2 * smallArray.length];  

        for (int i = 0; i < smallArray.length; i++) {
            bigArray[i] = smallArray[i]; 
        }

        return bigArray;
    }

    public static String fortyCharacters(String one, String two) {
        StringBuilder totalString = new StringBuilder();
        totalString.append(one);

        while (40 - (totalString.length() + two.length()) > 0) {
            totalString.append(" ");
        }

        totalString.append(two);

        return totalString.toString();
    }
}
