package iterator;


public class MyIterator<E> implements Iterator<E> {
    private int nextIndex = 0;
    private E[] data;
    private int length;

    public MyIterator myIterator() {
        return new MyIterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
        return (E) data[nextIndex++];
    }

    @Override
    public boolean hasNext() {
        return nextIndex < length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E previous() {
        return (E) data[--nextIndex];
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }


}

