/**
 * This class represnt a Time1 object from this format: "hh:mm".
 * The Values cant be nagative.
 * 
 * @author Almog Shtaigmann
 * @version 22.03.2022
 * 
 */
public class Time1 {
    // Instance variables:
    private int _hour; 
    private int _minute;

    //final variables:
    private final int MAX_HOUR = 23; 
    private final int MAX_MINUTE = 59; 
    private final int DEF_HOUR_MINUTE = 0; // The default and min value for hour and minute is 0


    private final String ADD_O = "0";
    private final int MAX_1_DIGIT = 9;
    private final int MINUTE_IN_HOUR = 60;
    private final int HOURES_IN_DAY = 24;

    /**
     * Constructor a new Time1
     * Initialize an instances of Time1 with the given hour and minute
     * If the given values are not valid the initialzition will be to a defualte value 0
     * @param h  the hour, must be within the range of 0-23
     * @param m  the minute, must be within the range of 0-59
     */
    public Time1(int h, int m){
        _hour = (isValidHour(h)) ? h:DEF_HOUR_MINUTE;
        _minute = (isValidMinute(m)) ? m:DEF_HOUR_MINUTE;
    }

    /**
     * Copy constructor 
     * Initialize an instance of Time1 identical to the given Time1
     * @param other the obeject that need to copy to the current time
     */
    public Time1(Time1 other){
        this(other._hour,other._minute);
    }
    
    /**
     * Return the value of hour in the Time1 object
     * @return the hour in Time1
     */
    public int getHour(){
        return _hour;
    }

    /**
     * Setter - Change hour value to given number only if its valid
     * if the value is not valid -> the hour will not change.
     * @param num the new hour value
     */
    public void setHour(int num){
        if(isValidHour(num))
            _hour = num;
    }

    /**
     * Return the value of minutes in the Time1 object
     * @return the minutes in Time1
     */
    public int getMinute(){
        return _minute;
    }

    /**
     * * Setter - Change the minute value to given number only if its valid
     * if the value is not valid -> the minute will not change. 
     * @param num the new minute value
     */
    public void setMinute(int num){
        if(isValidMinute(num))
            _minute = num;
    }

    /**
     *  Returns a string representation of this Time1 object -> hh:mm
     */
    public String toString(){
        String s = "";

        // If the hour / minute is between 0-9 its only 1 digit,->
        // so we need to add a '0' before the digit.. 7 -> 07....

        s += (!(_hour > MAX_1_DIGIT)) ? (ADD_O + _hour) : _hour;

        s += ":";

        s += (!(_minute > MAX_1_DIGIT)) ? (ADD_O + _minute): _minute;

        return s;        
    }
    
    /**
     * Calc' and return the number of minute that pass from mindnight (00:00)
     * @return the number of minute that pass form midnight
     */
    public int minFromMidnight(){
        return (_hour * MINUTE_IN_HOUR + _minute);
    }
  
    /**
    * Check if the given time is equal to the given time
    * @param other the given Time1 to check
    * @return True if the given Time1 is identical to the current Time1, otherwise false
    */
    public boolean equals (Time1 other){
        if((_hour == other._hour) && (_minute == other._minute))
            return true;
        else
            return false;
    }

    /**
     * Check if this time is before given time
     * Example: current Time is 07:33, given Time is 11:32 -> return true
     * @param other the given Time1 to check
     * @return true if the current Time is BEFORE the given Time, otherwise false
     */
    public boolean before (Time1 other){
        if(_hour <= other._hour && _minute < other._minute)
                return true;
        else if (_hour < other._hour)
            return true;
        
        return false; // if the program arrived to this part,one of the if's was false
    }

    /**
     * Check if this time is after given time
     * @param other the given Time1 to check
     * @return true if the current time is after the given time, otherwise false
     */
    public boolean after(Time1 other){
        return (other.before(this));
    }

    /**
     * Calc' the difference (in minute) between the current Time to given Time
     * *Assumption: this time is after other time.
     * @param other the given Time
     * @return the difference in minute between the current Time1 to given Time1
     */
    public int difference(Time1 other){
        int timeDiff = minFromMidnight() - other.minFromMidnight();
        
        return Math.abs(timeDiff);
    }

    /**
     * Adding or subtract the given number to/from the current Time1
     * Return a new Time1 after the calc'.
     * 
     * @param num the given amount of minutes to add / subtract
     * @return a new Time1 after change
     */
    public Time1 addMinutes(int num){
        //First we will check how many full hours we nedd to add (num/60)
        //Then we will check if when the add of the remain minutes is passing another hour, and add the remain as minutes
        //In the end we will add the total amount of hour, and minute anf create a new Time1 obejct

        int newHour = DEF_HOUR_MINUTE;
        int newMinute = DEF_HOUR_MINUTE;


        //How many hours and minutes need to add
        int hoursToAdd = num / MINUTE_IN_HOUR;
        int minutesToAdd = num % MINUTE_IN_HOUR;


        //First checking if we cross the valid hour range after adding(after 23, or less then 00)
        //Then checking if we corss the valid minute range after adding(after 59, or less then 00)
        
        //In case that the minutesToAdd + current minutes is over 59 -> new hour
        if(_minute + minutesToAdd > MAX_MINUTE){
            //Takeing the remain minutes from the total amount
           //Exampel -> 67%60 = The new minute will be 07, and + 1 to the hour(more then 60 minutes).

            newMinute += (_minute + minutesToAdd) % MINUTE_IN_HOUR; 
            hoursToAdd++;
        
        //in case we need to subtract minute + cross the hour(less then 00 minutes)
        } else if(_minute + minutesToAdd < DEF_HOUR_MINUTE){
            newMinute += MINUTE_IN_HOUR + (_minute + minutesToAdd); // subtract the remain minutes(num) from 60.
            hoursToAdd--;

        } else {
            newMinute += _minute + minutesToAdd; // any math actiob will not cross the hour
        }


        //In case that the hourToAdd + the current hour is over 24 -> a new day
        if(_hour + hoursToAdd > MAX_HOUR){
            //Takeing the remain hour from the total amount
            //Exampel -> 26%24 = 2. the new hour will be 02.
            newHour += (_hour + hoursToAdd) % HOURES_IN_DAY; 

        //In case that the hourToAdd + current hour is less the 00 -> a day before
        } else if(_hour + hoursToAdd < DEF_HOUR_MINUTE){
            newHour += HOURES_IN_DAY + /* this value is vegative->*/ (_hour + hoursToAdd); //Substract the hour from 24.

        //In case that the hourToAdd + courrent number is within the day.
        } else {
            newHour += _hour + hoursToAdd; 
        }
        
        return (new Time1(newHour,newMinute)); // return a new Time1 after num added
    }


    // Privates method of the class Time 1:
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
}
