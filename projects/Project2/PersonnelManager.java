
public class PersonnelManager {

    private Employee[] employees;
    
    public PersonalManager() {
        employees = { null };
    }

    private static Employee[] doubleArray(Employee[] smallArray) {
        Employee[] bigArray = new Employee[2 * smallArray.length];  

        for (int i = 0; i < smallArray.length; i++) {
            bigArray[i] = smallArray[i]; 
        }

        return bigArray;
    }
}
