package U3_EstructurasLineales.StackQueue;

public class BoundedQueue<T> {
    private final T[] elements;
    private int first;
    private int last;
    private int qty= 0;

    @SuppressWarnings("unchecked")
    public BoundedQueue(int limit) {
        elements =(T[]) new Object[limit];
        first = last = 0;
    }

    public boolean isEmpty() {
        return qty == 0;
    }
    public boolean isFull() {
        return qty == elements.length;
    }

    public void enqueue(T element) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        elements[last++] = element;
        qty++;
        if (last >= elements.length) {
            last = 0;
        }

    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T res = elements[first++];
        qty--;
        if (first >= elements.length) {
            first = 0;
        }


        return res;
    }

    private void dump() { // TODO
    }
}