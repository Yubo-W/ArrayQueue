// my implementation of the queue data structure.

public class ArrayQueue<E> {
    private E[] queue;
    private int size;

    public ArrayQueue() {
        this.queue = (E[]) new Object[10];
        this.size = 10;
    }

    public ArrayQueue(E[] input) {
        E[] temp = (E[]) new Object[input.length];
        for (int i = 0; i < input.length; i++) {
            E add = input[i];
            temp[i] = add;
        }
        for (int i = 0; i < temp.length; i++) {
            E add = temp[i];
            this.queue[i] = add;
        }
        this.size = input.length;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        int returned = this.size;
        return returned;
    }
}
