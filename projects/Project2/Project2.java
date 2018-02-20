/**********************************************************************
 * John Naylor
 * CMSC 256 Section 2 Spring
 * CMSC256 Section 2 Spring
 * Project 2: Program to manage employees like a true capitalist pig
 ********************************************************************/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Project2 {

    /***********************************************************
    * Fill up the employees array for a personnel manager;
    * Updates all of the employees in it;
    * Outputs their payroll to a txt file;
    ***********************************************************/
    public static void main(String[] argv) {
        printHeading();

        PersonnelManager capitalistPig = new PersonnelManager();

        fillEmployees(capitalistPig, "EmployeeIn.dat");

        updateEmployees(capitalistPig, "Updates.dat", "EmployeeOut.dat");

        printPayroll(capitalistPig, "HoursWorked.dat", "WeeklyPayroll.txt");

    }

    /*********************************************************************
    * Loops through every line in the input file and adds the employee
    * to the employees array
    * @param inFile A file name which contains all of the employees
    *********************************************************************/
    public static void fillEmployees(PersonnelManager pm, String inFile) {
        Scanner employeeIn = null;
        try {
            employeeIn = new Scanner(new File(inFile));
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }
        employeeIn.useDelimiter("\\s+|, ");
        String lastName;
        String withComma;
        String firstName;
        char indicator;
        double pay;
        String[] line;

        while (employeeIn.hasNextLine() && employeeIn.hasNext()) {
            line = employeeIn.nextLine().split("\\s+|, ");
            try{
                lastName = line[0];
                firstName = line[1];
                indicator = line[2].charAt(0);
                pay = Double.valueOf(line[3]);
            } catch (ArrayIndexOutOfBoundsException e) {
                PersonnelManager.invalidCode(line);
                continue;
            } catch (NumberFormatException e1) {
                PersonnelManager.invalidCode(line);
                continue;
            }
            
            // The `null` means that this employee is from the original inFile and not the update file
            pm.newEmployee(null, lastName, firstName, indicator, pay);
        }

        employeeIn.close();
    }

        /****************************************************************************************
    * Reads in the updates from the update file and changes the employees array accordingly 
    * @param updateFile Name of the file to get updates from
    * @param outputFile Name of the file to print the output
    *****************************************************************************************/
    public static void updateEmployees(PersonnelManager pm, String updateFile, String outputFile) {
        Scanner updates = null;
        try {
            updates = new Scanner(new File(updateFile));
            updates.useDelimiter("\\s+|, ");
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }
        PrintWriter employeeOut = null;
        try {
            employeeOut = new PrintWriter(outputFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

        int counter = 1;
        int raise = 0;
        String[] line;

        // ArrayList might be changed to something more effecient 
        ArrayList<Employee> deletedEmployees = new ArrayList<Employee>();
        ArrayList<Employee> newEmployees = new ArrayList<Employee>();

        char update;

        while (updates.hasNextLine() && updates.hasNext()) {
            line = updates.nextLine().split("\\s+|, ");
            update = line[0].charAt(0);

            switch (update) {
                case 'n':
                case 'N':
                    try{
                        String lastName = line[1];
                        String firstName = line[2];
                        char indicator = line[3].charAt(0);
                        double pay = Double.valueOf(line[4]);
                        pm.newEmployee(newEmployees, lastName, firstName, indicator, pay);
                    } catch (NumberFormatException e) {
                        PersonnelManager.invalidCode(line);
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        PersonnelManager.invalidCode(line);
                        continue;
                    }
                    break;
                case 'd':
                case 'D':
                    try{
                        pm.deleteEmployee(deletedEmployees, line[1]);
                    } catch (IndexOutOfBoundsException e) {
                        PersonnelManager.invalidCode(line);
                        continue;
                    }
                    break;
                case 'r':
                case 'R':
                    int newRaise;
                    try{
                        newRaise = Integer.valueOf(line[1]);
                    } catch (IndexOutOfBoundsException e) {
                        PersonnelManager.invalidCode(line);
                        continue;
                    } catch (NumberFormatException e1) {
                        PersonnelManager.invalidCode(line);
                        continue;
                    }
                    if (newRaise > raise) {
                        raise = newRaise;
                    }
                    break;
                default:
                    PersonnelManager.invalidCode(line);
            }

            counter++;
        }

        for (Employee employee : newEmployees) {
            if (employee != null){
                employeeOut.println("New Employee added: " + employee.getLastName() + ", " + employee.getFirstName());
            }
        }

        if (raise != 0) {
            employeeOut.println("New Wages:");
            pm.printEmployees(employeeOut);
        }

        for (Employee employee : deletedEmployees) {
            if (employee != null){
                employeeOut.println("Deleted Employee: " + employee.getLastName() + ", " + employee.getFirstName());  
            }
        }

        employeeOut.close();
        updates.close();
    }

    /********************************************************************************
    * Output every employee's pay for the week who has hours to the output file
    * @param hoursFile Name of the file containing the employee's hours
    * @param payrollFile Name of the file to print the employee's pay 
    *********************************************************************************/
    public static void printPayroll(PersonnelManager pm, String hoursFile, String payrollFile) {
        PrintWriter payroll = null;
        try {
            payroll = new PrintWriter(payrollFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }
        Scanner hoursWorked = null;
        try {
            hoursWorked = new Scanner(new File(hoursFile));
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

        double total = 0;
        int hours;
        String lastName = null;
        String[] line;

        payroll.println("Paycheck amount:");
        while (hoursWorked.hasNextLine() && hoursWorked.hasNext()) {
            line = hoursWorked.nextLine().split("\\s+|, ");

            try{
                lastName = line[0];
                hours = Integer.valueOf(line[1]);
            } catch (InputMismatchException e0) {
                PersonnelManager.invalidCode(line);
                continue;
            } catch (NoSuchElementException e1) {
                PersonnelManager.invalidCode(line);
                continue;
            } catch (NumberFormatException e2) {
                PersonnelManager.invalidCode(line);
                continue;
            } catch (ArrayIndexOutOfBoundsException e3) {
                PersonnelManager.invalidCode(line);
                continue;
            }

            payroll.println(pm.employeePayroll(lastName, hours));
            total += pm.employeeComputePay(lastName, hours);

        }
        
        payroll.println("                              ----------");

        payroll.println(PersonnelManager.fortyCharacters("Total", String.format("$%.2f", total)));

        hoursWorked.close();
        payroll.close();

    }

    /*******************************************
    * Prints the project information to stdout
    *******************************************/
    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("2/10/18");
        System.out.println("CMSC256 Section 2 Spring");
        System.out.println("Project 2: Program to manage employees");
    }
}
