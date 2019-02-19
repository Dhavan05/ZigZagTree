package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class BTreeZigZag {
	private Node root;
	Stack<Node> primary = new Stack<Node>();
	private Queue<Node> breadthFirstSearch = new LinkedList<>();
	char direction = 'l';

	public BTreeZigZag() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean insert(String value) {
		root = insert(root, value);
		return true;
	}

	private Node insert(Node node, String value) {
		if (node == null)
			node = new Node(value);
		else if (value.compareToIgnoreCase(node.getValue()) > 0)
			node.setRight(insert(node.getRight(), value));
		else if (value.compareToIgnoreCase(node.getValue()) < 0)
			node.setLeft(insert(node.getLeft(), value));
		return node;
	}

	public int countNodes() {
		return count(root);
	}

	private int count(Node node) {
		if (node == null)
			return 0;
		else {
			int n = 1;
			n += count(node.getLeft()) + count(node.getRight());
			return n;
		}
	}

	public void display() {
		primary.add(root);
		display(root);
		System.out.println();
	}

	private void display(Node node) {
		if (node == null )
			return;
		if( breadthFirstSearch.size() == 0 ) {
			while(!primary.isEmpty())
				breadthFirstSearch.add(primary.pop());
			if( direction == 'l')
				direction = 'r';
			else
				direction = 'l';
		}
		Node cur = breadthFirstSearch.poll();
		if (cur != null) {
			if(direction == 'l') {
				if (cur.getLeft() != null)
					primary.add(cur.getLeft());
				if (cur.getRight() != null)
					primary.add(cur.getRight());
			}else {
				if (cur.getRight() != null)
					primary.add(cur.getRight());
				if (cur.getLeft() != null)
					primary.add(cur.getLeft());
			}
				System.out.print(cur.getValue() + " ");
				display(cur);
		}
	}
}

public class ZigZag {

	public static void main(String[] args) {
		BTreeZigZag binaryTree = new BTreeZigZag();

		binaryTree.insert("J");
		binaryTree.insert("D");
		binaryTree.insert("K");
		binaryTree.insert("Z");
		binaryTree.insert("W");
		binaryTree.insert("O");
		binaryTree.insert("S");
		binaryTree.insert("Y");
		binaryTree.insert("T");
		binaryTree.insert("B");
		binaryTree.insert("C");
		System.out.println("Count: " + binaryTree.countNodes());
		binaryTree.display();
	}

}
