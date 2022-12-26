public class Tester { 
    public static void main(String[] args) throws Exception {
       
        MyCar = new Car(True,"AVis",23,11.2.22);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Time2 t2 =  new Time2(10,59);
        Time2 t2A = new Time2(11,21);
        

        Time1 t1 = new Time1(10,43);
        Time1 t1A = new Time1(11,21);

        String origin = "NYC";
        String destination = "TLV";
        int flightDuration = 736;
        int h = 10;
        int m = 15;
        int noOfPassengers = 300;
        int price = 3;

        System.out.println("new time2: " + t2.addMinutes(119));


        Flight f1 = new Flight("PHX", destination, 11, 45, flightDuration, noOfPassengers, price);
        Flight f2 = new Flight(origin, destination, 11, 00, flightDuration, noOfPassengers, price);
        Flight f3 = new Flight(f1);

        f3.addPassengers(1);
        System.out.println(f3.getNoOfPassengers());
        System.out.println(f3.getIsFull());
        


        // System.out.println("Time1: " + t1.addMinutes(-23*60));
        // System.out.println("Time2: " + t2.addMinutes(-23*60));
        
        
    }
}
