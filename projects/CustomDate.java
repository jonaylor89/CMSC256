/**************************************************
 * John Naylor
 * CMSC256 Section 2 * Project 1 CustomDate
 * CustomeDate class for creating calendar dates
 ************************************************/

public class CustomDate implements Comparable{
    private int month;    
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
        if(isValidDate(aMonth, aDay, yr)){
            setMonth(aMonth);
            setDay(aDay);
            setYear(yr);
        } else {
            throw new IllegalArgumentException("Invalid Date");
        }
    }

    public boolean isValidDate(int aMonth, int aDay, int yr) {
        if(yr < 0) {
            return false; 
        }

        if(aMonth < 0 || aMonth > 12) {
            return false;
        }

        if(aDay < 0 || aDay > numDayInMonth(aMonth)) {
            return false; 
        }

        return true;
    }


/****************************************
  * Returns the month of this Date
  * @return month 
  **************************************/
    public int getMonth(){
        return month;
    }
 /*****************************************
  * Returns the day for this Date
  * @return day
  ****************************************/
    public int getDay(){
        return day;
    }

 /**************************************************
  * Returns the year of this Date
  * @return year
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
        if(isValidDate(aMonth, day, year)) {
            month = aMonth; 
        } else {
            throw new IllegalArgumentException("Invalid month");
        }
    }

 /*************************************************************
  * Sets the day of the Date
  * @param aDay  an day name
  * @throws IllegalArgumentException invalid day arguments
  ***********************************************************/
    public void setDay(int aDay){
        if(isValidDate(month, aDay, year)){
            day = aDay;
        } else {
            throw new IllegalArgumentException("Invalid day");
        } 

 }

 /***************************************************************
  * Sets the year of the Date
  * @param newYear  an year name
  * @throws IllegalArgumentException year arguments less than 1
  *************************************************************/
    public void setYear(int newYear){
        if(isValidDate(month, day, newYear)) {
            year = newYear; 
        } else {
            throw new IllegalArgumentException("Invalid Year");
        }
    }
/********************************************
 * Check whether the year is a leap year
 * @return boolean
 ********************************************/
    public boolean isLeapYear() {
        if(year % 4 == 0) {
            if(year % 100 == 0) {
                if(year % 400 == 0) {
                    return true; 
                }
            }
            return true;
        }

        return false;
    } 

    public int numDayInMonth(int aMonth) {
        int NumDays = 0;

        switch(aMonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                NumDays = 31;
            case 4:
            case 6:
            case 9:
            case 11:
               NumDays = 30; 
            case 2:
                if(isLeapYear()) {
                    return NumDays = 29; 
                } else {
                    return NumDays = 28; 
                } 
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
        
    }

/***************************************************************
 * Determines whether it's the last day of the month or not
 * @return boolean of if it's the last day
 ***************************************************************/
    public boolean endOfMonth() {
        return day == numDayInMonth(month);
    }
/*******************************************************
 * Determines if it's the end of the year
 * @return boolean of the month being 12 or not
 ******************************************************/
    public boolean endOfYear() {
        return month == 12; 
    }

/********************************************** 
 * Advances the day by one in the year
 *********************************************/
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
/****************************************************************
 * Advances week by one by advancing the day by one seven times
 ***************************************************************/
    public void advanceOneWeek() {
        for(int i = 0; i < 7; i++) {
            advanceOneDay();
        }
    }

/****************************************
 * @return if two objects are equal
 **************************************/
    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true; 
        }

        if(obj == null) {
            return false; 
        }


        CustomDate cd = (CustomDate) obj;
        return cd.getDay() == this.day && cd.getMonth() == this.month && cd.getYear() == this.year;
    }

/*******************************************
 * @return comparison of two objects
 *****************************************/
    @Override
    public int compareTo(Object obj) {
        final int LESS = -1;
        final int EQUAL = 0;
        final int GREATER = 1;

        if(this == obj) {
            return EQUAL; 
        }

        if(obj == null) {
            return -1; 
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
        String output = String.format("%02d/%02d/%04d", month, day, year);
        return output;
    }
}
