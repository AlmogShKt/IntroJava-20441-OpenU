
public class Time2Driver {

    public static void main(String[] args) {
        Time2 firstTime = new Time2(18, 33);
        Time2 secondTime = new Time2(firstTime);

        System.out.println("The hour of the first Time2 object is: " + firstTime.getHour());
        


    }
}
