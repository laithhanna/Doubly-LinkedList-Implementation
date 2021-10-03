/**
 * This is the Doubly LinkedList class. Note that "DLL" will be used as an abbreviation for "Doubly LinkedList"
 * to implement my DLL I will utilize sentinel nodes which are basically dummy nodes that holds no value (value == null).
 * They will make the logic and implementation easier. We will always insert and remove nodes in between these
 * 2 sentinel nodes. Hence, there is no need to keep track of the head and the tail since these sentinel nodes will be
 * the header and the trailer nodes and the value they hold is null.
 */
public class DoublyLinkedList {
    private final DLLNode header; //sentinel header node
    private final DLLNode trailer; // sentinel trailer node
    private int size; //to keep track of the size of our DLL. Since we did not initialize it, it will hold 0 (default value for int)

    //the header and trailer references should point for each other

    public DoublyLinkedList() {
        header = new DLLNode(null, null, null); //next reference should point to the sentinel trailer but we did not initialize it yet. So next is null
        trailer = new DLLNode(null, header, null); // prev reference for the sentinel trailer will point to the sentinel header
        header.setNext(trailer); // Now the sentinel header will point to the sentinel trailer
    }

    /**
     * This method will return the size of the DLL
     * Note that the sentinel nodes will not contribute to the size of the DLL
     * @return the size of the DLL
     */
    public int getSize () {
        return size;
    }

    /**
     * This method will return True of the DLL is empty (size = 0) otherwise it will return false
     * Note that the sentinel nodes will not contribute to the size of the DLL
     * @return True if the DLL is empty
     */
    public Boolean isEmpty () {
        return size == 0;
    }

    /**
     * This method will return the value of the first node (after the sentinel header). since it is the first node that actually holds value.
     * @return the value of the first node (after the sentinel header).
     */
    public Integer first() {
        //first check if the DLL is empty
        if (isEmpty()) {
            return null;
        } else {
            return header.getNext().getValue();
        }
    }

    /**
     * This method will return the value of the last node (before the sentinel trailer). since it is the last node that actually holds value.
     * @return the value of the last node (before the sentinel trailer).
     */
    public Integer last() {
        //first check if the DLL is empty
        if (isEmpty()) {
            return null;
        } else {
            return trailer.getPrev().getValue();
        }
    }

    /**
     * This method will add a node at the start of the DLL (between the sentinel header and the node after it).
     * @param value This is the value that we want the new node to hold.
     */
    public void addFirst(Integer value) {
        addBetween(value, header, header.getNext());
    }

    /**
     * This method will add a node at the end of the DLL (between the sentinel trailer and the node before it).
     * @param value This is the value that we want the new node to hold.
     */
    public void addLast(Integer value) {
        addBetween(value, trailer.getPrev(), trailer);
    }

    /**
     * This method will add a new node between any 2 nodes in the DLL.
     * Note that when we add a node to this implementation of the DLL we ALWAYS add a node between 2 nodes,
     * since we have 2 sentinel header and trailer and all the new nodes are added between them
     * @param value that the new node will hold.
     * @param predecessor is the reference to the node before the node we want to insert
     * @param successor is the reference to the node after the node we want to insert
     */
    private void addBetween(Integer value, DLLNode predecessor, DLLNode successor) {
        // first create the new node that we want to add
        DLLNode newest = new DLLNode(value, predecessor, successor);
        //make sure to fix the references after you add a new node
        predecessor.setNext(newest);
        successor.setPrev(newest);

        size++; //increment the size of the DLL
    }

    /**
     * This method will remove the first node in the DLL (after the sentinel header).
     * Note that first we need to check if the DLL is empty.
     * @return the value of the node that has been removed. If DLL is empty return null.
     */
    public Integer removeFirst() {
        //check if the DLL is empty. Note that the sentinel nodes do not contribute to the size of the DLL
        if (isEmpty()) {
            return null;
        } else {
            return remove(header.getNext());
        }
    }

    /**
     * This method will remove the last node in the DLL (before the sentinel trailer).
     * Note that first we need to check if the DLL is empty.
     * @return the value of the node that has been removed. If DLL is empty return null.
     */
    public Integer removeLast() {
        //check if the DLL is empty. Note that the sentinel nodes do not contribute to the size of the DLL
        if (isEmpty()) {
            return null;
        } else {
            return remove(trailer.getPrev());
        }
    }

    /**
     * This method will remove a node from the DLL
     * @param node that will be removed
     * @return the value of the node that was removed
     */
    private Integer remove(DLLNode node) {
        //we will set 2 local variables to hold the references to the predecessor and the successor nodes of the node we wat to remove.
        //when we remove a node we need to fix the references of the nodes before and after the node we just removed.
        DLLNode predecessor = node.getPrev();
        DLLNode successor = node.getNext();
        // After removing a node. The predecessor of the node we just removed will have its next reference pointing to the successor of the node we removed
        // And the The successor of the node we just removed will have its prev reference pointing to the predecessor of the node we removed
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--; //decrease the size of the DLL

        return node.getValue(); //return the value of the node we just removed.
    }

    /**
     * This method will add a node at a specified index.
     * This method will also have a lot of checks to see whether the DLL is empty or index is negative or out of bounds
     * and if one of these prohibited actions occurred the user will be notified with a message.
     * @param index at which the new node will be
     * @param value that the new node will hold
     */
    public void addAt(int index, Integer value) {
        if (isEmpty() && index != 0) {
            System.out.println("Index is out of bounds and the list is empty! start at index 0");
        } else if (isEmpty() && index == 0) {
            addFirst(value);
        } else if (!isEmpty()) {
            if (index >= size) {
                System.out.println("Index is out of bounds");
            } else if (index == 0) {
                addFirst(value);
            } else {
                // Start at the first node that holds value, then traverse the list to find the the node before and after the node I want to add
                DLLNode start = header.getNext();
                for (int i = 0; i < index; i++) {
                    start = start.getNext();
                }
                addBetween(value, start.getPrev(), start);
            }
        }
    }

    /**
     * This method will remove a node at a specified index
     * @param index at which the node will be removed
     * @return the value of the removed node.
     */
    public Integer removeAt(int index) {
        //check if the index is valid
        if (index < 0) {
            System.out.println("index starts at 0");
            return null;
        } else if (isEmpty()) {
            return null;
        } else {
            if (size == 1) {
                return removeFirst();
            } else if (size > 1) {
                if (index == 0) {
                    return removeFirst();
                } else if (index == size - 1) {
                    return removeLast();
                } else {
                    DLLNode start = header.getNext();
                    for (int i = 0; i < index; i++) {
                        start = start.getNext();
                    }
                    return remove(start);
                }
            }
        }
        return -1; //we will never reach this point since I covered all potential cases in the conditional statements
    }

    /**
     * This method will print the list.
     * The main role of this method is to test our DLL and to make sure everything is working as intended.
     */
    public void printList() {
        //check if the DLL is empty
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.print("Traversing the list: ");
            // start at the first node the holds value. This will be our starting point.

            DLLNode start = header.getNext(); //now we have a reference to the first node that hold value.

            while (start != trailer) { //basically we will keep printing the nodes as long as the node we are on is not the sentinel trailer
                System.out.print(start.getValue() + " ");
                start = start.getNext(); //move to the next node
            }
            System.out.println("\nThe size of the list: " + size); // I will print the size just to be extra sure that our DLL is working
        }
    }
}
