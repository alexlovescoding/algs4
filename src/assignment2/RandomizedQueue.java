import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>  {
  
  private int size;
  private Item[] container;
  
  public RandomizedQueue() {
    container = (Item[])new Object[1];
    size = 0;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  public void enqueue(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException();
    }
    if (size >= container.length) {
      resize(container.length * 2);
    }
    container[size++] = item;
  }
  
  public Item dequeue() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    int index = StdRandom.uniform(size);
    Item item = container[index];
    container[index] = null;
    size--;
    if (index != size - 1) {
      for (int i = index; i < size; i++) {
        container[i] = container[i + 1];
      }
    }
    if (size <= (int)(container.length / 4)) {
      resize((int)(container.length / 2));
    }
    return item;
  }
  
  public Item sample() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    int index = StdRandom.uniform(size);
    return container[index];
  }
  
  private void resize(int newSize) {
    Item[] newContainer = (Item[]) new Object[newSize];
    for (int i = 0; i < size; i++) {
      newContainer[i] = container[i];
    }
    container = newContainer;
  }
  
  //Iterator stuff
  @Override
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }
  
  private class RandomizedQueueIterator implements Iterator<Item> {    
    @Override
    public boolean hasNext() {
      return !isEmpty();
    }

    @Override
    public Item next() {
      return dequeue();
    }
    
    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }
  
}
