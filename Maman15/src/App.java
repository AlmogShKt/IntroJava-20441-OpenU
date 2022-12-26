public class App {

    //Old
    /**
     * Return the intersection of 2 sets
     * @param interSet the new set
     * @param shortSet the short set
     * @param longSet the long set
     * @return {@code interSet} when he have the common elements
     */
    // private Set CreatInterSet(Set interSet,Set shortSet, Set longSet){
    //     IntNode tempShort = shortSet.getHead();

    //     //We only need to check the short set,for each element, if he member in the long set, if yes add to the new set.
    //     for(int i = 0; i < shortSet.numOfElements(); i++){ //In order to check all the elements in the short set
    //         if(longSet.isMember(tempShort.getValue()))//Only if they share the same element
    //             interSet.addToSet(tempShort.getValue());//Add this element to set new set

    //         tempShort = tempShort.getNext();//Move to the next element in the set
    //     }
    //     return interSet;//return the new elemnt
    // }

    // public Set union(Set other){

    //     Set uniSet = new Set();
    //     IntNode temp = other.getHead();

    //     while (temp != null){ //will run n times (n is the amount of elements in other)
    //         uniSet.addToSet(temp.getValue());
    //         temp = temp.getNext();
    //     }

    //     temp = _head;
    //     while(temp != null){//will run m times (m is the amount of elements in this set)
    //         uniSet.addToSet(temp.getValue());
    //         temp = temp.getNext();
    //     }

    //     return uniSet;
    // }

    
    // public Set difference(Set other){
    //     Set diffSet = new Set();

    //     IntNode temp = _head;

    //     while(temp != null){
    //         if(!(other.isMember(temp.getValue())))//Only if the element is not memebr in other(this/other = all the elements that only in this!)
    //             diffSet.addToSet(temp.getValue());//Add the element
            
    //         temp = temp.getNext();
    //     }

    //     return diffSet;

    // }

    public static void main(String[] args) throws Exception {
        Set s = new Set();
        Set s2 = new Set();
        Set s3 = new Set();
        Set s4 = new Set();

       
        

        s.addToSet(1);
        s.addToSet(7);
        s.addToSet(3);

        Set ss = new Set(s);
        System.out.println(ss);


        s2.addToSet(31);
        s2.addToSet(11);
        s2.addToSet(7);
        s2.addToSet(1);

        s4.addToSet(7);
        s4.addToSet(1);
        s4.addToSet(31);
        s4.addToSet(11);

        //Check toString
        System.out.println("\tostring:");

        System.out.println("Group A: " + s.toString()); //Group A: {3,7,1}
        System.out.println("Group B: " + s2.toString()); //Group B: {11,31,7,1}
        System.out.println("Group C: " + s3.toString() + "\n"); //Group C: {}



        //Check is empty:
        System.out.println("\nisEmpty:");
        System.out.println(s3.isEmpty()); // true
        System.out.println(s.isEmpty());// False
    
        //check equals
        System.out.println("\nequals:");
        System.out.println(s4.equals(s2)); // true
        System.out.println(s4.equals(s)); // false


        //check subSet
        Set s5 = new Set();
        s5.addToSet(11);
        s5.addToSet(1);
 
        System.out.println("\nsubSet:");
        System.out.println(s2.subSet(s5)); // true
        s5.addToSet(5);
        System.out.println(s2.subSet(s5)); // false

        //check RemoveFromSet
        System.out.println("\nremoveFromSet:");
        System.out.println("Group D: " + s5.toString() + "\n");
        s5.removeFromSet(1); 
        System.out.println("Group D: " + s5.toString() + "\n"); // {5,11}
        s5.removeFromSet(7); 
        System.out.println("Group D: " + s5.toString() + "\n"); // {5,11}
        s5.removeFromSet(11); 
        System.out.println("Group D: " + s5.toString() + "\n"); // {5}
        s5.removeFromSet(5); 
        System.out.println("Group D: " + s5.toString() + "\n"); // {}

        //check intersectopn
        s5 = new Set();
        s5.addToSet(9);
        s5.addToSet(1);
        s5.addToSet(31);
 
        System.out.println("\nintersection:");
        System.out.println(s5.intersection(s2)); // {1,31}
        System.out.println(s.intersection(s2)); // {1,7,3}


        //check union
        System.out.println("\nunion:");
        System.out.println(s5.union(s2)); // {9,1,7,31,11}
        System.out.println(s.union(s2)); // {3,1,7,31,11}

        //check diff
        System.out.println("\nGroup A: " + s.toString());
        System.out.println("Group D: " + s5.toString());
        System.out.println("difference:");
        System.out.println(s.difference(s5));
    }
}
