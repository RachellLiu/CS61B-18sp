public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int first;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = (items.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
    }

    /** Resizes to required capacity. */
    private void resize(int cap){
        T[] a = (T []) new Object[cap];

        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = item;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space. */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("null");
            return;
        }
        for (int i = 0; i <= size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = items[0];
            System.arraycopy(items, 1, items, 0, size - 1);
            items[size - 1] = null;
            size -= 1;
            return temp;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = items[size - 1];
            items[size - 1] = null;
            size -= 1;
            return temp;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null. */
    public T get(int index) {
        if (isEmpty() || index > (size - 1)) {
            return null;
        }
        return items[index];
    }
}
