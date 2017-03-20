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
            if (input[i] == null) {
                throw new IllegalArgumentException();
            }
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

    public void clear() {
        this.size = 10;
        this.queue = (E[]) new Object[this.size];
    }

    public int size() {
        int returned = this.size;
        return returned;
    }

    public E get(int index) {
        if (index >= this.size) {
            throw new IllegalArgumentException();
        }
        E returned = this.queue[index];
        return returned;
    }

    public int getIndex(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < this.size; i++) {
            if (this.queue[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        E returned = this.queue[0];
        for (int i = 1; i < this.size; i++) {
            this.queue[i - 1] = this.queue[i];
        }
        this.size--;
        return returned;
    }

    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        resize();
        E add = element;
        this.queue[size] = add;
        this.size++;
    }

    public void enqueue(E[] input) {
        E[] temp = (E[]) new Object[input.length];
        for (int i = 0; i < input.length; i++) {
            E add = input[i];
            temp[i] = add;
        }
        int tempSize = input.length + this.size;
        while (tempSize > this.queue.length) {
            resize();
            tempSize = input.length + this.queue.length;
        }
        for (int i = 0; i < input.length; i++) {
            E add = input[i];
            this.queue[i + this.size] = add;
            this.size++;
        }
    }

    private void resize() {
        if (this.size == this.queue.length) {
            E[] temp = (E[]) new Object[this.queue.length * 2];
            for (int i = 0; i < this.queue.length; i++) {
                temp[i] = this.queue[i];
            }
            this.queue = temp;
        }
    }
}
