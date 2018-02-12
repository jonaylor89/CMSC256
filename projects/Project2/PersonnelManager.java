
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class PersonnelManager {

    private Employee[] employees;
    
    public PersonnelManager() {
        employees = new Employee[1];
        
    }

    public void parseIn(String inFile) {
        Scanner employeeIn = new Scanner(inFile);
        String lastName;
        String firstName;
        char indicator;
        int pay;

        int index = 0;

        while (employeeIn.hasNextLine && employeeIn.hasNext()) {
            lastName = employeeIn.next().substring(length-2); 
            firstName = employeeIn.next();
            indicator = employeeIn.next().charAt(0);
            pay = employeeIn.nextInt();

            if (indicator == 'h') {
                employees[index] = new HourlyEmployee(lastName, firstName, pay);
            } else if (indicator == 's') {
                employees[index] = new SalariedEmployee(last, firstName, pay);
            } else {
                employees[index] = null;
            }

            index++;
        }

    }

    public void parseUpdates(String updateFile) {
        Scanner hoursWorked = new Scanner(updateFile);

    }

    public void printOut(String outFile) {
        try {
            PrintWriter employeeOut = new PrintWriter(outFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

    }

    public void parseHours(String hoursFile) {
        Scanner hoursworked = new Scanner(hoursFile);

    }

    public void printPayroll(String payrollFile) {
        try {
            PrintWriter payroll = new PrintWriter(payrollFile);
        } catch(FileNotFoundException e) {
            System.out.println("[!!!] File not Found");
            System.out.println(e);
        }

    }

    private static Employee[] doubleArray(Employee[] smallArray) {
        Employee[] bigArray = new Employee[2 * smallArray.length];  

        for (int i = 0; i < smallArray.length; i++) {
            bigArray[i] = smallArray[i]; 
        }

        return bigArray;
    }
}
