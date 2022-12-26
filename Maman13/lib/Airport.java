/**
 * This Class represent a fight board in airport.
 * 
 * @author Almog Shtaigmann
 * @version 14.4.22
 */
public class Airport {

    // Instance variables:
    private Flight[] _flightsSchedule;
    private int _noOfFlight; 
    private String _city;

    //Finales variables: 
    private final int MAX_FLIGHTS = 200; //The max flight in 1 day;
    private final int DEF_VALUE = 0;
    private final int VALUE_NOT_EXIST_INDEX = -1; //In case that the value is not exsit in the array, use -1 for this usecase

    /**
     * Airport constructor.
     * Initialize an instances of Airport. 
     * The max flights number in 1 day is 200.
     * @param city The airport location(city name).
     */
    public Airport(String city){
        _city = city;

        _flightsSchedule = new Flight[MAX_FLIGHTS]; // Set the board for the max amount of flight(200)

        _noOfFlight = DEF_VALUE;
    }


    /**
     * Add flight to the flights board, only if its not full and the origin/destination is from/to the airort city.
     * @param f the new flight to add.
     * @return True if the flight added, otherwise false(full or not from\to airport city).
     */
    public boolean addFlight(Flight f){
        // If there flight board is full - cant add more flight
        if(_noOfFlight == MAX_FLIGHTS){ 

            return false;

        // If the destination or the origin is not from\to the airport city - cant add this flight
        } else if (f.getDestination().equals(_city) == false && f.getOrigin().equals(_city) == false) { 
            return false;
        
        //The flight can be add to the flight board
        } else {
            _flightsSchedule[_noOfFlight++] = new Flight(f);
            return true;
        }
    }


    /**
     * Remove a given flight from the flights board.
     * If the flight dose not exist the method returns false.
     * @param f the given flight to remove.
     * @return true is the flight removed, othewise false(there is no flights or the flight dose not exist).
     */
    public boolean removeFlight(Flight f){
        //First search for the flight
        //Then remove this flight by set the last flight in the remove flight position.
        //If the flight is not exsit or there is no flights -> return flase.

        if(_noOfFlight > DEF_VALUE){ //Remove flight only if thers is flights.. 
            
            int removeFrom = getIndex(f); // Will hold the index of the remove flight; if the index is ['-1'] -> the flight was not found!
                    
            if(removeFrom != VALUE_NOT_EXIST_INDEX){ //Only if the flight found in the flight borad(not '-1')
                _flightsSchedule[removeFrom] = _flightsSchedule[_noOfFlight-1];//Replace the wanted flught with the last flight(last in the array).
                _flightsSchedule[(_noOfFlight-1)] = null; //Set the 'extra' place is null.
                _noOfFlight--; //Update the number of flight in the airport.

                return true;

            } else{
                System.out.println("Not is board"); //If the program arrived to this part, the flight 'f' was not in the flights board.

                return false;
            }

        } else // If there is no flights, we cant remove..
            return false; 
    }
    

    /**
     * Find and return the time of the first flight from given city to the airport
     * @param place the given city to check
     * @return the time of the earliest flight that land on the airport from given city. if there is no flight from place return null.
     */
    public Time1 firstFlightFromOrigin(String place){
        boolean foundDefFlight = false; // after finding the first flight that lands in the city, change to true, and then check if this the earliest flight to this/from place.
        int indexForEarlFligh = VALUE_NOT_EXIST_INDEX;

        for(int i = 0; i < _noOfFlight; i++){

            if(_flightsSchedule[i].getOrigin().equals(place) && !foundDefFlight){
                indexForEarlFligh = i;
                foundDefFlight = true;
            } 

            if(foundDefFlight){
                //Check if the flight in index 'i' is from city AND if the departure time is before the other flight from the city.
                if(_flightsSchedule[i].getOrigin().equals(place) && _flightsSchedule[i].getDeparture().before(_flightsSchedule[indexForEarlFligh].getDeparture())){

                    //save the index of this fligh - in the end this index will represent the earliest flight from city.
                    indexForEarlFligh = i;
                }
            }            
        }
        //Only if flight found(the index is not -1), return the departure time of this flight(from the index)
        return (indexForEarlFligh == VALUE_NOT_EXIST_INDEX) ? null : _flightsSchedule[indexForEarlFligh].getDeparture();
    }
    
    /**
     * Sum and return the amount of full flights from all the flight.
     * @return the amounnt of full flights.
     */
    public int howManyFullFlights(){
        int numOfFullFlight = DEF_VALUE;

        for(int i = 0; i < _noOfFlight; i++){
            if(_flightsSchedule[i].getIsFull())
                numOfFullFlight++;
        }
        return numOfFullFlight;
    }
    
    /**
     * Calc' and return how many flight there is from the airport to given city, and from the city to the airport.
     * @param city the given city
     * @return the amonut of flights from and to city
     */
    public int howManyFlightsBetween(String city){
        int numOfFlights = DEF_VALUE;

        for(int i = 0; i < _noOfFlight; i++){
            if(_flightsSchedule[i].getOrigin().equals(city)|| _flightsSchedule[i].getDestination().equals(city)){
                numOfFlights++;
            }
        }
        return numOfFlights;
    }

