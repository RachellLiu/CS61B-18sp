import javax.swing.*;

public class LinkedListDeque<T> {
    private ListNode sentinel;
    private int size;
    private class ListNode {
        private T item;
        private ListNode next;
        private ListNode prev;
        private ListNode(T item0, ListNode next0, ListNode prev0){
            item = item0;
            next = next0;
            prev = prev0;
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new ListNode(null,new ListNode(null, null, null), new ListNode(null,null,null) );
    }
    public void addFirst(T item){
        if(isEmpty()){
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }else {
            sentinel.next = new ListNode(item, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }
    public void addLast(T item){
        if(isEmpty()){
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }else{
            sentinel.prev = new ListNode(item, sentinel, sentinel.prev);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(isEmpty()){
            System.out.println("null");
            return;
        }
        ListNode temp = sentinel;
        while(temp.next.item != null){
            System.out.print(temp.next + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        if(isEmpty()) {
            return null;
        }else {
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return temp;
        }
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }else {
            T temp = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return temp;
        }
    }
    public T get(int index){
        if(isEmpty() || index > size - 1){
            return null;
        }
        ListNode temp = sentinel;
        int i = 0;
        while (i < index){
            temp = temp.next;
            i += 1;
        }
        return temp.item;
    }
    public T getRecursive(int index){
        if (isEmpty() || index > size - 1){
            return null;
        }
        return getRecursiveHelper(index, sentinel);
    }
    private T getRecursiveHelper(int index, ListNode deque){
        if(index == 0){
            return deque.next.item;
        }
        return getRecursiveHelper(index - 1, deque.next);
    }
}
