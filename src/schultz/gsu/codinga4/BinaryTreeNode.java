package schultz.gsu.codinga4;

public class BinaryTreeNode {
	
	private char element;
	private BinaryTreeNode parent;
	private BinaryTreeNode leftChild;
	private BinaryTreeNode rightChild;
	
	public BinaryTreeNode(char element) {
		this.element = element;
		parent = null;
		leftChild = null;
		rightChild = null;
	}

	public char getElement() {
		return element;
	}

	public void setElement(char element) {
		this.element = element;
	}

	public BinaryTreeNode getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}

	public BinaryTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode leftChild) {
		this.leftChild = leftChild;
		this.leftChild.setParent(this);
	}

	public BinaryTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode rightChild) {
		this.rightChild = rightChild;
		this.rightChild.setParent(this);
	}
	
	public int numChildren() {
		if(leftChild == null && rightChild == null)
			return 0;
		
		else if(leftChild != null && rightChild != null)
			return 2;
		
		return 1;
	}
	
	public BinaryTreeNode getSibling() {
		if(parent == null)
			return null;
		
		else {
			if(this == parent.getLeftChild())
				return parent.getRightChild();
			else
				return parent.getLeftChild();
		}
	}
	
}
