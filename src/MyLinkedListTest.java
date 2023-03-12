import iterator.Iterator;
import linkedList.MyLinkedList;

import java.util.ArrayList;

public class MyLinkedListTest  {

    public static void main(String[] args) {
        //
        MyLinkedListTest myLinkedListTest = new MyLinkedListTest();
        myLinkedListTest.testLinkedTest();
    }

    public void testLinkedTest() {
        //
        MyLinkedList<String> list = new MyLinkedList();

        list.add("one");
        list.add("two");
        list.add("three");

        // Displaying the list
        System.out.println("The list is: \n"
                + list);

        // Create an iterator for the list
        // using iterator() method
        Iterator<String> iter = list.iterator();

        // Displaying the values after iterating
        // through the list
        System.out.println("\nThe iterator values"
                + " of list are: ");

        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }

    }


}
