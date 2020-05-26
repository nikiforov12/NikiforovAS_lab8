package lab8;

public class Node {
	private int key;
	private Node left = null;
	private Node right = null;
	private Node parent = null;
	
	Node(int value){key = value;}
	
	Node getLeft() {return left;}
	Node getRight() {return right;}
	Node getParent() {return parent;}
	int getKey() {return key;}
	
	void setKey(int vKey) {key=vKey;}
	void setLeft(Node vLeft) {left = vLeft;}
	void setRight(Node vRight) {right = vRight;}
	void setParent(Node vParent) {parent = vParent;}
}