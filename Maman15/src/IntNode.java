/**
 * Maman 15
 * @author Almog Shtaigmann
 * @version 14.05.22
 * 
 * This class represent Set of odd numbers 
 */
public class IntNode
{
    private int _value;
    private IntNode _next;
    
    
    public IntNode(int v, IntNode n){
        _value = v;
        _next = n; 
    }
    public int getValue() {
        return _value;
    }

    public IntNode getNext() {
        return _next;
    }

    public void setValue(int v) {
        _value = v; 
    }

    public void setNext(IntNode n) {
        _next = n; 
    }





}