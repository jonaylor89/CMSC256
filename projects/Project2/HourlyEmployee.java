
public class HourlyEmployee extends Employee {

    public HourlyEmployee(String first, String last, double hourlyWage) {
        super(first, last, hourlyWage);
    }

    public double computePay(double hours) {

        if (hours > 40) {
            return wage * (40 + 1.5 * (hours-40));
        }

    }

    @Override
    public String toString() {
        return String.format("%s, %s %f/hour", lastName, firstName, wage);
    }

}
