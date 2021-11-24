public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int first;
    private final int limitLength = 8;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = (items.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
    }

    /** Resizes up to required capacity. */
    private void resize(int cap) {
        T[] a = (T []) new Object[cap];
        int tempLength = items.length - first;
        System.arraycopy(items, first, a, a.length / 2 - 1, tempLength);
        if (tempLength != items.length) {
            System.arraycopy(items, 0, a, (a.length / 2 - 1) + tempLength, first);
        }
        items = a;
        nextFirst = minusOne(a.length / 2 - 1);
        first = plusOne(nextFirst);
        nextLast = plusOne(nextFirst + size);
    }

    /** Resizes down to required capacity. */
    private void resizeDown(int cap) {
        if (cap < limitLength){
            return;
        }
        T[] a = (T []) new Object[cap];
        if (first + size > items.length) {
            int tempLength = items.length - first;
            int tempLength2 = first + size - items.length;
            System.arraycopy(items, first, a, a.length / 2 - 1, tempLength);
            System.arraycopy(items, 0, a, (a.length / 2 - 1) + tempLength, tempLength2);
        }else{
            System.arraycopy(items, first, a, a.length / 2 - 1, size);
        }
        items = a;
        nextFirst = minusOne(a.length / 2 - 1);
        first = plusOne(nextFirst);
        nextLast = plusOne(nextFirst + size);
    }

    /** Helper methods. */
    private int minusOne(int index){
        if (index == 0){
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index){
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    /** Adds an item of type T to the front of the deque.*/
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        first = nextFirst;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (items[first] == null){
            first = nextLast;
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space. */
    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("null");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = items[first];
            items[nextFirst + 1] = null;
            first = plusOne(first);
            nextFirst = plusOne(nextFirst);
            size -= 1;
            if ((double) size / items.length < 0.25) {
                resizeDown(items.length / 2);
            }
            return temp;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = items[nextLast];
            items[nextLast] = null;
            nextLast = minusOne(nextLast);
            size -= 1;
            if ((double) size / items.length < 0.25) {
                resizeDown(items.length / 2);
            }
            return temp;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null. */
    @Override
    public T get(int index) {
        if (isEmpty() || index > (size - 1) || index < 0) {
            return null;
        }
        if (index + first < items.length) {
            return items[first + index];
        }
        return items[first + index - items.length];
    }
}
