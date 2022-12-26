public class App {
    public static void main(String[] args) throws Exception {
        String city = "TLV";
        Airport a1 = new Airport(city);

        Time1 t1 = new Time1(10,45);
        Flight f1 = new Flight(city, "JFK", 10, 45, 120, 250, 10);
        Flight f2 = new Flight("JFK", "TLV", 11, 45, 110, 20, 450);

        System.out.println(a1.addFlight(f2));
        a1.addFlight(f2);

        System.out.println(a1);


        
        
        //System.out.println(a1.mostExspensiveTicket());



        
        
    }
}
