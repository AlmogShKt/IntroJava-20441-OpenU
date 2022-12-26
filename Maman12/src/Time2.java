
/**
 * This class represnt a Time2 object
 * 
 * @author Almog Shtaigmann
 * @version 22.03.2022
 * 
 */
public class Time2 {
     // Instance variables:
     private int _minFromMid; 
 
     //final variables:
     private final int MAX_HOUR = 23; 
     private final int MAX_MINUTE = 59; 
     private final int DEF_HOUR_MINUTE = 0; // the defulte and min value for hour and minute is 0
 
 
     private final String ADD_O = "0";
     private final int MAX_1_DIGIT = 9;
     private final int MINUTE_IN_HOUR = 60;
     private final int HOURES_IN_DAY = 24;


     /**
     * Constructor a new Time2
     * Initialize an instance of Time2 with the given hour and minute to the total mumber of minute
     * If the given values are not valid the initialzition will be to a def value 0
     * @param h  the hour, must be within the range of 0-23
     * @param m  the minute, must be within the range of 0-59
     */
    public Time2(int h, int m){
        h = (isValidHour(h)) ? h:DEF_HOUR_MINUTE;
        m = (isValidMinute(m)) ? m:DEF_HOUR_MINUTE;
        
        _minFromMid = h * MINUTE_IN_HOUR + m;
    }

    /**
     * Copy constructor 
     * Initialize an instance of Time2 identical to the given Time2
     * @param other the obeject that need to copy to the current time
     */
    public Time2(Time2 other){
        _minFromMid = other._minFromMid;
    }

     /**
     * Return the value of hour in the Time2 object
     * @return the hour
     */
    public int getHour(){
        return (getHourFromTotal());
    }

    /**
     * Setter - Change hour value to given number only if its valid
     * if the value is not valid -> the hour will not change.
     * @param num the new hour
     */
    public void setHour(int num){
        if(isValidHour(num)){
            int min = getMinFromTotal();
            _minFromMid = 0;
            _minFromMid = num * MINUTE_IN_HOUR + min;
        }
    }

    /**
     * Return the value of minute in the Time2 object
     * @return the minute
     */
    public int getMinute(){
        return (getMinFromTotal());
    }

    /**
     * Return the value of minutes in the Time2 object
     * if the value is not valid -> the minutes will not change. 
     * @param num the new minute
     */
    public void setMinute(int num){
        if(isValidMinute(num)){
            int hours = getHourFromTotal();
            _minFromMid = 0;
            _minFromMid = (hours*MINUTE_IN_HOUR + num);
        }
    }

    /**
     *  Returns a string representation of the Time2 object -> hh:mm
     */
    public String toString(){
        String s = "";
        int min = getMinFromTotal();
        int hours = getHourFromTotal();


        // If the hour / minute is between 0-9 its only 1 digit,
        // so we need to add a '0' before the digit.. 7 -> 07....

        s += (!(hours > MAX_1_DIGIT)) ? (ADD_O + hours): hours;        

        s += ":";

        s += (!(min > MAX_1_DIGIT)) ? (ADD_O + min) : min;
       
        return s;        
    }
    
    /**
     * Calc the number of minute that pass from mindnight (00:00)
     * @return the number of minute that pass form midnight
     */
    public int minFromMidnight(){
        return _minFromMid;
    }
  
    /**
    * Return true if the given Time2 is identical to the current Time2 for all attributes(hour and minute)   
    * @param other the given Time2 to check
    * @return True if the given Time2 is identical to the current Time2, otherwise false
    */
    public boolean equals (Time2 other){
        int min = getMinFromTotal();
        int hours = getHourFromTotal();

        if((hours == other._minFromMid / MINUTE_IN_HOUR) &&
          (min == other._minFromMid % MINUTE_IN_HOUR))
            return true;
        else
            return false;
    }

    /**
     * Check if this time is before given time
     * Example: current Time2 is 07:33, given Time2 is 11:32 -> return true
     * @param other the given Time2 to check
     * @return true if the current Time is BEFORE the given Time, otherwise false
     */
    public boolean before (Time2 other){
        int min = getMinFromTotal();
        int hours = getHourFromTotal();

        if((hours <= other._minFromMid / MINUTE_IN_HOUR ) && min < other._minFromMid)
            return true;
        else if(hours < other._minFromMid / MINUTE_IN_HOUR )
            return true;
        
        return false; // if the program arrived to this part,one of the if's was false
    }

