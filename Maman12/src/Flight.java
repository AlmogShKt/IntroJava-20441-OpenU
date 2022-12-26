/**
 * This class represnet a Flight object
 * @author Almog Shtaigmann
 * @Version 10.3.2022
 */
public class Flight {

    // Instance variables:
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;

    //final variables:
    private final int MAX_CAPACITY = 250;
    private final int DEF_VALUE = 0;

    /**∫
     * Flight constructor
     * Initialize an instances of Flight with follwing params:
     * @param origin The departure city 
     * @param destination the destination city
     * @param depHour the hour of departure (hh:mm), in order to create a Time1 object
     * @param depMinute the of departure (hh:mm), in order to create a Time1 object
     * @param flightDuration the flight duration, in minutes
     * @param noOfPassengers the num of passanger in the flight]
     * @param price the price of 1 ticket in the flight
     */
    public Flight(String origin, String dest, int depHour, int depMinute, int flightDuration, int noOfPass, int price){
        setOrigin(origin);

        setDestination(dest);

        setDeparture(new Time1(depHour, depMinute));
        
        setFlightDuration(flightDuration);

        _noOfPassengers = (noOfPass > DEF_VALUE) ? noOfPass:DEF_VALUE;
        _noOfPassengers = (_noOfPassengers >= MAX_CAPACITY) ? MAX_CAPACITY:_noOfPassengers;

        _isFull = (_noOfPassengers >= MAX_CAPACITY) ? true:false;

        setPrice(price);
        
    }

    /**
     * Copy constructor
     * Initialize an instances of Flight identical to the given Flight
     * @param other the given Flight object
     */
    public Flight(Flight other){
        this(other._origin, other._destination, other._departure.getHour(),other._departure.getMinute(),
             other._flightDuration, other._noOfPassengers,other._price);
    }
    
    /**
     * Return the flight origin
     * @return the origin (name of city)
     */
    public String getOrigin(){
        return _origin;
    }

    /**
     * Change the flight's origin 
     * @param origin the new origin
     */
    public void setOrigin(String origin){
        _origin = origin;
    }

    /**
     * Return the flight's destination
     * @return the destination (name of city)
     */
    public String getDestination(){
        return _destination;
    }

    /**
     * Change the flight's destination
     * @param destination the new destination
     */
    public void setDestination(String destination){
        _destination = destination;
    }


     /**
     * Return the departure time
     * @return the departure time, a new Time1 object
     */
    public Time1 getDeparture(){
        return  new Time1(_departure);
    }

    /** 
     * Change the departure time of the flight(an Time1 object)
     * @param other the new departure time
     */
    public void setDeparture(Time1 other){
        _departure = new Time1(other);
    }

    /**
     * Return the flight Duration in minutes.
     * @return the flight Duration
     */
    public int getFlightDuration(){
        return _flightDuration;
    }

    /**
     * Change the flight's time.
     * If the value is negative, 0 will be the value, otherwise the given flight Duration.
     * @param flightDuration the given flight duration in minutes
     */
    public void setFlightDuration(int flightDuration){
        _flightDuration = (flightDuration < DEF_VALUE) ? DEF_VALUE:flightDuration;
    }

    /**
     * Return theamount of passangers in the flight
     * @return the num of passangers
     */
    public int getNoOfPassengers(){
        return _noOfPassengers;
    }

    /**
     * Change the number of passangers in the flight.
     * If the number is more then the max capacity, the max capacuty will be the value.
     * If the number is negative, the value will set to 0.
     * Check and update if after adding the passanger the flights is full.
     * @param noOfPass the given number of passangers theat needs to be update
     */
    public void setNoOfPassengers(int noOfPass){
        _noOfPassengers = (noOfPass > MAX_CAPACITY || noOfPass < 0 ) ? _noOfPassengers:noOfPass;

        _isFull = (_noOfPassengers < MAX_CAPACITY) ? false:true;
    }

    /**
     * Return if the flight's is full.
     * @return the true is the flight is full otherwise fasle
     */
    public boolean getIsFull(){
        return _isFull;
    }

    /**
     * Return the price of 1 ticket in the flight
     * @return the price of 1 ticket
     */
    public int getPrice(){
        return _price;
    }

      /**
      * Set the price value as the given value
      * @param price the given price value
      */
    public void setPrice(int price){
        _price = (price < DEF_VALUE) ? DEF_VALUE:price;
    }
    
    /**
    * Check if the current flight is the same as the given flight
    * Return true if the given flight is identical to the current flight for follwing attributes:
    * Origin, destantion, and departure time
    * @param other
    * @return True if the given flight is identical to the current flight, otherwise false
     */
    public boolean equals(Flight other){
        if((_origin == other._origin) && (_destination == other._destination) && (_departure.equals( other._departure)))
            return true;
        else
            return false;
    }
    /**
     * Retuen a new time that represent the landing time.
     * @return a Time1 object that represent the landing time
     */
    public Time1 getArrivalTime(){
        return (_departure.addMinutes(_flightDuration));
    }

    /**
     * Add passangers to the flight. If the new amount of passangers is over the max capacity nothing will change.
     * @param num the given number of passanger to add.
     * @return true if the flight became full, otherwise false(there is avillibale seats on the flight). 
     */
    public boolean addPassengers(int num){
        if(num + _noOfPassengers <= MAX_CAPACITY)
            setNoOfPassengers(num + _noOfPassengers); //Use's the Set method for the update
        
        return _isFull;
    }

    /**
     * Check if the current flight is cheper then the given flight
     * @param other the given flight
     * @return true if the price of ticket in the current flight is cheper the the given fligh, otherwise false
     */
    public boolean isCheaper(Flight other){
        return (_price < other._price);
    }

    /**
     * Return the total amount of money that recived from the all tickets
     * @return the amount of monuy from all the passanger in the flight, price of 1 ticket * amount of passangers
     */
    public int totalPrice(){
        return (_noOfPassengers * _price);
    }

    /**
     * 
     * Check if the current flight land before the given flight.
     * @param other the given flight
     * @return true if the currnt flight land before the given flight, otherwise return false
     */
    public boolean landsEarlier(Flight other){
        Time1 curruntLandTime = getArrivalTime();
        Time1 givenLandTime = other.getArrivalTime();

        return (curruntLandTime.before(givenLandTime));
    }

    /**
     * Return a string representation of Flight.
     * By the following foramt:
     * Flight from _origin to _destination departs at _departure. Flight is not/full.
     */
    public String toString(){
        String s = "";

        s += " Flight from " + _origin + " to " + _destination + " departs at " + _departure + ". ";
        s += (_isFull) ? "Flight is full.":"Flight is not full.";
        
        return s;
    }

    
}
