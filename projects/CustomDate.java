

public class CustomDate implements Comparable{
    private int month;     //instance variables
    private int day;
    private int year;

 /************************************************************************
  * Default constructor for a Date that sets the date to 1/1/2000
  ********************************************************************/
    public CustomDate(){
        month =  1;
        day =  1;
        year = 2000;
    }

 /************************************************************
  * Parameterized constructor for a Date
  * Calls the mutator methods to set the instance variables
  * @param aMonth month
  * @param aDay   day
  * @param yr   year
  ***********************************************************/
    public CustomDate(int aMonth, int aDay, int yr){
        setMonth(aMonth);
        setDay(aDay);
        setYear(yr);
    }


/****************************************
  * Returns the month of this Date
  * @return int
  **************************************/
    public int getMonth(){
        return month;
    }
 /*****************************************
  * Returns the day for this Date
  * @return int
  ****************************************/
    public int getDay(){
        return day;
    }

 /**************************************************
  * Returns the year of this Date
  * @return int
  ***********************************************/
    public int getYear(){
        return year;
    }

    /*************************************************************
  * Sets the month of the Date
  * @param aMonth  a month
  * @throws IllegalArgumentException invalid month arguments
  ***************************************************************/
    public void setMonth(int aMonth){
        if(aMonth < 1 || aMonth > 12){
            throw new IllegalArgumentException("Invalid month");
        }
        month = aMonth;
    }

 /**
  * Sets the day of the Date
  * @param aDay  an day name
  * @throws IllegalArgumentException invalid day arguments
  */
    public void setDay(int aDay){
        if(aDay  <  1 ||  aDay  >  31 ){
            throw new IllegalArgumentException("Invalid year");
        }
        day =  aDay;
 }

 /***************************************************************
  * Sets the year of the Date
  * @param newYear  an year name
  * @throws IllegalArgumentException year arguments less than 1
  *************************************************************/
    public void setYear(int newYear){
        if(newYear < 1){
            throw new IllegalArgumentException("Invalid year");
        }
        year = newYear;
    }

    public boolean isLeapYear() {
        if(year % 4 == 0) {
            if(year % 100 == 0) {
                if(year % 400 == 0) {
                    return true; 
                }
            }
        }

        return false;
    } 

    public boolean endOfMonth() {
        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return day == 31;
            case 4:
            case 6:
            case 9:
            case 11:
               return day == 30; 
            case 2:
                if(isLeapYear()) {
                    return day == 29; 
                } else {
                    return day == 28; 
                } 
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    public boolean endOfYear() {
        return month == 12; 
    }

    public void advanceOneDay() {
        if(endOfMonth()) {
            if(endOfYear()) {
                year += 1; 
                month = 1;
                day = 1;
            }else {
                month += 1;
                day = 1;
            }
        }else {
            day += 1;    
        }
    }

    public void advanceOneWeek() {
        for(int i = 0; i < 7; i++) {
            advanceOneDay();
        }
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return EQUAL; 
        }

        if(obj == null) {
            return false; 
        }


        CustomDate cd = (CustomDate) obj;
        return cd.getDay() == this.day && cd.getMonth() == this.month && cd.getYear() == this.year;
    }
    
    @Override
    public int compareTo(Object obj) {
        final int LESS = -1;
        final int EQUAL = 0;
        final int GREATER = 1;

        if(this == obj) {
            return EQUAL; 
        }

        if(obj == null) {
            return false; 
        }

        CustomDate cd = (CustomDate) obj;

        // Compare year first
        if(this.year < cd.getYear()) { return LESS; }
        if(this.year > cd.getYear()) { return GREATER; }
        
        // Then month
        if(this.month < cd.getMonth()) { return LESS; }
        if(this.month > cd.getMonth()) { return GREATER; }

        // Finally day
        if(this.day < cd.getDay()) { return LESS; }
        if(this.day > cd.getDay()) { return GREATER; }

        // Final check to make sure everything is working
        assert this.equals(cd) : "compareTo inconsistent with equals.";

        return EQUAL;
    }

 /***********************************************************************
  * @return The month, day, and year information for this Date
  *****************************************************************/
  @Override
    public String toString() {
        String output;
        output = "SimpleDate: " + month + "\\"+ day;
        output  = output + "\\"+ year + "\n";
        return output;
    }
}
