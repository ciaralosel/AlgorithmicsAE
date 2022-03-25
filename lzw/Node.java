
public class Node {
	
	private char letter; // label on incoming branch
	private boolean isWord; // true when node represents a word
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	
	/** create a new node with letter c */
	public Node(char c){
		letter = c;
		isWord = false;
		sibling = null;
		child = null;
	}
	
	// include accessors and mutators for
	// the various components of the class
	
	public char getLetter() {
		return this.letter;
	}
	
	public boolean getIsWord() {
		return this.isWord;
	}
	
	public Node getSibling() {
		return this.sibling;
	}
	
	public Node getChild() {
		return this.child;
	}
	
	public void setLetter(char c) {
		this.letter = c;
	}
	
	public void setIsWord(boolean b) {
		this.isWord = b;
	}
	
	public void setSibling(Node s) {
		this.sibling = s;
	}
	
	public void setChild(Node c) {
		this.child = c;
	}
	
}
