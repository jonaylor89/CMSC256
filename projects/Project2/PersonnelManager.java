
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class PersonnelManager {

    private Employee[] employees;
    private int length;

    /**
    * 
    */   
    public PersonnelManager() {
        employees = new Employee[1];
        length = 0;
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

        while (employeeIn.hasNextLine && employeeIn.hasNext()) {
            withComma = employeeIn.next();
            lastName = withComma.substring(0, length-2);
            firstName = employeeIn.next();
            indicator = employeeIn.next().charAt(0);
            pay = employeeIn.nextInt();

            maintainCapacity();
            
            newEmployee(lastName, firstName, indicator, pay, length+1);
        }

        employeeIn.close();

    }


    /**
    * 
    */
    public void updateEmployee(String updateFile, String outputFile) {
        Scanner updates = new Scanner(updateFile);
        try {
            PrintWriter employeeOut = new PrintWriter(outFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

        int counter = 1;
        double raise = 0;
        ArrayList<Employee> deletedEmployees = new ArrayList<Employee>();
        ArrayList<Employee> newEmployees = new ArrayList<Employee>();

        char update;

        while (updates.hasNextLine() && updates.hasNext()) {
            update = updates.next().charAt(0);

            switch (update) {
                case 'n':
                    String withComma = updates.next();
                    String lastName = withComma.substring(0, withComma.length()-2);
                    newEmployee(newEmployees, lastName, updates.next(), updates.next().charAt(0), updates.nextInt(), counter);
                    break;
                case 'd':
                    deleteEmployee(deletedEmployees, updates.next(), counter);
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

        for (Employee employee : newEmployees) {
            employeeOut.println("New Employee added: " + employee.getLastName() + "," + employee.getFirstName());
        }

        if (raise != 0) {
            employeeOut.println("New Wages:");
            for (Employee employee : employees) {
                employee.setWage(employee.getWage() * (1.0 + (1.0/raise)));
                employeeOut.println(employee);
            }
        }

        for (Employee employee : deletedEmployees) {
            employeeOut.println("Deleted Employee: " + employee.getLastName() + "," + employee.getFirstName());  
        }

        employeeOut.close();
        updates.close();

    }

    /**
     * 
     */
    private void newEmployee(String lastName, String firstName, char indicator, double pay, int lineNumber) {

        maintainCapacity();

        if (indicator == 'h') {
            employees[length] = new HourlyEmployee(lastName, firstName, pay);
        } else if (indicator == 's') {
            employees[length] = new SalariedEmployee(last, firstName, pay);
        } else {
            invalidCode(lineNumber);
            length--;
        }

        length++;

    }


    /**
    * 
    */
    private void deleteEmployee(ArrayList<Employee> deletedEmployees, String lastName, int lineNumber) {
        for (int i = employees.length; i < 0; i--) {
            if (employees[i].getLastName().equals(lastName)){
                deletedEmployees.add(employees[i]);
                employees[i] = employees[employees.length-1];
                employees[employees.length-1] = null;
                return;
            }
        }

        System.out.println("[!!!] Cannot delete " + lastName);
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
                    pay = employee.computerPay(hours);
                    payroll.printf("%s, %s   $%f\n", employee.getLastName(), employee.getFirstName(), pay);
                    total += pay;
                }
            }

        }
        
        // TODO: Fix Formatting
        System.out.println("--------------");
        System.out.println("Total $" + total);

        hoursFile.close();
        payroll.close();

    }

    private void maintainCapacity() {
        if (length == employees.length-1) {
            employees = doubleArray(employees);
        }
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
