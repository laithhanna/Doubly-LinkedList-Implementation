/**
 * This is the Doubly linked list Node class.
 * each node will have a value (integer in this example) and 2 references,
 * a reference to the previous node and a reference to the next node. Hence, Doubly Linked List
 */
public class DLLNode {
    private Integer value; //I used the wrapper class so I can hold null value for the sentinel nodes. Primitive data types can't be null
    private DLLNode prev;
    private DLLNode next;

    public DLLNode (Integer value, DLLNode prev, DLLNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    //create setters and getters for the fields

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DLLNode getPrev() {
        return prev;
    }

    public void setPrev(DLLNode prev) {
        this.prev = prev;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }
}
