/*****************************************
 * John Naylor
 * CMSC 256 Section 2 Spring
 * 
 * 
 ****************************************/

public class Project2 {

    /**
    * 
    */
    public static void main(String[] argv) {
        printHeading();

        PersonnelManager capitalistPig = new PersonnelManager();

        capitalistPig.fillEmployees("EmployeeIn.dat");

        capitalistPig.parseUpdates("Updates.dat");

        capitalistPig.printOut("EmployeeOut.dat");

        capitalistPig.printPayroll("HoursWorked.dat", "WeeklyPayroll.txt");

    }

    /**
    * 
    */
    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("2/10/18");
        System.out.println("CMSC256 Section 2 Spring");
        System.out.println("Project 2: Program to manage employees like a true capitalist pig");
    }
}
