package assignment2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {  

  private class Node {
    Item data;
    Node next = null;
    Node prev = null;
  }
  
  private Node first;
  private Node last;
  private int size;
  
  public Deque() {
    first = null;
    last = null;
    size = 0;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  public void addFirst(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException();
    }
    Node newFirst = new Node();
    newFirst.data = item;
    if (first == null) {
      first = newFirst;
      last = newFirst;
    } else {
      first.prev = newFirst;
      newFirst.next = first;
      first = newFirst;
    }
    size++;
  }
  
  public void addLast(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException();
    }
    if (last == null) {
      addFirst(item);
      return;
    }
    Node newLast = new Node();
    newLast.data = item;
    last.next = newLast;
    newLast.prev = last;
    last = newLast;
    size++;
  }
  
  public Item removeFirst() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    final Item firstItem = first.data;
    first = first.next;
    if (first == null) {
      last = null;
    }
    size--;
    return firstItem;
  }
  
  public Item removeLast() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    final Item lastItem = last.data;
    last = last.prev;
    if (last == null) {
      first = null;
    }
    size--;
    return lastItem;
  }
  
  //Iterator stuff  
  @Override
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }
  
  private class DequeIterator implements Iterator<Item> {
    Node current = first;
    
    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (current == null) {
        throw new java.util.NoSuchElementException();
      }
      Item item = current.data;
      current = current.next;
      return item;
    }
    
    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }
  
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addLast(0);
    StdOut.println(deque.removeLast());
    deque.addLast(2);
    StdOut.println(deque.removeLast());
  }
}
