package project2;

public class Test {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(10);
        list.insert(5);
        list.insert(20);

        list.printList();
        
        System.out.println("Front: " + list.front());  
        System.out.println("Back: " + list.back());    
        System.out.println("Size: " + list.size());    

        list.printList();

        
        SinglyLinkedList<Integer> anotherList = new SinglyLinkedList<>();
        anotherList.insert(8);
        anotherList.insert(30);

        list.printList();

        
        list.merge(anotherList);
        System.out.println("Size after merge: " + list.size()); 
        System.out.println("Back after merge: " + list.back());  

        list.printList();

        
        list.reverse();
        System.out.println("Front after reverse: " + list.front()); 
        System.out.println("Back after reverse: " + list.back());    

        list.printList();

        
        list.pop_front();
        list.pop_back();
        System.out.println("Front after operations: " + list.front());  
        System.out.println("Back after operations: " + list.back());
        
        list.printList();

    }
}
