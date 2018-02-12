
public class SalariedEmployee extends Employee {

    /**
    * 
    */
    public SalariedEmployee(String first, String last, double salary) {
        super(first, last, salary / (40.0 * 52.0));
    }

    /**
    * 
    */
    public double computePay(double hours) {
        return getWage() * 52;
    }

    /**
    * 
    */
    public void setSalary(double salary) {
        setWage(salary / (40 * 52));
    }

    /**
    * 
    */
    public double getSalary() {
        return getWage() * (40 * 52);
    }

    /**
    * 
    */
    @Override
    public String toString() {
        return String.format("%s, %s %f/year", getLastName(), getFirstName(), getWage() * (52 * 40));
    }

}
