// This is another implementation of the traditional Queue. This implementation of the
// Queue is done using an array.

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class ArrayQueue<E> {
    private E[] queue;
    private int size;

    // constructs a new ArrayQueue object.
    public ArrayQueue() {
        this.queue = (E[]) new Object[10];
        this.size = 0;
    }

    // constructs a new ArrayQueue object with the elements given in the parameter.
    // throws IllegalArgumentException() if there is a null element.
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

    // returns whether or not the ArrayQueue is empty.
    public boolean isEmpty() {
        return this.size == 0;
    }

    // removes all elements from the ArrayQueue.
    public void clear() {
        this.size = 0;
        this.queue = (E[]) new Object[10];
    }

    // returns the size of the ArrayQueue.
    public int size() {
        int returned = this.size;
        return returned;
    }

    // returns the ArrayQueue object as a String representation.
    public String toString() {
        String result = "[";
        if (this.isEmpty()) {
            result += "]";
            return result;
        }
        for (int i = 0; i < this.size - 1; i++) {
            result += this.queue[i] + ", ";
        }
        result += this.queue[this.size] + "]";
        return result;
    }

    // retrieves the element at a certain index and returns it.
    // throws IndexOutOfBoundsException() if index is larger than the ArrayQueue.
    public E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E returned = this.queue[index];
        return returned;
    }

    // returns the index of the first occurrence of the object in the ArrayQueue.
    // returns -1 if object is not in the ArrayQueue object.
    // throws IllegalArgumentException() if the element passed in is null.
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

    // remove and return the element at the front of the ArrayQueue.
    // throws IllegalArgumentException() if the ArrayQueue does not have anything in it.
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

    // removes all occurrences of the element passed in within the ArrayQueue.
    // throws IllegalArgumentException() if the element in parameter is null.
    public void removeAll(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        E[] temp = (E[]) new Object[this.size];
        int index = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.queue[i] != element) {
                temp[index] = this.queue[i];
                index++;
            }
        }
        this.clear();
        this.enqueue(temp);
    }

    // puts in the element passed in at the end of the ArrayQueue.
    // throws IllegalArgumentException() if the element passed in is null.
    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        this.resize();
        E add = element;
        this.queue[size] = add;
        this.size++;
    }

    // puts in all of the elements passed in by the parameter into the ArrayQueue.
    // throws IllegalArgumentException() if there are null objects in the parameter.
    public void enqueue(E[] input) {
        E[] temp = (E[]) new Object[input.length];
        for (int i = 0; i < input.length; i++) {
            if (input[i] == null) {
                throw new IllegalArgumentException();
            }
            E add = input[i];
            temp[i] = add;
        }
        int tempSize = input.length + this.size;
        while (tempSize > this.queue.length) {
            this.resize();
            tempSize = input.length + this.queue.length;
        }
        for (int i = 0; i < input.length; i++) {
            E add = input[i];
            this.queue[i + this.size] = add;
            this.size++;
        }
    }

    // checks to make sure the internal array is large enough, and expand it if necessary.
    private void resize() {
        if (this.size == this.queue.length) {
            E[] temp = (E[]) new Object[this.queue.length * 2];
            for (int i = 0; i < this.queue.length; i++) {
                temp[i] = this.queue[i];
            }
            this.queue = temp;
        }
    }

    // put the elements of the ArrayQueue into a random order.
    public void shuffle() {
        Set<Integer> set = new HashSet<Integer>();
        Random r = new Random();
        E[] temp = (E[]) new Object[this.size];
        int num = r.nextInt(this.size);
        int index = 0;
        while (set.size() != this.size) {
            if (!set.contains(num)) {
                set.add(num);
                temp[index] = this.queue[num];
                index++;
            }
            num = r.nextInt(this.size);
            if (set.size() == this.size - 1) {
                for (int i = 0; i < this.size; i++) {
                    if (!set.contains(i)) {
                        temp[index] = this.queue[i];
                        set.add(i);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < this.size; i++) {
            E add = temp[i];
            this.queue[i] = add;
        }
    }

    // returns whether or not the ArrayQueue has no duplicate elements.
    public boolean isUnique() {
        if (this.size < 2) {
            return true;
        }
        Set<E> set = new HashSet<E>();
        for (E element : this.queue) {
            if (set.contains(element)) {
                return false;
            } else {
                set.add(element);
            }
        }
        return true;
    }

    // removes duplicate elements from the ArrayQueue object.
    public void removeDuplicates() {
        if (this.size > 1) {
            Set<E> set = new HashSet<E>();
            int iterations = this.size;
            for (int i = 0; i < iterations; i++) {
                if (!set.contains(this.queue[i])) {
                    set.add(this.queue[i]);
                } else {
                    shift(i);
                    this.size--;
                }
            }
        }
    }

    // shifts all of the elements from the ArrayQueue over starting from a certain index.
    private void shift(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.queue[i] = this.queue[i + 1];
        }
    }

    // reverses the order of the elements in the ArrayQueue.
    public void reverse() {
        E[] temp = (E[]) new Object[this.size];
        for (int i = this.size - 1; i >= 0; i--) {
            temp[(this.size - 1) - i] = this.queue[i];
        }
    }

    // start: [2, 5, 1, 6, 7]
    //          [2, 2, 1, 6, 7]
    // temp = 5
    //
    // end: [7, 2, 5, 1, 6]

    public void rotate(int times) {
        if (this.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (times != 0 && this.size > 1) {
            for (int i = 0; i < times; i++) {
                E temp = this.queue[1];
                for (int j = 1; j < this.size; i++) {
                    if (j == this.size - 1) {
                        this.queue[0] = temp;
                    } else {
                        temp = this.queue[j + 1];
                        this.queue[j + 1] = this.queue[j];
                    }
                }
            }
        }
    }
}
