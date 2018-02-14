
public class SalariedEmployee extends Employee {

    /****************************************
    * @param first Employee first name
    * @param last Employee last name
    * @param salary Employee salary
    ***************************************/
    public SalariedEmployee(String first, String last, double salary) {
        super(first, last, salary / (40.0 * 52.0));
    }

    /***********************************************
    * Compute the pay for the employee
    * @param hours Hours worked
    ***********************************************/
    public double computePay(double hours) {
        return getWage() * 52;
    }

    /************************************************
    * Set the new salary for that employee
    * @param salary New employee salary
    *************************************************/
    public void setSalary(double salary) {
        setWage(salary / (40 * 52));
    }

    /************************************
    * Get the salary of the employee
    ************************************/
    public double getSalary() {
        return getWage() * (40 * 52);
    }

    /***********************************************************
    * @return A string in the format last_name, first_name $pay
    ************************************************************/
    @Override
    public String toString() {
        StringBuilder stringify = new StringBuilder();
        stringify.append(getLastName());
        stringify.append(", ");
        stringify.append(getFirstName());

        while (40 - (stringify.length() + String.format("$%.2f", getSalary()).length()) > 0) {
            stringify.append(" "); // Add a space until the 40 character limit is reached
        }

        stringify.append(String.format("$%f", getSalary()));

        return stringify.toString();
    }

}
