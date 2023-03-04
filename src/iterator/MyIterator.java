package iterator;


public class MyIterator<E> implements Iterator<E> {
    private int index = 0;
    private E[] elements;
    private int length;

    public MyIterator myIterator() {
        this.index = 0;
        this.elements = (E[]) new Object[0];
        this.length = 0;
        return new MyIterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
        //
        return (E) elements[index++];
    }

    @Override
    public boolean hasNext() {
        //
        return index < length;
    }



}

