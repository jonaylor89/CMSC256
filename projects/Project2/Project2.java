/*****************************************
 * John Naylor
 *
 ****************************************/

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Project2 {

    static Scanner employeeIn = new Scanner("EmployeeIn.dat");
    static Scanner hoursWorked = new Scanner("HoursWorked.dat");
    static Scanner updates = new Scanner("Updates.dat");
    static PrintWriter employeeOut = new PrintWriter("EmployeeOut.dat");
    static PrintWriter payroll = new PrintWriter("WeeklyPayroll.txt")

    public static void main(String[] argv) {
        printHeading();

        PersonnelManager capitalistPig = new PersonnelManager();
    }

    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("2/5/18");
        System.out.println("CMSC256 Section2 Spring");
        System.out.println("Project 2: Program to manage employees like a true capitalist pig");
    }
}
