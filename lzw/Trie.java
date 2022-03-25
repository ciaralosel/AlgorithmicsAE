import java.util.ArrayList;

public class Trie {
	
	// create root of the trie
	private Node root; 
	
	public Trie() {
		// null character in the root
		root = new Node(Character.MIN_VALUE); 
	}        
	
	// searches trie for char c 
	public Node search(char c, Node node) {
		if(node == null) {
			node = root;
		}
		Node nextNode = node.getChild();
		boolean nodeFound = false;
		while (nodeFound == false) {
			if (nextNode != null) {
				if (nextNode.getLetter() == c) {
					nodeFound = true; //iterate
					// char has been found so return corresponding node so that it can iterate from this node  
					return nextNode;
				} else {
					// char not been found so inspect next sibling
					nextNode = nextNode.getSibling();
				}
			} else {
				nodeFound = true; //insert
				// char not been found in trie so return original node 
				return node;
			}
		}
		return null;
	}
	
	// insert char c into trie with parent node node
	public void insert(char c, Node node) {
		if (node == null) {
			node = root;
		}
		Node parentNode = node;
		Node nodeToAdd = new Node(c);
		nodeToAdd.setSibling(parentNode.getChild());
		parentNode.setChild(nodeToAdd);
	}
	
}
