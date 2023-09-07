package csci2320.collections1;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LinkedSeq<E> implements Seq<E>, Iterable<E> {
  private static class Node<E> {
    E value;
    Node<E> next;
    Node(E value, Node<E> next) {
      this.value = value;
      this.next = next;
    }
  }
  private Node<E> head = null;
  private Node<E> tail = null;
  private int numElems = 0;

  @Override
  public E get(int index) {
    if (index < 0 || index >= numElems)
      throw new IndexOutOfBoundsException("Get at " + index + " of " + numElems);
    Node<E> rover = head;
    for (int i = 0; i < index; ++i) rover = rover.next;
    return rover.value;
  }

  @Override
  public void set(int index, E elem) {
    if (index < 0 || index >= numElems)
      throw new IndexOutOfBoundsException("Set at " + index + " of " + numElems);
    Node<E> rover = head;
    for (int i = 0; i < index; ++i) rover = rover.next;
    rover.value = elem;
  }

  @Override
  public void add(E elem) {
    if (isEmpty()) {
      head = tail = new Node<E>(elem, null);
    } else {
      tail.next = new Node<E>(elem, null);
      tail = tail.next;
    }
    numElems++;
  }

  @Override
  public void insert(int index, E elem) {
    if (index < 0 || index > numElems)
      throw new IndexOutOfBoundsException("Insert at " + index + " of " + numElems);
    numElems++;
    if (index == 0) {
      head = new Node<E>(elem, head);
      if (tail == null) tail = head;
    } else {
      Node<E> rover = head;
      for (int i = 0; i < index - 1; ++i) rover = rover.next;
      rover.next = new Node<E>(elem, rover.next);
      if (rover == tail) tail = tail.next;
    }
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= numElems)
      throw new IndexOutOfBoundsException("Remove at " + index + " of " + numElems);
    numElems--;
    if (index == 0) {
      var tmp = head.value;
      head = head.next;
      if (head == null) tail = null;
      return tmp;
    } else {
      Node<E> rover = head;
      for (int i = 0; i < index - 1; ++i) rover = rover.next;
      var tmp = rover.next.value;
      rover.next = rover.next.next;
      if (rover.next == null) tail = rover;
      return tmp;
    }
  }

  @Override
  public int size() {
    return numElems;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private Node<E> rover = head;
      public E next() {
        var tmp = rover.value;
        rover = rover.next;
        return tmp;
      }
      public boolean hasNext() {
        return rover != null;
      }
    };
  }
  
  public <E2> E2 foldLeft(E2 zero, BiFunction<E2, E, E2> f) {
    E2 acc = zero;
    for (E e: this) {
      acc = f.apply(acc, e);
    }
    return acc;
  }
}
