
public class SalariedEmployee extends Employee {

    public SalariedEmployee(String first, String last, double salary) {

        double hourly = salary / (40.0 * 52.0);

        super(first, last, hourly);
    }

    public double computePay(double hours) {
        return wage * 52;
    }

    public void setSalary(double salary) {
        wage = salary / (40 * 52);
    }

    public double getSalary() {
        return wage * (40 * 52);
    }

    @Override
    public String toString() {
        return String.format("%s, %s %f/year", lastName, firstName, wage * (52 * 40));
    }

}
