/**
* A class that implements a data structure using Binary Search tree.
* Class: BinarySearchTree.java
* Author: Majde Judeh
* Date: 11/18/2015
*/



import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {

	private E data;
	private BinarySearchTree<E> leftSubtree, rightSubtree;

	
	/**
	 * Initialize the BST with the given data. Both
	 * the left and right subtrees should initially
	 * be null.
	 *
	 * @param datum The node's data.
	 */

	public BinarySearchTree(E datum) {
		data = datum;
		leftSubtree = null;
		rightSubtree = null;
	}
	
	public E getData(){
		return data;
	}
	
	
	/**
	 * Inserts a new data item to the tree.
	 * Whatever is inserted will always be a leaf until possibly something
	 * is inserted afterwards.
	 * 
	 * @Requires, values are not equal to any other values already in the tree.
	 * 
	 * @param datum The value to insert.
	 */
	public void insert(E datum){
		
		//This is the "BST node" that will be inserted in it's respective position.
		BinarySearchTree<E> newSubTree = new BinarySearchTree<E>(datum);
		
		/**
		 * The "BST node" passed in is compared to the "BST node" that is currently being reffered to.
		 * The first node to be compared against to this new node is always the root.
		 * If it the node to be added is less then the node that it is being compared against,
		 * then the node will be added to the left of the current node if the reference to the
		 * left node is still null, meaning that nothing is there yet. Otherwise it will recursively call
		 * itself with that node then complete the comparison again. The method is the same for if it is greater
		 * but instead it goes to the right.
		 */
		if(this.data.compareTo(datum) > 0){
			if(this.leftSubtree == null){
	
				this.leftSubtree = newSubTree;
				
			}
			else{
				this.leftSubtree.insert(datum);
			}
		}
		else if (this.data.compareTo(datum) < 0){
			if(this.rightSubtree == null){
				
				this.rightSubtree = newSubTree;
				
			}
			else{
				this.rightSubtree.insert(datum);
			}
		}
		

	}
	
	/**
	 * Returns true if the searchValue is in the tree or
	 * false otherwise. This method will implement the
	 * recursive binary search algorithm.
	 *
	 * @param searchValue The value to search for.
	 * @return Whether the searchValue is in the BST.
	 */
	public boolean contains(E searchValue){
		
		//Checks to see if the searchValue is equal to the current BSTnode.
		//Returns true is it is.
		if(this.data.compareTo(searchValue) == 0)	
			return true;
			
		//If not, checks to see if the search value is less than the value
		//it is being compared to. If so, calls this method W.R.T. the leftSubTree.
		if (this.leftSubtree != null && this.data.compareTo(searchValue) > 0){
			return this.leftSubtree.contains(searchValue);
		}
		//If not, checks to see if the search value is greater than the value
		//it is being compared to. If so, calls this method W.R.T. the rightSubTree.
		if (this.rightSubtree != null && this.data.compareTo(searchValue) < 0){
			return this.rightSubtree.contains(searchValue);
			
		}
		
		//Returns false if the value is not found.
		return false;
	}
	
	/**
	 * Returns an iterator that traverses the
	 * BST following the in-order approach.
	 */
	public Iterator<E> getInOrderIterator(){
		 return new InOrderIterator();
	}
	
	
	/**
	 * Returns an iterator that traverses the
	 * BST following the pre-order approach.
	 */
	public Iterator<E> getPreOrderIterator(){
		return new PreOrderIterator();
	}
	
	/**
	 * Returns an iterator that traverses the
	 * BST following the post-order approach.
	 */
	public Iterator<E> getPostOrderIterator(){
		return new PostOrderIterator();
	}
	
	/**
	 * The Class implements Iterator and makes an Iterator that will iterate
	 * through the BST inOrder. 
	 * 
	 * This is done through recursion.
	 * 
	 * The only difference between this class and the classes that follow it are the order of the algorithm
	 * in the method "createListRecursively." 
	 * 
	 * Recursively checks the leftSubTree first.
	 * Adds the data to the linkedList second. (Visits the node.)
	 * Then recursively checks the rightSubtree third.
	 * 
	 */ 
	 
	private class InOrderIterator implements Iterator<E>{
		
		//Uses a linkedList to store the data. The linked list is what is actually
		//iterated through.
		private LinkedList<E> listForInOrder; 
		
		/**
		 * Constructor that instansiates the LinkedList and also stores the 
		 * the data inOrder through the linked list.
		 */
		public InOrderIterator(){
			listForInOrder = new LinkedList<E>();
			createListRecursively(BinarySearchTree.this);
		}
		
		public void createListRecursively(BinarySearchTree<E> current){
				
				//Checks to see if there is a leftSubtree and then recursively
				//calls this method if there is a leftSubtree.
				if(current.leftSubtree != null)
					createListRecursively(current.leftSubtree);
				
				//Adds the data of the BST object to the LinkedList.
				listForInOrder.add(current.data);
				
				//Checks to see if there is a rightSubtree and then recursively
				//calls this method if there is a rightSubTree.
				if(current.rightSubtree != null)
					createListRecursively(current.rightSubtree);


		}
		
		//Checks to see if the list is empty.
		@Override
		public boolean hasNext() {
			return !(listForInOrder.isEmpty());
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		//Returns the data from the list and removes it from the list at the same time.
		@Override
		public E next(){
			return listForInOrder.remove();
		}
		
	}//End of InOrderIterator
	
	/**
	 * Again, the only difference between this class and the previous is the order 
	 * in which the method "createListRecursively" is implemented.
	 * 
	 * Visits the node first and adds it to the linked list.
	 * Recursively checks the leftSubTree.
	 * Recursively checks the rightSubTree.
	 * 
	 */
	private class PreOrderIterator implements Iterator<E>{
		private LinkedList<E> listForPreOrder;
		
		public PreOrderIterator(){
			listForPreOrder = new LinkedList<E>();
			createListRecursively(BinarySearchTree.this);
		}
		
		public void createListRecursively(BinarySearchTree<E> current){
			
			
				listForPreOrder.add(current.data);
				
				if(current.leftSubtree != null)
					createListRecursively(current.leftSubtree);
				
				if(current.rightSubtree != null)
					createListRecursively(current.rightSubtree);


		}
		
		@Override
		public boolean hasNext() {
			return !(listForPreOrder.isEmpty());
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		@Override
		public E next(){
			return listForPreOrder.remove();
		}
		
	}//end of PreOrder
	
	/**
	 * Recursively checks the leftSubTree.
	 * Recursively checks the rightSubTree.
	 * Visits the node and adds it to the linked list.
	 */ 
	private class PostOrderIterator implements Iterator<E>{
		private LinkedList<E> listForPostOrder;
		
		public PostOrderIterator(){
			listForPostOrder = new LinkedList<E>();
			createListRecursively(BinarySearchTree.this);
		}
		
		public void createListRecursively(BinarySearchTree<E> current){
				if(current.leftSubtree != null)
					createListRecursively(current.leftSubtree);
				if(current.rightSubtree != null)
					createListRecursively(current.rightSubtree);
				listForPostOrder.add(current.data);

		}
		
		@Override
		public boolean hasNext() {
			return !(listForPostOrder.isEmpty());
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		@Override
		public E next(){
			return listForPostOrder.remove();
		}
		
	}//end of Post order iterator.
	
}//End of BST class.

	//So at first I did two of the iterators iteratively and then I realized how much
	//easier it is to do recursively. Since I really do not like to comment,
	//I'm going to change the way I did the other
	//two to make them recursive and then comment on the general structure of all of them. 
	//But I still wanted to leave these here because I feel an attatchment to them
	//and In case I ever look back at this code.
	
	
	/*
			private class InOrderIterator implements Iterator<E>{
				
				private BinarySearchTree<E> current;
				private E data;
				private Stack<BinarySearchTree<E>> stackForInOrder;
				
				public InOrderIterator(){
					stackForInOrder = new Stack<BinarySearchTree<E>>();
					current = BinarySearchTree.this;
					while (current != null){
						stackForInOrder.push(current);
						current = current.leftSubtree;
					}
				}
				
				
				@Override
				public boolean hasNext() {
					return !stackForInOrder.isEmpty();
				}
				
				@Override
				public void remove(){
					throw new UnsupportedOperationException();
				}
				
				@Override
				public E next(){
					current = stackForInOrder.pop();
					data = current.data;
					if (current.rightSubtree != null){
						current = current.rightSubtree;
						while (current != null){
							stackForInOrder.push(current);
							current = current.leftSubtree;
						}
					}
					return data;
					
				}
				
				
			}//end of InOrderIterator class
			
			private class PreOrderIterator implements Iterator<E>{
				
				private BinarySearchTree<E> current;
				private Stack<BinarySearchTree<E>> stackForPreOrder;
				private LinkedList<E> listForPreOrder;
				
				public PreOrderIterator(){
					listForPreOrder = new LinkedList<E>();
					stackForPreOrder = new Stack<BinarySearchTree<E>>();
					stackForPreOrder.push(BinarySearchTree.this);
					
					while(!stackForPreOrder.empty()){
						current = stackForPreOrder.pop();
						listForPreOrder.add(current.data);
						if(current.rightSubtree != null){
							stackForPreOrder.push(current.rightSubtree);
						}
						if(current.leftSubtree != null){
							stackForPreOrder.push(current.leftSubtree);
						}
					}
					
				}//end of constructor for PreOrderIterator
				
				@Override
				public boolean hasNext() {
					return !(listForPreOrder.isEmpty());
				}
				
				@Override
				public void remove(){
					throw new UnsupportedOperationException();
				}
				
				@Override
				public E next(){
					return listForPreOrder.remove();
				}
			}//end of class for PreOrderIterator
			
	*/