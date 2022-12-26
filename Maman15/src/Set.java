/**
 * This class represent Set of odd numbers 
 * @category Maman 15
 * @author Almog Shtaigmann
 * @version 25.05.22
 */
public class Set {

    private IntNode _head;
    private int _numOfElements;

    public Set(){
        _head = null;
        _numOfElements = 0;
    }

    public Set(Set other){
        //Check if the set is not empty
        if(other.isEmpty()){
            return;
        } else {
            //Creat new node with the value of the head
            _head = new IntNode(other.getHead().getValue(), null);
                                
            //Start with the next node
            int tmpValue;
            IntNode tmpOtherNode = other.getHead().getNext();
            IntNode tmpThisNode = _head;
            while(tmpOtherNode != null){
                tmpValue = tmpOtherNode.getValue();
                tmpThisNode.setNext(new IntNode(tmpValue, null));

                tmpThisNode = tmpThisNode.getNext();
                tmpOtherNode = tmpOtherNode.getNext();
            }
            //Set the num of elements
            _numOfElements = other.numOfElements();
        }
    }

    public IntNode getHead(){
        return _head;
    }

    /**
     * @return the amount of elements in the set.
     */
    public int numOfElements(){
        return _numOfElements;
    }
    
    /**
     * @return True if the set is empty, and false if there is more then 1 element
     * Time complexity is O(1) space complexity is O(1)
     */
    public boolean isEmpty(){
        return _head == null;
        
    }

