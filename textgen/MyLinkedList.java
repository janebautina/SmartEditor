package textgen;

import java.util.AbstractList;

import org.omg.CORBA.Current;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Jane Bautina
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException("Setting null poiter element");
		}
		// 1. Create a new LLNode
		LLNode newNode = new LLNode(element);
		// 2. Link the new Node in
		newNode.next = tail;
		newNode.prev = newNode.next.prev;
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		// 3. Update Size
		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		if (size == 0) { // empty list
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		if ((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		LLNode current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return (E) current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Setting null poiter element");
		} else if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException("Check out of bounds");
		} else {
			LLNode current = this.head.next;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			LLNode newNode = new LLNode(element);
			newNode.next = current;
			newNode.prev = current.prev;
			newNode.prev.next = newNode;
			current.prev = newNode;
			size++;
		}
	}

	/** Return the size of the list */
	public int size() {
		return this.size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (size == 0) { // empty list
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		if ((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		LLNode current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		current.next.prev = current.prev;
		current.prev.next = current.next;
		current.prev = null;
		current.next = null;
		E currentData = (E) current.data;
		current = null;
		size--;
		return currentData;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Setting null poiter element");
		}
		if (size == 0) {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		if ((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		LLNode current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E temp = (E) current.data;
		current.data = element;
		return temp;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
