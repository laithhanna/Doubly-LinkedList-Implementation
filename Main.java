public class Main {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        //Lets test our DLL. Note that the first node start at index 0
        dll.addFirst(1);
        dll.addFirst(10);
        dll.addLast(25);
        dll.printList();

        dll.addAt(1, 100);
        dll.printList();

        dll.addAt(3, 90);
        dll.printList();

        dll.removeAt(0);
        dll.printList();

        dll.removeAt(3);
        dll.printList();

        //Now, we have 3 nodes in our DLL. Lets try to remove 5 nodes!
        // basically since we have checks in our code it will prevent nullPointerException from occurring
        //Now, the first 3 method calls will work in the loop but the last 2 will only return null and will not activate
        //the inner remove method from executing and will not decrement the size. (we will not have negative size)
        //Also, note that the sentinel header and trailer nodes do not contribute to the size of the DLL.
        for (int i = 0; i < 5; i++) {
            dll.removeFirst();
        }
        dll.printList();

        dll.removeFirst(); //nothing will happen, the function will just return null
        dll.removeAt(0); //nothing will happen, the function will just return null
        dll.removeAt(-1); //tell the user that index must start at 0

    }
}