    /**
     * Check if the value of {@code num} is part of the set
     * @param num the value to check
     * @return {@code true} if num is part of the set, {@code false} is its not
     * Time complexity is O(n), n is the amount of elements in the set, space complexity is O(1)
     */
     public boolean isMember(int num){
        if(isEmpty())
            return false;

        IntNode temp = _head;
        while(temp != null && temp.getValue() <= num){
            if(temp.getValue() == num)
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * Check if set is subSet of this set (all the elements is other are members in this set).
     * @return true if other set is subSet of this set.
     * Time complexity is O(n), n is the amount of elements in other set, space complexity is O(1)
     */
    public boolean subSet(Set other){
        //If both sets are empty or this not empty but other yes- they equals to the empty set / the empty set is part of any set
        if(isEmpty() && other.isEmpty()|| other.isEmpty() && !isEmpty())
            return true;
        
        //If the other set is empty and this set is not.
        if(!other.isEmpty() && isEmpty())
            return false;

        IntNode tempOther = other.getHead();
        IntNode tempThis = _head;
        int count = 0;

        while(tempOther != null){
            //If we check all the elemnts in this set, and still didnt return ture, so other is not subset of this set
            if(tempThis == null)
                return false;
            
            else if(tempOther.getValue() == tempThis.getValue()){
                if(++count == other.numOfElements()){ //if the amount of matchs is equal to the amount of elements in other set, other is subset
                    return true;
                }
                //if not(count < other.nomOfElements), check the next elements
                tempOther = tempOther.getNext();
                tempThis = tempThis.getNext();
            

            } else if(tempOther.getValue()  > tempThis.getValue()){ // if they noy eqals, but the element in other is greather then the element in this, check the next element in this set
                tempThis = tempThis.getNext();
            } else{ //If the element in other is smaller then this, so this element is not in this and other is not subSet of this
                return false;
            }
        }
        return true;
    }
            
    /**
     * Check if 2 sets are equals(if they have the same amount of elements,and other is subset of this set)
     * @return true if the 2 sets are equals, otherwise false
     * Time complexity is O(n), n is the amount of elements n other set, space complexity is O(1)
     */
    public boolean equals(Set other){
        //If they dont have the same amount of elements, the Time complexity will be O(1).
        if(_numOfElements == other.numOfElements() && subSet(other))
            return true;
        
        return false;
        
    }

    //Use in addToSet
    private IntNode addNewNode(int x,IntNode nextNode){
        //In order to avoid write the same prosess of creat new IntNode and add 1 to the amount of elements in the set
        _numOfElements++;
        return new IntNode(x, nextNode);

    }

    /**
     * Adding only odd elements to the set, and sort the set while adding
     * @param x the element to add
     * Time complexity is O(n), space complexity is O(1)
     */
    public void addToSet(int x){
        if(!isMember(x) && x % 2 == 1){
            //There 2 main cases, if there is more then 1 elment(2>) or less(1/0)

            //In case that there is 2 or more elemnts and, x is bigger the the first element
            if(_numOfElements > 1 && x > _head.getValue()){
                IntNode tmp = _head.getNext();
                IntNode before = _head;

                while(tmp != null){ 
                    //If x is between 2 numbers -> add x between them, if not check the next elements
                    if(x > before.getValue() && x < tmp.getValue()){
                        //Add x after before and before tmp
                        before.setNext(addNewNode(x, tmp));
                        return;
                    } 
                    before = tmp;
                    tmp = tmp.getNext();
                }
                //In case that x is the biggest number in the set -> add x after the last the first element
                before.setNext(addNewNode(x, null));
                return;

            } else {
                //At this point there is 3 options:
                //1.the set is empty -> set the first elment with the value of x
                //2. there is 1 element
                //2.1 check if x is bigger then this elment and acordenly set the next/before elemnt
                //3. x is smaller the the first element -> add x before him

                if(_head == null){
                    _head = new IntNode(x, null);
                    
                }else if(x > _head.getValue()){
                    _head.setNext(new IntNode(x, null));
                }else{
                    _head = new IntNode(x, _head);
                }
                _numOfElements++;
                return;
            }   
        }
    }

    /**
     * Remove element from the set, if the element is npt exist the set will not changed
     * @param x the element to remove
     * Time complexity is O(n)-isMember, space complexity is O(1)
     */
    public void removeFromSet(int x){
        if(!isEmpty() && isMember(x)){ // only if the set is not empty and x is part of the set
            IntNode temp = _head;
            IntNode before = null;

            while(temp.getValue() != x){ //Run until the value of temp is x
                before = temp;
                temp = temp.getNext();
            }

            if(before == null){ // If before is null there is 2 options:
                if(temp.getNext() == null)//1. there is only 1 element is the set, and now the set will be empty
                    _head = before;
                else // 2. the set is not empty, but we need ti remove the first element is the set
                    _head = temp.getNext(); 
            } else // In any other case, remove the element by link the element before o the element after the one that needs to be romoved
                before.setNext(temp.getNext());
            _numOfElements--;
        }// end of if
        //if the elment is not in the set -> nothing will change
    }

    /**
     * @return the string of the set in foramt of {{@code a1,a2,a3..} }
     * Time complexity is O(n), n is the amount of elements in the set,space complexity is O(1)
     */
    public String toString(){
        String s = "{";
        IntNode temp = _head;

        while(temp != null){
            s += temp.getValue();
            
            if(temp.getNext() != null)// only if temp is npt the last element
                s += ",";
            
            temp = temp.getNext();
        }
         s += "}";

        return s;
    }

    /**
     * return set of all the common elements in both sets
     * @param other the other set
     * @return set of common elements
     * Time complexity is O(n) space complexity is O(n), n is the amount of elements in the intersection set
     */
    public Set intersection(Set other){
        Set interSet = new Set();//The new set for the common elements

        if(!(isEmpty() && other.isEmpty())){ //Only if both sets are not empty
           
            //Define who is the short set, and send the right order for the private method
            if(_numOfElements > other.numOfElements())
               interSet = creatInterSet(interSet, other, this);
            else
               interSet = creatInterSet(interSet, this, other);

            return interSet;

        } else 
            return interSet; //Return empty set if one of the set are empty

    }

    //look for the common elements in both sets
    private Set creatInterSet(Set interSet,Set shortSet, Set longSet){
        IntNode tempLongSt = longSet.getHead();
        IntNode tempShortSt = shortSet.getHead();
        IntNode tempNewSt = interSet.getHead();

        while(tempShortSt != null ){
            //In case that all the elements in the long set is not in the short, return what we found until this point
            if(tempLongSt == null){
                return interSet;
            }
            //Only if the elements values is in both sets, add to the new set
            else if(tempShortSt.getValue() == tempLongSt.getValue()){
                //for the first elements, set the head of the new set
                if(tempNewSt == null){
                    interSet._head = new IntNode(tempLongSt.getValue(), null);
                    interSet._numOfElements++;
                    tempNewSt = interSet.getHead();
                //In all other case, set the next node with the common value
                } else{
                    tempNewSt.setNext(new IntNode(tempLongSt.getValue(), null));
                    tempNewSt = tempNewSt.getNext();
                }
                
                //Check the next 2 elements in the sets
                tempLongSt = tempLongSt.getNext();
                tempShortSt = tempShortSt.getNext();
            
            //if one of the values is greahter then the second, check the next element
            //this 3 5 9
            //other 1 7
            //if(1>3) no, check 7 with 3, then 5 with 7, then 9 with 7.
            } else if(tempShortSt.getValue() > tempLongSt.getValue()){
                tempLongSt = tempLongSt.getNext();
            } else{
                tempShortSt = tempShortSt.getNext();
            }
        }
        return interSet;
    }
   
    private void setNextNodeInSet(Set newSet,IntNode tmpNode, IntNode tmpNew){
        
        tmpNew.setNext(new IntNode(tmpNode.getValue(), null));
        newSet._numOfElements++;
    }

     /**
     * Return the union of 2 sets (this set, and second set)
     * @param other the second set
     * @return a new, union set
     * Time complexity is O(m+n) = O(n), Space complexity is O(n) - n is the size of the amount of elements in this set + other set - the size on uniSet
     */
    public Set union(Set other){
        //First, if one of the set is sub set of the second, return a new and duplicate set.
        if(subSet(other))
            return new Set(this);
        else if(other.subSet(this))
            return new Set(other);

        IntNode tmpThis = _head;
        IntNode tmpOther = other.getHead();

        //The new and union set
        Set uniSet = new Set();

        //Set the head value of the new set as the smallest head from this and other sets
        uniSet._head = tmpOther.getValue() < tmpThis.getValue() ? (new IntNode(tmpOther.getValue(), null)):(new IntNode(tmpThis.getValue(), null));

        IntNode tmpNew = uniSet.getHead();

        //Add elemets to the new set until omnt of them is empty(pointing to null), then add the rest
        while(tmpOther != null && tmpThis != null){
            //Holds the values of  each node
            int thisValue = tmpThis.getValue(), otherValue = tmpOther.getValue(), newValue = tmpNew.getValue();
            
            //Based on megrgeSort algoritem - always add the small number, then point to the next node

            if(thisValue < otherValue){
                //Only if the value is diffrent, add it
                if(thisValue != newValue){
                    setNextNodeInSet(uniSet, tmpThis,tmpNew);
                    tmpNew = tmpNew.getNext();
                }
                tmpThis = tmpThis.getNext();

            } else {
                if(otherValue != newValue){
                    setNextNodeInSet(uniSet, tmpOther, tmpNew);
                    tmpNew = tmpNew.getNext();
                }
                tmpOther = tmpOther.getNext();
            }
        }
        // At this point there is 2 options:
        //1.this set is null and we need to add the rest of other to the new set
        //2.other set is null and we need to add the resp of this set to the new set
        
        IntNode tmpFin;//poniter to the rest of the not empty set
        if(tmpThis == null){
            tmpFin = tmpOther;
        }
        else{
            tmpFin = tmpThis;
        }
            
        //The same way we did early, add the rest of the elment to the new set(only if they diffrent)
        while(tmpFin != null){
            int value = tmpFin.getValue(), newValue = tmpNew.getValue();
            if(value != newValue){
                setNextNodeInSet(uniSet, tmpFin,tmpNew);
                tmpNew = tmpNew.getNext();
            }
            
            tmpFin = tmpFin.getNext();
        }
        //Return the new set
        return uniSet;
    }

    /**
     * Return the difference set (this / other)
     * @param other Set
     * @return A new set, with the result of this/other (only the elements that in this set)
     * Time complexity is O(n+m) = o(n), n is the amount of elements in this, m-other, Space complexity is O(n) - n is the size of the amount of elements in this set + other set - the size on uniSet
     */
    public Set difference(Set other){
        //If other is empty, rerurn copy of this set
        if(other.isEmpty())
            return new Set(this);
        if(isEmpty()){//if this empty, return the empty set
            return new Set();
        }

        Set diffSet = new Set(); // The new set to return
        IntNode tempThis = _head;
        IntNode tempOther = other.getHead();
        IntNode tempNew = diffSet._head;

        //Only if there is elements that not exist in other set -> add it to the new set

        while(tempOther != null){//Run untill we check all the elements in other set
            if(tempThis == null){//In case that we check all the element in this set, there is no other option of difference between the 2 sets 
                return diffSet;//-> return what we found until this point

            //If the values are equals, check the next elements of the 2 sets
            }else if(tempOther.getValue() == tempThis.getValue()){
                tempOther = tempOther.getNext();
                tempThis = tempThis.getNext();

            //In this case, the element in other is not in this set so -> add to the new set
            }else if(tempOther.getValue() > tempThis.getValue()){
                //For the first element in the new set
                if(diffSet._head == null){
                    diffSet._head = new IntNode(tempThis.getValue(), null);
                    diffSet._numOfElements++;
                    tempNew = diffSet.getHead();
                //In all ther case
                } else {
                    setNextNodeInSet(diffSet, tempThis,tempNew);
                    tempNew = tempNew.getNext();
                }
                tempThis = tempThis.getNext();

            } else {
                //In case that there is option for the element to be in the set, check the next one
                tempOther = tempOther.getNext();
            }
        }

        //In case that still there is elements to add, after we check all the elements in other set
        //So add all the rest to the new set.
        
        while(tempThis != null){ //until we add all the elements
            if(tempNew == null){//In case that this is the first element to add
                tempNew = new IntNode(tempThis.getValue(), tempThis.getNext());
                diffSet._head = tempNew;
                tempThis = tempThis.getNext();
                diffSet._numOfElements++;
            } else {
                setNextNodeInSet(diffSet, tempThis,tempNew);
                tempNew = tempNew.getNext();
                tempThis = tempThis.getNext();
            }
        }
        

        //return the new sry
        return diffSet;
    }//End of method differnce



}//End of class Set
