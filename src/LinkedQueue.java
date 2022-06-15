public class LinkedQueue<E> {

    /**
     * Node class to be used by the LinkedQueue class
     *
     * @param <E>
     */
    private class Node<E> {

        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    private int count;
    private Node<E> front;
    private Node<E> rear;

    /**
     * Constructor for a LinkedQueue
     */
    public LinkedQueue() {
        front = rear = null;
        count = 0;
    }

    /**
     * Add an item to the Queue
     *
     * @param data item to be added to the Queue
     */
    public void enqueue(E data) {
        if (rear != null) {
            rear.next = new Node(data, null);
            rear = rear.next;
        } else {
            rear = new Node(data, null);
            front = rear;
        }
        count++;
    }

    /**
     * Remove an item from the Queue - throws exception if queue is empty
     *
     * @return the item at the from of the Queue
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue - Queue is empty");
        }
        E data = front.data;
        front = front.next;
        count--;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    /**
     * Check is queue is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Shows front of Queue
     *
     * @return the number of items of customer at the front of queue
     */
    public int peek() {
        Customer c = (Customer) front.data;
        return c.getNI();
    }

    /**
     * Obtain the number of elements in the Queue
     *
     * @return
     */
    public int size() {
        return count;
    }
    /**
     * Obtain the Queue in the form of String
     *
     * @return Queue of type String instead of Customer type
     */

    public String toString() {
        Node pointer = front; //pointer to iterate through the queue
        String s = "";  //String to return at the end
        while(pointer != null){
            s = s + (pointer.data.toString() + ", ");
            pointer = pointer.next;
        }
        return s;
    }
    /**
     * Obtain the total serving time of the Queue
     *
     * @return
     */

    public int servingTimeQueue(){
        int i = 0; //int variable to add all serving times
        Node pointer = front; //pointer to iterate through the queue
        while (pointer != null){
            Customer c = (Customer) pointer.data;
            i += c.servingTime(); //adding all the serving times
            pointer = pointer.next;
        }
        return i;
    }

}