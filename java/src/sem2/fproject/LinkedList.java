package sem2.fproject;

import javafx.collections.ObservableList;

/**
 * An custom created single direction linked list.
 * @param <T> Any type of data.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class LinkedList<T> {
	
	/**
	 * A node used in this {@link LinkedList}.
	 */
	public class Node {
		
		/**
		 * The generic value of our node
		 */
		private final T value;
		/**
		 * The child node.
		 */
		private Node child;
		/**
		 * The frequency of this node in the list.
		 */
		private int freq;
		
		/**
		 * Creating a new {@link Node}.
		 * @param value starting value.
		 */
		Node(T value) {
			this.value = value;
			this.freq = 1;
		}
		
		/**
		 * Creating a new {@link Node}.
		 * @param value starting value.
		 * @param child child node.
		 */
		Node(T value, Node child) {
			this.value = value;
			this.child = child;
			this.freq = 1;
		}
		
		/**
		 * Gets the generic value of this node.
		 * @return {@link #value}.
		 */
		public T getValue() {
			return value;
		}
		
		/**
		 * Gets the child node.
		 * @return {@link #child}.
		 */
		public Node getChild() {
			return child;
		}
		
		/**
		 * Sets a new child node.
		 * @param child new child.
		 */
		public void setChild(Node child) {
			this.child = child;
		}
		
		/**
		 * Increasing the frequency of this node.
		 */
		public void increaseFreq() {
			freq += 1;
		}
		
		/**
		 * Decreasing the frequency of this node.
		 */
		public void decreaseFreq() {
			freq -= 1;
		}
		
		/**
		 * Gets the frequency of this node.
		 * @return {@link #freq}.
		 */
		public int getFreq() {
			return freq;
		}
		
		/**
		 * Checks if this node is equal to another node in value.
		 * @param node the node to check
		 * @return <code>true</code> if it is, <code>false</code> otherwise.
		 */
		public boolean equals(Node node) {
			return value.equals(node.value);
		}
		
		/**
		 * Copying a node to a new instance.
		 * @return new instance of this node.
		 */
		public Node copy() {
			return new Node(value, child);
		}
		
		public String toString() {
			return "[" + value.toString() + "," + freq + "]";
		}
	}
	
	/**
	 * The head first start node of this list.
	 */
	private Node first;
	
	/**
	 * Creating a new {@link LinkedList}.
	 */
	public LinkedList() {
	}
	
	/**
	 * Creating a new {@link LinkedList}.
	 * @param first starting node.
	 */
	public LinkedList(Node first) {
		this.first = first;
	}
	
	/**
	 * Adding a node to the head.
	 * @param value value to insert.
	 */
	public void addFirst(T value) {
		Node node = new Node(value);
		node.child = first;
		first = node;
	}
	
	/**
	 * Adding a node to the tail.
	 * @param value value to insert.
	 */
	public void addLast(T value) {
		Node add = new Node(value);
		if(first == null)
			first = add;
		else
			getLast().child = add;
	}
	
	/**
	 * Removing a head node.
	 */
	public void removeFirst() {
		if(first.child == null)
			first = null;
		else
			first = first.child;
	}
	
	/**
	 * Removing a tail node.
	 */
	public void removeLast() {
		Node preLast = getPreLast();
		preLast.child = null;
	}
	
	/**
	 * Removing a node found from the generic value.
	 * @param value value.
	 */
	public void remove(T value) {
		Node n = first;
		if(first == null)
			return;
		if(first.getValue().equals(value)) {
			first = first.child;
			return;
		}
		while(n.child != null) {
			if(n.child.getValue().equals(value)) {
				if(n.child.child != null)
					n.child = n.child.child;
				else
					n.child = null;
				break;
			}
			n = n.child;
		}
	}
	
	/**
	 * Adding a node to a specific index.
	 * @param value value to insert.
	 * @param index index to add to.
	 * @return <code>true</code> if succeeded, <code>false</code> otherwise.
	 */
	public boolean add(T value, int index) {
		if(index < 0)
			throw new IndexOutOfBoundsException();
		if(value == null)
			throw new NullPointerException();
		
		Node node = first;
		Node add = new Node(value);
		if(index == 0) {//head
			add.child = node;
			first = add;
			return true;
		}
		
		for(int i = 0; i < index - 1; i++) {
			if(node.child == null)
				return false;
			node = node.child;
		}
		
		if(node.child != null)
			add.child = node.child;
		node.child = add;
		return true;
	}
	
	/**
	 * Sets a node at a specific index.
	 * @param value value to insert.
	 * @param index index to set to.
	 * @return <code>true</code> if succeeded, <code>false</code> otherwise.
	 */
	public boolean set(T value, int index) {
		if(index < 0)
			throw new IndexOutOfBoundsException();
		if(value == null)
			throw new NullPointerException();
		
		Node node = first;
		Node add = new Node(value);
		if(index == 0) {//head
			add.child = first.child;
			first = add;
			return true;
		}
		
		for(int i = 0; i < index - 1; i++) {
			if(node.child == null)
				throw new IndexOutOfBoundsException();
			node = node.child;
		}
		
		if(node.child != null && node.child.child != null)
			add.child = node.child.child;
		node.child = add;
		return true;
	}
	
	public Node get(int index) {
		if(index < 0)
			throw new IndexOutOfBoundsException();
		if(first == null)
			throw new NullPointerException();
		
		Node node = first;
		for(int i = 0; i < index; i++) {
			if(node.child == null)
				throw new IndexOutOfBoundsException();
			node = node.child;
		}
		return node;
	}
	
	/**
	 * Searches a node depending on value.
	 * @param value value searching with.
	 * @return the node found, otherwise a null object.
	 */
	public Node search(T value) {
		if(value == null)
			throw new IndexOutOfBoundsException();
		if(first == null)
			return null;
		
		if(first.getValue().equals(value))
			return first;
		
		Node node = first;
		while(node.child != null) {
			node = node.child;
			if(node.getValue().equals(value))
				return node;
		}
		return null;
	}
	
	/**
	 * Sorting the {@link LinkedList} depending on {@link Node#freq}'s.
	 */
	public void sort() {
		if(first == null)
			return;
		Node grand = null;
		Node parent = first;
		while(parent.child != null) {
			if(parent.child.freq > parent.freq) {
				Node temp = parent.child;
				parent.child = temp.child;
				temp.child = parent;
				if(grand != null)
					grand.child = temp;
				grand = null;
				parent = first;
			}
			parent = parent.child;
			if(grand == null)
				grand = first;
			else
				grand = grand.child;
		}
	}
	
	/**
	 * Gets the head node.
	 * @return head node.
	 */
	public Node getFirst() {
		return first;
	}
	
	/**
	 * Gets the tail node.
	 * @return tail node.
	 */
	public Node getLast() {
		Node last = first;
		while(last.child != null)
			last = last.child;
		return last;
	}
	
	/**
	 * Gets the pre-last tail node.
	 * @return pre-last node.
	 */
	public Node getPreLast() {
		Node last = first;
		while(last.child != null) {
			last = last.child;
			if(last.child.child == null)
				return last;
		}
		return last;
	}
	
	/**
	 * Checks if this {@link LinkedList} is empty.
	 * @return <code>true</code> if it is, <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Clears this {@link LinkedList}
	 */
	public void clear() {
		first = null;
	}
	
	/**
	 * Gets the size of this {@link LinkedList}
	 * @return size integer
	 */
	public int size() {
		Node last = first;
		int size = 1;
		while(last.child != null) {
			last = last.child;
			size++;
		}
		return size;
	}
	
	/**
	 * Constructs a string with all {@link Node}s in this {@link LinkedList}
	 * @return data string.
	 */
	public String toString() {
		Node node = first;
		StringBuilder out = new StringBuilder();
		out.append(node.toString()).append(",");
		while(node.child != null) {
			node = node.child;
			out.append(node.toString()).append(",");
		}
		return out.toString();
	}
	
	/**
	 * Prints all of the {@link Node}s.
	 */
	public void print() {
		System.out.println(this);
	}
	
	/**
	 * Updates an {@link ObservableList} with {@link Node}s.
	 * @param ob list to update.
	 */
	public void updateView(ObservableList<String> ob) {
		Node n = first;
		if(first != null)
			ob.add(n.toString());
		while(n.child != null) {
			n = n.child;
			ob.add(n.toString());
		}
	}
	
}
