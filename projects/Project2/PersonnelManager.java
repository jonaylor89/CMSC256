
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class PersonnelManager {

    private Employee[] employees;

    /**
    * 
    */   
    public PersonnelManager() {
        employees = new Employee[1];
        
    }

    /**
    * 
    */
    public void fillEmployees(String inFile) {
        Scanner employeeIn = new Scanner(inFile);
        String lastName;
        String withComma;
        String firstName;
        char indicator;
        double pay;

        int index = 0;

        while (employeeIn.hasNextLine && employeeIn.hasNext()) {
            withComma = employeeIn.next();
            lastName = withComma.substring(0, length-2);
            firstName = employeeIn.next();
            indicator = employeeIn.next().charAt(0);
            pay = employeeIn.nextInt();

            // check if array is full
            if (employees[employees.length-1] != null) {
                employees = doubleArray(employees);
            }
            
            if (indicator == 'h') {
                employees[index] = new HourlyEmployee(lastName, firstName, pay);
            } else if (indicator == 's') {
                employees[index] = new SalariedEmployee(last, firstName, pay);
            } else {
                invalidCode(index+1);
                index--;
            }

            index++;
        }

        employeeIn.close();

    }


    /**
    * 
    */
    public void parseUpdates(String updateFile) {
        Scanner updates = new Scanner(updateFile);
        int counter = 1;
        double raise = 0;

        char update;

        while (updates.hasNextLine() && updates.hasNext()) {
            update = updates.next().charAt(0);

            switch (update) {
                case 'n':
                    String withComma = updates.next();
                    String lastName = withComma.substring(0, withComma.length()-2);
                    newEmployee(lastName, updates.next(), updates.next().charAt(0), counter);
                    break;
                case 'd':
                    deleteEmployee(updates.next(), counter);
                    break;
                case 'r':
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

        if (raise != 0) {
            for (Employee employee : employees) {
                employee.setWage(employee.getWage() * (1.0 + (1.0/raise)));
            }
        }

        updates.close();

    }

    /**
     * 
     */
    public void newEmployee(String lastName, String firstName, char indicator, int pay) {

        // Check if array is full
        if (employees[employees.length-1] != null) {
            employees = doubleArray(employees);
        }

        int i = 0;
        while (employees[i] != null) {
            i++;
        }

        if (indicator == 'h') {
            employees[i] = new HourlyEmployee(lastName, firstName, pay);
        } else if (indicator == 's') {
            employees[i] = new SalariedEmployee(last, firstName, pay);
        } else {
            invalidCode(i+1);
        }

    }


    /**
    * 
    */
    public void deleteEmployee(String lastName, int lineNumber) {
        for (Employee employee : employees) {
            if (employee.getLastName().equals(lastName)){
                employee = null;
                return;
            }
        }

        System.out.println("[!!!] Cannot delete " + lastName);
    }

    /**
    * 
    */
    public void printOut(String outFile) {
        try {
            PrintWriter employeeOut = new PrintWriter(outFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

        // Fill in

        employeeOut.close();
    }

    /**
    * 
    */
    public void printPayroll(String hoursFile, String payrollFile) {
        try {
            PrintWriter payroll = new PrintWriter(payrollFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }
        Scanner hoursWorked = new Scanner(hoursFile);


        while (hoursWorked.hasNextLine() && hoursWorked.hasNext()) {

            // Fill in

        }


        double total = 0;
        double pay;

        System.out.println("Paycheck amount:");

        for (Employee employee : employees) {
            pay = employee.computerPay();
            total += pay;
            System.out.println(String.format("\t%s, %s  $%.2f", employee.getLastName(), 
                                                                employee.getFirstName(), 
                                                                pay));
        }
        
        // TODO: Fix Formatting
        System.out.println("--------------");
        System.out.println("Total $" + total);

        hours.close();
        payroll.close();

    }

    /**
    * 
    */
    private static void invalidCode(int lineNumber) {
        System.out.println("Command was not recognized; <" + lineNumber + ">");
    }

    /**
    * 
    */
    private static Employee[] doubleArray(Employee[] smallArray) {
        Employee[] bigArray = new Employee[2 * smallArray.length];  

        for (int i = 0; i < smallArray.length; i++) {
            bigArray[i] = smallArray[i]; 
        }

        return bigArray;
    }
}
