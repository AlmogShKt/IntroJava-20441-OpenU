/**
 * This program is a self made tester for course nr. 20441 MMN 13
 *
 * @author Tamir
 * @version 3.2022
 */
import java.util.Random;
public class AirportTester
{
    public static void main(String[] args){
        // declarations
        final int FLIGHTS = 50; 
        final int HOURS = 24;
        final int MINUTES = 60;
        final int MAX_DURATION = 800;
        final int PASS_INPUT = 300;
        final int MAX_PRICE = 1000;
        final int RAN_CELL1 = FLIGHTS / 5;
        final int RAN_CELL2 = FLIGHTS;
        final int RAN_CELL3 = FLIGHTS * 3 / 4;
        Flight fCheck1 = null;
        Flight fCheck2 = null;
        Flight fCheck3 = null;
        int count = 0;
        String city;
        Airport airport;
        
        Random ran = new Random();
        // array as a bank of airport choices for the random function
        String[] airports = {"tel aviv", "new york", "london", "boston", "paris", "moscow", "beijing", "los angeles", "rome", "toronto"};
        city = airports[ran.nextInt(airports.length)];
        airport = new Airport(city);
        // Flight(String origin, String destination, int hour, int minute, int flightDuration, int noOfPassengers, int price)
        while (count < FLIGHTS){
        //for (int i = 0; i < FLIGHTS; i++){
            Flight f = new Flight(airports[ran.nextInt(airports.length)], airports[ran.nextInt(airports.length)], 
                        ran.nextInt(HOURS), ran.nextInt(MINUTES), ran.nextInt(MAX_DURATION), ran.nextInt(PASS_INPUT), 
                        ran.nextInt(MAX_PRICE));
            if(f.getOrigin() != f.getDestination() && airport.addFlight(f)){
                count++;    // checks the number of times a flight was successfully added
                if(count == RAN_CELL1)
                fCheck1 = new Flight(f);  // copying flight for future checks
                if(count == RAN_CELL2)
                fCheck2 = new Flight(f);  // copying flight for future checks
                if(count == RAN_CELL3)
                fCheck3 = new Flight(f);  // copying flight for future checks
            }
        }
        
        System.out.println(airport);
        System.out.println("The number of flights that were added to the list is " + count + "\n"); // must equal Airport\MAX_FLIGHTS
        
        System.out.println("Removing flight 1 was successfull: " + airport.removeFlight(fCheck1));
        System.out.println("Removing flight 2 was successfull: " + airport.removeFlight(fCheck2));
        fCheck3.setDeparture(new Time1(0,0));   // deliberately changing fCheck3 so it not on the flight list
        System.out.println("Removing flight 3 was successfull: " + airport.removeFlight(fCheck3));
        
      //  Time1 firstFlightTime = airport.firstFlightFromOrigin(airports[0]);
       // System.out.println("First flight time from " + airports[0] + " is : " + firstFlightTime);
        
        System.out.println("The number of full flights is: " + airport.howManyFullFlights());
        
        String place = airports[ran.nextInt(airports.length)];
        System.out.println("The number of flights between " + city + " and "  + place +
                            " is : " + airport.howManyFlightsBetween(place));
                            
        System.out.println("The most popular destination is: " + airport.mostPopularDestination());
        
        System.out.println("The most expensive ticket is: " + airport.mostExpensiveTicket());
        
        System.out.println("The longest flight is: " + airport.longestFlight());
        


    }
}