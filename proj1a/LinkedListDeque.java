public class LinkedListDeque<T> {
    /** The fist item is sentinel.next. */
    private ListNode sentinel;
    private int size;

    private class ListNode {
        private T item;
        private ListNode next;
        private ListNode prev;
        private ListNode(T item0, ListNode next0, ListNode prev0) {
            item = item0;
            next = next0;
            prev = prev0;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode(null, new ListNode(null, null, null),
                new ListNode(null, null, null));
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new ListNode(item, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev = new ListNode(item, sentinel, sentinel.prev);
            sentinel.prev.prev.next = sentinel.prev;
        }
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
        ListNode temp = sentinel;
        while (temp.next.item != null) {
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
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
            T temp = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
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
        ListNode temp = sentinel;
        int i = 0;
        while (i <= index) {
            temp = temp.next;
            i += 1;
        }
        return temp.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (isEmpty() || index > (size - 1)) {
            return null;
        }
        return getRecursiveHelper(index, sentinel);
    }

    /** Recursive get helper method. */
    private T getRecursiveHelper(int index, ListNode deque) {
        if (index == 0) {
            return deque.next.item;
        }
        return getRecursiveHelper(index - 1, deque.next);
    }
}
