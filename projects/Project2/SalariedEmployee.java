
public class SalariedEmployee extends Employee {

    public SalariedEmployee(String first, String last, double wage) {

        double hourly = wage / (40.0 * 52.0);

        super(first, last, hourly);
    }

    public double computePay(double hours) {
    
    }

    @Override
    public String toString() {
    
    }

}
