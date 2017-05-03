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
		private final T value;
		private Node child;
		private int freq;
		
		Node(T value) {
			this.value = value;
			this.freq = 1;
		}
		
		public Node(T value, Node child) {
			this.value = value;
			this.child = child;
			this.freq = 1;
		}
		
		public T getValue() {
			return value;
		}
		
		public Node getChild() {
			return child;
		}
		
		public void setChild(Node child) {
			this.child = child;
		}
		
		public void increaseFreq() {
			freq += 1;
		}
		
		public void decreaseFreq() {
			freq -= 1;
		}

		public int getFreq() {
			return freq;
		}
		
		public boolean equals(Node node) {
			return value.equals(node.value);
		}
		
		public String toString() {
			return "[" + value.toString() + "," + freq + "]";
		}
		
		public Node copy() {
			return new Node(value, child);
		}
	}
	
	/**
	 * The head first start node of this list.
	 */
	private Node first;
	
	public LinkedList() {
	
	}
	
	public LinkedList(Node first) {
		this.first = first;
	}
	
	public void addFirst(T value) {
		Node node = new Node(value);
		node.child = first;
		first = node;
	}
	
	public void addLast(T value) {
		Node add = new Node(value);
		if(first == null)
			first = add;
		else
			getLast().child = add;
	}
	
	public void removeFirst() {
		if(first.child == null)
			first = null;
		else
			first = first.child;
	}
	
	public void removeLast() {
		Node preLast = getPreLast();
		preLast.child = null;
	}
	
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
	
	public Node getFirst() {
		return first;
	}
	
	public Node getLast() {
		Node last = first;
		while(last.child != null)
			last = last.child;
		return last;
	}
	
	public Node getPreLast() {
		Node last = first;
		while(last.child != null) {
			last = last.child;
			if(last.child.child == null)
				return last;
		}
		return last;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void clear() {
		first = null;
	}
	
	public int size() {
		Node last = first;
		int size = 1;
		while(last.child != null) {
			last = last.child;
			size++;
		}
		return size;
	}
	
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
	
	public void print() {
		System.out.println(this);
	}

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
