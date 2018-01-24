
public class CustomDateTest {
    public static void main(String[] argv) {
        CustomDate cdDefault = new CustomDate();
        CustomDate cdValues = new CustomDate(1, 24, 18);
        CustomDate cdValues2 = new CustomDate(1, 24, 18);
        
        try{
            CustomDate invalidDate = new CustomDate(1, 44, 18);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

         try{
            CustomDate invalidDate = new CustomDate(14, 10, 18);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try{
            CustomDate invalidDate = new CustomDate(1, 8, -18);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try{
            CustomDate invalidDate = new CustomDate(-7, -9, -18);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }



        System.out.println(cdDefault.isLeapYear());
        System.out.println(cdValues.isLeapYear());

       System.out.println(cdDefault.equals(cdValues));
       System.out.println(cdValues.equals(cdDefault));
       System.out.println(cdValues.equals(cdValues2));

       System.out.println(cdDefault.compareTo(cdValues));
       System.out.println(cdValues.compareTo(cdDefault));
       System.out.println(cdValues.compareTo(cdValues2));

       cdValues.advanceOneWeek();
       cdValues.advanceOneWeek();

       System.out.println(cdValues);
       System.out.println(cdDefault);

    }
}
