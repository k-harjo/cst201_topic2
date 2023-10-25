package project2;

public class SinglyLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList(SinglyLinkedList<T> aList) {
        
    }

    //head
    public T front() {
        if (head != null) {
            return head.data;
        }
        throw new RuntimeException("List is empty");
    }

    //tail
    public T back() {
        if (tail != null) {
            return tail.data;
        }
        throw new RuntimeException("List is empty");
    }

    // insert
    public void insert(T val) {
        Node<T> newNode = new Node<>(val);
        if (head == null) {
            head = tail = newNode;
        } else if (val.compareTo(head.data) < 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && val.compareTo(current.next.data) > 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
        size++;
    }

    // remove first data
    public void pop_front() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    // remove tail
    public void pop_back() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next == null) {
            head = tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            tail.next = null;
        }
        size--;
    }
    //find out if e
    public boolean empty() {
        return size == 0;
    }

    // get # of elements
    public int size() {
        return size;
    }

    // reverse elements in list
    public void reverse() {
        Node<T> prev = null, current = head, next;
        tail = head;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // merge with another list
    public void merge(SinglyLinkedList<T> aList) {
        Node<T> current = aList.head;
        while (current != null) {
            this.insert(current.data);
            current = current.next;
        }
    }
    public void printList() {
    	Node<T> current = head;
        while (current != null) {
        	System.out.print(current.data + "->");
        	current = current.next;
        }
        	System.out.print("None\n");
        	
        
    }
}



