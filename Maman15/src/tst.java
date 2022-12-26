public class tst {
    public static void main(String[] args) {
        Set s = new Set();
        Set s2 = new Set();
        Set _set1 = new Set();


      
        _set1.addToSet(3);
        _set1.addToSet(5);
        _set1.addToSet(11);
        _set1.addToSet(13);
        

        Set _set2 = new Set();
        _set2.addToSet(3);
        _set2.addToSet(5);
        _set2.addToSet(7);
        _set2.addToSet(11);

        Set _set3 = new Set();
        _set3.addToSet(11);
        _set3.addToSet(13);
        _set3.addToSet(3);
        _set3.addToSet(33);
        
        _set3.addToSet(333);

   
        System.out.println("\n toString:");

        System.out.println("Group Set1: " + _set1.toString()); //Group A: {3,7,1}
        System.out.println("Group Set2: " + _set2.toString()); //Group B: {11,31,7,1}
        System.out.println("Group Set3: " + _set3.toString());

        
        //System.out.println(s.difference(s2));

        System.out.println("Dif- " + _set1.difference(_set3));
        





    }
    
}
