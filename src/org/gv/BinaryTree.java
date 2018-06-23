package org.gv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author GV
 *
 */
public class BinaryTree {

	boolean checkBST(BSTNode root) {
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

//	/*
//	 * Returns true if the given tree is a BST and its values are >= min and <= max.
//	 */
//	boolean isBSTUtil(Node node, int min, int max) {
//		/* an empty tree is BST */
//		if (node == null)
//			return true;
//
//		/* false if this node violates the min/max constraints */
//		if (node.data < min || node.data > max)
//			return false;
//
//		/*
//		 * otherwise check the subtrees recursively tightening the min/max constraints
//		 */
//		// Allow only distinct values
//		return (isBSTUtil(node.left, min, node.data - 1)
//				&& isBSTUtil(node.right, node.data + 1, max));
//	}

	/*
	 * Returns true if the given tree is a BST and its values are >= min and <= max.
	 */
	<T> boolean isBSTUtil(BSTNode node, Comparable<T> min, Comparable<T> max) {
		/* an empty tree is BST */
		if (node == null)
			return true;
		/* false if this node violates the min/max constraints */
		if (node.getData().compareTo(min) < 0 || node.getData().compareTo(max) > 0)
			return false;
		
		/*
		 * otherwise check the subtrees recursively tightening the min/max constraints
		 */
		// Allow only distinct values
		return (isBSTUtil(node.getLeft(), min, node.getData())
				&& isBSTUtil(node.getRight(), node.getData(), max));
	}

	/* Driver program to test above functions */
	public static void main(String args[]) {
		BinaryTree tree = new BinaryTree();
		BSTNode<String> root = new BSTNode<>();

		final Scanner scanner = new Scanner(System.in);
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		
		List<String> lines = new ArrayList<>();

		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();

			System.out.println("line " + line);
			if(line.isEmpty()) {
				break;
			}

			lines.add(line);
		}

		scanner.close();


		// printing lines in reverse

		for (int i = lines.size() - 1; i >= 0; i--) {

			String line = lines.get(i);

			System.out.println("|" + line + "|");

			for (int index = 0; index < line.length(); index++) {

				root.put(line.charAt(index) + "");				

			}

		}

		BSTNode.preOrder(root);
		
		
		
//		root.put(2);
//		root.put(5);
//		root.getLeft().put(1);
//		root.getLeft().put(0);
		

//		if (tree.checkBST(root))
//			System.out.println("IS BST");
//		else
//			System.out.println("Not a BST");
//		
//		System.err.println("Left value ::: " + root.getLeft().getLeft().getLeft().getData());
//		System.err.println("Max Depth ::: " + root.maxDepth(root));
//		System.err.println("Search ::: " + root.search(-1));
	}
}