    /**
     * Check what is the most popular destination
     * @return the name of the most popular city
     */
    public String mostPopularDestination(){
        if(_noOfFlight > DEF_VALUE){ //Start check, only if there is flight

            // Represnt all the cities (without duplicates)
            String[] cities = new String[MAX_FLIGHTS]; 

            // Represnt a count of how many flights fly to eatch city.
            int[] citiesCount = new int[MAX_FLIGHTS];
            
            // The index is according to the array of cities.
            //Examples:
            //citis        TLV  JFK  DDC
            //citiesCount   2    1    1
            //index         0    1    2
            //-> Index 2 represnt the city 'DDC' AND!! the amount of flight that lands there (2 flights).
            

            for(int i = 0; i < _noOfFlight; i++){
                int cityIndex = getIndex(cities, _flightsSchedule[i].getDestination()); //Search if the city is alread in cities, and set the index of this city(-1 if not exist).
                if(cityIndex == VALUE_NOT_EXIST_INDEX){ // if the index is -1 -> the city dose not exsit -> add to the end of array.
                    cities[i] =  _flightsSchedule[i].getDestination(); // Adding the city to the end.
                    citiesCount[i]++; //Add 1 to the count of this city - represent by the index.

                } else { // If the city alredy exsit in cities, just add +1 to the count in the right position.
                        citiesCount[cityIndex]++;
                }
            }
            //At this point, there is 2 arraies that represents for eatch city, how many flights fly towards this city.
        
            //Using private method, find the max number in citiesCount -> the most popular city, and return this city(by the index)
            return cities[getIndexForPopFlight(citiesCount)];

         //In case that flight board is empty
        } else
            return null;    
    }
        

    /**
     * Find the most expensive flight ticket.
     * @return the flight with the highest price for 1 ticket. null if there is no flights.
     */
    public Flight mostExpensiveTicket(){
        if(_noOfFlight > DEF_VALUE){ 

            int indexExpTicket = DEF_VALUE; // the index of the most exspensive flight.
            int expTicket = DEF_VALUE; // The value of the most exspensive ticket.

            for(int i = 0; i < _noOfFlight; i++){
                if(_flightsSchedule[i].getPrice() > expTicket){
                    expTicket = _flightsSchedule[i].getPrice();
                    indexExpTicket = i;
                }
            }
            
            return new Flight(_flightsSchedule[indexExpTicket]);

        } else //In case that there is no flights.. 
            return null;
    }


    /**
     * Find the longest flight.
     * @return The longest flight. null if there is no flights.
     */
    public Flight longestFlight(){
        if(_noOfFlight > DEF_VALUE){ 

            int indexLngFli = DEF_VALUE; // the index of the longest flight.
            int lngFlight = DEF_VALUE; // The flight duration of the longest flight.

            for(int i = 0; i < _noOfFlight; i++){
                if(_flightsSchedule[i].getFlightDuration() > lngFlight){
                    lngFlight = _flightsSchedule[i].getFlightDuration();
                    indexLngFli = i;
                }
            }
            
            return new Flight(_flightsSchedule[indexLngFli]);

        } else //In case that there is no flights.. 
            return null;
    }

    public String toString(){
        if(_noOfFlight > DEF_VALUE){
            String s = "The flights for airport " + _city + " today are:\n";

            for(int i = 0; i < _noOfFlight; i++){
                s += _flightsSchedule[i].toString() + "\n";
            }

            return s;

        } else //In case that there is no flights.. 
            return null;
    }
 

    //______________________________________\\
    //Private methodes - use only in class
    
    
    /**
     * @param f the flight to search
     * @return the index number of the fligh f in the flight board, if the flght dose not exit - return -1;
     */

    private int getIndex(Flight f){
        for(int i = 0; i<_noOfFlight; i++){
            if(_flightsSchedule[i].equals(f)){
                return i;
            }
        }
        return VALUE_NOT_EXIST_INDEX;
    }
    
    /**
     * Finds the index of city in array of cities
     * @param cities the cities array
     * @param city the city to search in cities
     * @return the index of city in cities, if the city dose not exsit retuen -1 
     */
    private int getIndex(String[] cities, String city){
        for(int i = 0; i < cities.length; i++){
            if(cities[i] != null){
                if(cities[i].equals(city)){
                    return i;
                }
            }
            else{
                break;
            }
        }
        return VALUE_NOT_EXIST_INDEX;
    }

    /**
     * Clac the most common flight, and return the index of this city in cities
     * @param citiesCount the array of number, that represent how many time flight lands there - how popular this city.
     * @return an index that represent the loction of the most popular city in cities.
     */
    private int getIndexForPopFlight(int[] citiesCount){
        int maxCount = citiesCount[DEF_VALUE];
        int index = DEF_VALUE;
        for(int i = 1; i < citiesCount.length; i++){
            if(citiesCount[i] > maxCount ){
                maxCount = citiesCount[i];
                index = i;
            }
        }
        System.out.println(maxCount);
        return index;
    }
}
