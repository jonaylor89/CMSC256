
public class HourlyEmployee extends Employee {

    /****************************************
    * @param first Employee first name
    * @param last Employee last name
    * @param hourlyWage Employee wage
    ***************************************/
    public HourlyEmployee(String first, String last, double hourlyWage) {
        super(first, last, hourlyWage);
    }

    /***********************************************
    * Compute the pay for the employee
    * @param hours Hours worked
    ***********************************************/
    public double computePay(double hours) {

        if (hours > 40) {
            return getWage() * (40 + 1.5 * (hours-40));
        }

        return getWage() * hours;
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

        while (40 - (stringify.length() + String.format("$%.2f", getWage()).length()) > 0) {
            stringify.append(" ");
        }

        stringify.append(String.format("$%f", getWage()));

        return stringify.toString();
    }

}