    /**
     * Check if this time is after given time
     * @param other the given Time2 to check
     * @return true if the current time is after the given time, otherwise false
     */
    public boolean after(Time2 other){
        return (other.before(this));
    }

    /**
     * Calc' the difference (in minute) between the current Time2 to given Time2
     * @param other the given Time2
     * @return the difference in minute between the current Time2 to given Time2
     */
    public int difference(Time2 other){
        int timeDiff = _minFromMid - other._minFromMid;
        
        return Math.abs(timeDiff);

        
    }


    /**
     * Adding or subtract the given number to/from the current Time1
     * Return a new Time1 after the calc'.
     * 
     * @param num the given amount of minutes to add / subtract
     * @return a new Time2 after change
     */
    public Time2 addMinutes(int num){
        //First we will check how many full hours we nedd to add (num/60)
        //Then we will check if when the add of the remain minutes is passing another hour, and add the remain as minutes
        //In the end we will add the total amount of hour, and minute anf create a new Time1 obejct

        int min = getMinFromTotal();
        int hours = getHourFromTotal();

        int newHour = DEF_HOUR_MINUTE;
        int newMinute = DEF_HOUR_MINUTE;

        //How many hours and minutes need to add
        int hoursToAdd = num / MINUTE_IN_HOUR;
        int minutesToAdd = num % MINUTE_IN_HOUR;


        //First checking if we cross the valid hour range after adding(after 23, or less then 00)
        //Then checking if we corss the valid minute range after adding(after 59, or less then 00)
        
        //In case that the minutesToAdd + current minutes is over 59 -> 1 needs to be add for the hour
        if(min + minutesToAdd > MAX_MINUTE){
            //takeing the remain minutes from the total amount
            //Exampel -> 67%60 = The new minute will be 07, and + 1 to the hour(more then 60 minutes).

            newMinute += (min + minutesToAdd) % MINUTE_IN_HOUR; 
            hoursToAdd++;
        
        //in case we need to subtract minute + cross the hour(less then 00 minutes)
        } else if(min + minutesToAdd < DEF_HOUR_MINUTE){
            newMinute += MINUTE_IN_HOUR + (min + minutesToAdd); // subtract the remain minutes(num) from 60.
            hoursToAdd--;

        } else {
            newMinute += min + minutesToAdd; // any math actiob will not cross the hour
        }


        //In case that the hourToAdd + the current hour is over 24 -> a new day
        if(hours + hoursToAdd > MAX_HOUR){
            //Takeing the remain hour from the total amount
            //Exampel -> 26%24 = 2. the new hour will be 02.
            newHour += (hours + hoursToAdd) % HOURES_IN_DAY; 

        //In case that the hourToAdd + current hour is less the 00 -> a day before
        } else if(hours + hoursToAdd < DEF_HOUR_MINUTE){
            newHour += HOURES_IN_DAY + /* this value is vegative->*/ (hours + hoursToAdd); //Substract the hour from 24.

        //In case that the hourToAdd + courrent number is within the day.
        } else {
            newHour += hours + hoursToAdd; 
        }
        
        return (new Time2(newHour,newMinute)); // return a new Time1 after num added
    }





    // Privates method of the class Time 2:
    // Use's only in the class

    /**
     * Checking if the hour value is valid (0-23) 
     * @param h is the hour 
     * @return true if the value is valid otherwise false
     */
    private boolean isValidHour(int h){
        boolean isValidHour = (h >= DEF_HOUR_MINUTE && h <= MAX_HOUR) ? true:false;
        return isValidHour;
    }

    /**
     * Checking if the minute value is valid(0-59)
     * @param m is the minute
     * @return true if the value is valid otherwise false
     */
    private boolean isValidMinute(int m){
        boolean isValidMinute = (m >= DEF_HOUR_MINUTE && m <= MAX_MINUTE) ? true:false;
        return isValidMinute;
    }

    /**
     * Use the instance variable in the object , calc' & return the number of minutes
     * @return the number of minutes in the object Time2
     */
    private int getMinFromTotal(){
        return _minFromMid % MINUTE_IN_HOUR;
    }

    /**
     * Use the instance variable in the object , calc' & return the number of hours
     * @return the number of hours in the objects Time2
     */
    private int getHourFromTotal(){
        return _minFromMid / MINUTE_IN_HOUR;
    }
}
