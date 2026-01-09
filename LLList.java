import java.util.*;

public class LLList implements List {
    private class Node {
        private Object item;
        private Node next;
        
        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }
    
    private Node head;
    private Node last;
    private int length;
    
    public LLList() {
        head = new Node(null, null);
        last = head;
        length = 0;
    }
    
    
    public LLList(Object[] initItems) {
        head = new Node(null, null);
        
        Node prevNode = head;
        for (int i = 0; i < initItems.length; i++) {
            Node nextNode = new Node(initItems[i], null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }
        
        length = initItems.length;
        last = prevNode;
    }
    
    
    public int length() {
        return length;
    }
    
    
    public boolean isFull() {
        return false;
    }
    
    private Node getNode(int i) {
        if (i == length - 1) {
            return last;
        }
        
        Node trav = head;
        int travIndex = -1;
        
        while (travIndex < i) {
            travIndex++;
            trav = trav.next;
        }
        
        return trav;
    }
    
    public Object getItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        Node n = getNode(i);
        return n.item;
    }
    
    public boolean addItem(Object item, int i) {
        if (i < 0 || i > length) {
            throw new IndexOutOfBoundsException();
        }
        
        Node newNode = new Node(item, null);
        Node prevNode = getNode(i - 1);           
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        
        if (newNode.next == null) {
            last = newNode;
        }

        length++;
        return true;
    }

    public Object removeItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        Node prevNode = getNode(i - 1);           
        Object removed = prevNode.next.item;
        prevNode.next = prevNode.next.next;

        if (prevNode.next == null) {
            last = prevNode;
        }
        
        length--;
        return removed;
    }
    
    public String toString() {
        String str = "{";
        
        Node trav = head.next;    
        while (trav != null) {
            str = str + trav.item;
            if (trav.next != null) {
                str = str + ", ";
            }
            trav = trav.next;
        }
        
        str = str + "}";
        return str;
    }
    
    public ListIterator iterator() {
        return new LLListIterator();
    }
    
    private class LLListIterator implements ListIterator {
        private Node nextNode; 
        
        public LLListIterator() {
            nextNode = head.next;
        }
        
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        public Object next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            Object item = nextNode.item;
            nextNode = nextNode.next;
            return item;
        }
    }

    public Object getLastItem() {
        if (last == head) {
            return null; 
        }
        return last.item;
    }
}
