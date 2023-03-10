import arrayList.MyArrayList;
import iterator.Iterator;

public class MyArrayListTest {

    public static void main(String[] args) {
        //
        MyArrayListTest myArrayListTest = new MyArrayListTest();
        myArrayListTest.testMyArrayList();
    }

    public void testMyArrayList() {
        //
        MyArrayList<String> list = new MyArrayList();

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