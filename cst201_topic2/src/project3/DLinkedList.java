package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * provided file for DLinkedList Assignment 
 *
 * @author lkfritz
 */
public class DLinkedList<T extends Comparable<T>> {

    public static void main(String[] args) throws FileNotFoundException {

        DLinkedList<String> lst1 = new DLinkedList<>();
        DLinkedList<String> lst2 = new DLinkedList<>();        

        Scanner fin = new Scanner(new File("text1.in"));
        String str;

        while (fin.hasNext()) {
            str = fin.next();
            str = cleanUp(str);           
            lst1.insertOrderUnique(str);           
        }
        fin.close();

        fin = new Scanner(new File("text2.in"));
        while (fin.hasNext()) {
            str = fin.next();
            str = cleanUp(str);
            lst2.insertOrderUnique(str);           
        }

        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        
        
        DLinkedList combined = lst1.merge(lst2);
        
        System.out.println("\nAFTER MERGE");
        System.out.println("List 1:  [" + lst1);
        System.out.println("List 2:  [" + lst2);
        System.out.println("\n" + combined);
    }

    /**
     * ASSIGNED
     * @param str
     * @return str in all lower case with LEADING and TRAILING non-alpha
     * chars removed
     */
    public static String cleanUp(String input) {
        return input.replaceAll("^[^a-zA-Z]+", "")
                    .replaceAll("[^a-zA-Z]+$", "")
                    .toLowerCase();
    }


    //inner DNode class:  PROVIDED
    private class DNode {

        private DNode next, prev;
        private T data;

        private DNode(T val) {
            this.data = val;
            next = prev = this;
        }
    }

    //DLinkedList fields:  PROVIDED
    private DNode header;

    //create an empty list:  PROVIDED
    public DLinkedList() {
        header = new DNode(null);
    }

    /**
     * PROVIDED add
     *
     * @param item return ref to newly inserted node
     */
    public DNode add(T item) {
        //make a new node
        DNode newNode = new DNode(item);
        //update newNode
        newNode.prev = header;
        newNode.next = header.next;
        //update surrounding nodes
        header.next.prev = newNode;
        header.next = newNode;
        return newNode;
    }

    //PROVIDED
    public String toString() {
        String str = "[";
        DNode curr = header.next;
        while (curr != header) {
            str += curr.data + " ";
            curr = curr.next;
        }
        str = str.substring(0, str.length() - 1);
        return str + "]";
    }

    /**
     * ASSIGNED
     * remove val from the list
     *
     * @param val
     * @return true if successful, false otherwise
     */
    public boolean remove(T val) {
        if (header.next == header) return false; 
        DNode curr = header.next; 
        while (curr != header && !curr.data.equals(val)) {
            curr = curr.next;
        }
        if (curr == header) return false;
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        return true;
    }



    /**
     * ASSIGNED
     *
     * @param item
     */
    public void insertOrder(T item) {
        DNode newNode = new DNode(item);
        DNode curr = header.next; 
        while (curr != header && curr.data.compareTo(item) < 0) {
            curr = curr.next;
        }
        newNode.prev = curr.prev;
        newNode.next = curr;
        curr.prev.next = newNode;
        curr.prev = newNode;
    }



    /**
     * ASSIGNED
     *
     * @param item
     */
    public boolean insertOrderUnique(T item) {
        DNode curr = header.next;
        while (curr != header) {
            if (curr.data.equals(item)) return false; 
            curr = curr.next;
        }
        this.insertOrder(item);
        return true;
    }


    /**
     * ASSIGNED
     * PRE:  this and rhs are sorted lists
     * @param rhs
     * @return list that contains this and rhs merged into a sorted list
     * POST:  returned list will not contain duplicates
     */
    public DLinkedList<T> merge(DLinkedList<T> rhs) {
        DLinkedList<T> result = new DLinkedList<>();
        DNode currThis = this.header.next;
        DNode currRhs = rhs.header.next;

        while (currThis != this.header && currRhs != rhs.header) {
            if (currThis.data.compareTo(currRhs.data) < 0) {
                result.add(currThis.data);
                currThis = currThis.next;
            } else if (currThis.data.compareTo(currRhs.data) > 0) {
                result.add(currRhs.data);
                currRhs = currRhs.next;
            } else {
                result.add(currThis.data);
                currThis = currThis.next;
                currRhs = currRhs.next;
            }
        }

        while (currThis != this.header) {
            result.add(currThis.data);
            currThis = currThis.next;
        }

        while (currRhs != rhs.header) {
            result.add(currRhs.data);
            currRhs = currRhs.next;
        }

        this.header.next = this.header; 
        this.header.prev = this.header;
        rhs.header.next = rhs.header;
        rhs.header.prev = rhs.header;

        return result;
    }

}