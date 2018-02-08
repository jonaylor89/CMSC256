
public abstract class Employee {

    private String firstName;
    private String lastName;
    private double wage;

    public Employee() {
        firstName = "";
        lastName = "";
        wage = 0.0;
    }
    
    public Employee(String first, String last, double hourlyWage) {
        firstName = first;
        lastName = last;
        wage = hourlyWage;
    }

    public abstract double computePay(double hours);

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName; 
    }

    public String getName() {
        return lastName + ", : + firstName"; 
    }

    public double getWage() {
        return wage; 
    }

    public void setFirstName(String name) {
        firstName = name; 
    }

    public void setLastName(String name) {
        lastName = name; 
    }

    public void setWage(double wage) {
        this.wage = wage; 
    }

    public void raiseWage(double percent) {
        wage = wage * ((100 + percent) / 100) ;
    }

}
