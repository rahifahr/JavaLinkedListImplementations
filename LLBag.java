public class LLBag implements Bag {
    private Node head;
    private int size;

    private class Node {
        Object item;
        Node next;

        Node(Object item) {
            this.item = item;
            this.next = null;
        }
    }

    public LLBag() {
        this.head = null;
        this.size = 0;
    }

    public boolean add(Object item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    public boolean remove(Object item) {
        if (head == null) {
            return false; 
        }

        if (head.item.equals(item)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.item.equals(item)) {
                current.next = current.next.next;  
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public boolean contains(Object item) {
        Node current = head;
        while (current != null) {
            if (current.item.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int numItems() {
        return size;
    }

    public Object grab() {
        if (head == null) {
            return null;
        }

        Object[] items = toArray();
        int randomIndex = (int) (Math.random() * size);
        return items[randomIndex];
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.item;
            current = current.next;
        }
        return array;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}