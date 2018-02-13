
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class PersonnelManager {

    private Employee[] employees; // Array containing all of the employee objects
    private int length; // Index of where the first empty space is

    /**************************************
    * Initialize a new Employee array
    * with length 1
    **************************************/   
    public PersonnelManager() {
        employees = new Employee[1];
        length = 0;
    }

    /*********************************************************************
    * Loops through every line in the input file and adds the employee
    * to the employees array
    * @param inFile A file name which contains all of the employees
    *********************************************************************/
    public void fillEmployees(String inFile) {
        Scanner employeeIn = new Scanner(inFile);
        String lastName;
        String withComma;
        String firstName;
        char indicator;
        double pay;

        while (employeeIn.hasNextLine() && employeeIn.hasNext()) {
            withComma = employeeIn.next();
            lastName = withComma.substring(0, length-2); // Strip the comma off the last name
            firstName = employeeIn.next();
            indicator = employeeIn.next().charAt(0);
            pay = employeeIn.nextInt();
            
            // The `null` means that this employee is from the original inFile and not the update file
            newEmployee(null, lastName, firstName, indicator, pay, length+1);
        }

        employeeIn.close();
    }


    /****************************************************************************************
    * Reads in the updates from the update file and changes the employees array accordingly 
    * @param updateFile Name of the file to get updates from
    * @param outputFile Name of the file to print the output
    *****************************************************************************************/
    public void updateEmployees(String updateFile, String outputFile) {
        Scanner updates = new Scanner(updateFile);
        PrintWriter employeeOut = null;
        try {
            employeeOut = new PrintWriter(outputFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

        int counter = 1;
        int raise = 0;

        // ArrayList might be changed to something more effecient 
        ArrayList<Employee> deletedEmployees = new ArrayList<Employee>();
        ArrayList<Employee> newEmployees = new ArrayList<Employee>();

        char update;

        while (updates.hasNextLine() && updates.hasNext()) {
            update = updates.next().charAt(0);

            switch (update) {
                case 'n':
                case 'N':
                    String withComma = updates.next();
                    String lastName = withComma.substring(0, withComma.length()-2);
                    newEmployee(newEmployees, lastName, updates.next(), updates.next().charAt(0), updates.nextDouble(), counter);
                    break;
                case 'd':
                case 'D':
                    deleteEmployee(deletedEmployees, updates.next());
                    break;
                case 'r':
                case 'R':
                    int newRaise = updates.nextInt();
                    if (newRaise > raise) {
                        raise = newRaise;
                    }
                    break;
                default:
                    invalidCode(counter);
            }

            counter++;
        }

        for (Employee employee : newEmployees) {
            employeeOut.println("New Employee added: " + employee.getLastName() + ", " + employee.getFirstName());
        }

        if (raise != 0) {
            employeeOut.println("New Wages:");
            for (Employee employee : employees) {
                employee.setWage(employee.getWage() * (1.0 + (1.0/raise)));
                employeeOut.println(employee);
            }
        }

        for (Employee employee : deletedEmployees) {
            employeeOut.println("Deleted Employee: " + employee.getLastName() + ", " + employee.getFirstName());  
        }

        employeeOut.close();
        updates.close();
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
    private void newEmployee(List<Employee> newEmployees, 
                            String lastName, 
                            String firstName, 
                            char indicator, 
                            double pay, 
                            int lineNumber) {

        boolean valid = true;

        maintainCapacity();

        if (indicator == 'h') {
            employees[length] = new HourlyEmployee(lastName, firstName, pay);
        } else if (indicator == 's') {
            employees[length] = new SalariedEmployee(lastName, firstName, pay);
        } else {
            invalidCode(lineNumber);
            valid = false;
            length--; // Since a new employee wouldn't be added, the length global variable shouldn't increment
        }

        length++;
        
        if (newEmployees != null && valid){
            newEmployees.add(employees[length]);
        }

    }


    /************************************************************************************************
    * Deletes an employee from the employees array
    * @param deletedEmployees A list to put the new employee 
    * along with the employees array if the user wants to keep track of the employees they delete
    * @param lastName The employee's last name
    *************************************************************************************************/
    private void deleteEmployee(List<Employee> deletedEmployees, String lastName) {
        for (int i = employees.length; i < 0; i--) {
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

    /********************************************************************************
    * Output every employee's pay for the week who has hours to the output file
    * @param hoursFile Name of the file containing the employee's hours
    * @param payrollFile Name of the file to print the employee's pay 
    *********************************************************************************/
    public void printPayroll(String hoursFile, String payrollFile) {
        PrintWriter payroll = null;
        try {
            payroll = new PrintWriter(payrollFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }
        Scanner hoursWorked = new Scanner(hoursFile);

        double total = 0;
        double pay;
        int hours;
        String lastName;

        System.out.println("Paycheck amount:");
        while (hoursWorked.hasNextLine() && hoursWorked.hasNext()) {

            lastName = hoursWorked.next();
            hours = hoursWorked.nextInt();

            for (Employee employee : employees) {
                if (lastName.equals(employee)) {
                    pay = employee.computePay(hours);
                    payroll.printf("%s, %s   $%f\n", employee.getLastName(), employee.getFirstName(), pay);
                    total += pay;
                    break;
                }
            }

        }
        
        // TODO: Fix Formatting
        System.out.println("--------------");
        System.out.println("Total $" + total);

        hoursWorked.close();
        payroll.close();

    }

    /************************************************************
    * Ensures the global employees array isn't full
    * If the array does happen to be full it is doubled in size
    ***********************************************************/
    private void maintainCapacity() {
        if (length == employees.length-1) {
            employees = doubleArray(employees);
        }
    }

    /***********************************************************************
    * Prints an error message with the line number of the error to stdout
    * @param lineNumber The line number of the error
    ***********************************************************************/
    private static void invalidCode(int lineNumber) {
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
}
