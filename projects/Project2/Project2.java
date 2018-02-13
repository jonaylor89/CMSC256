/*****************************************
 * John Naylor
 * CMSC 256 Section 2 Spring
 * 
 * 
 ****************************************/

public class Project2 {

    /***********************************************************
    * Fill up the employees array for a personnel manager;
    * Updates all of the employees in it;
    * Outputs their payroll to a txt file;
    ***********************************************************/
    public static void main(String[] argv) {
        printHeading();

        PersonnelManager capitalistPig = new PersonnelManager();

        capitalistPig.fillEmployees("EmployeeIn.dat");

        capitalistPig.updateEmployees("Updates.dat", "EmployeeOut.dat");

        capitalistPig.printPayroll("HoursWorked.dat", "WeeklyPayroll.txt");

    }

    /*******************************************
    * Prints the project information to stdout
    *******************************************/
    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("2/10/18");
        System.out.println("CMSC256 Section 2 Spring");
        System.out.println("Project 2: Program to manage employees like a true capitalist pig");
    }
}
