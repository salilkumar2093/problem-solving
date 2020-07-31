package com.manager.parking;

public class MaximumSumBst {
	public static void main(String... args) {
		MaximumSumBst obj = new MaximumSumBst();
		TreeNode node = new TreeNode(4);
		node.left = new TreeNode(3);
		node.left.left = new TreeNode(1);
		node.left.right = new TreeNode(2);
		System.out.println(obj.maxSumBST(node));
	}

	int globalMax = 0;

	public int maxSumBST(TreeNode root) {
		recur(root);
		return globalMax;
	}

	BSTSum recur(TreeNode root) {
		if (root == null)
			return null;

		BSTSum left = recur(root.left);
		BSTSum right = recur(root.right);

		if (left == null && right == null) {
			globalMax = Math.max(globalMax, root.val);
			return new BSTSum(root);
		}
		if (left != null && right != null) {
			return handleFullNode(root, left, right);
		}
		if (left != null) {
			return handleLeftNode(root, left);
		} else {
			return handleRightNode(root, right);
		}
	}

	BSTSum handleFullNode(TreeNode root, BSTSum left, BSTSum right) {
		System.out.println(" yo " + " rr " + root.val + " " + left.max + " " + right.min);
		if (root.val > left.max && root.val < right.min && left.isBST && right.isBST) {
			// valid bst
			int sum = root.val + left.sum + right.sum;
			globalMax = Math.max(globalMax, sum);
			return new BSTSum(root, true, left.min, right.max, sum);
		}
		int sum = Math.max(left.sum, right.sum);
		globalMax = Math.max(globalMax, sum);
		return new BSTSum(root, false, left.min, right.max, sum);
	}

	BSTSum handleLeftNode(TreeNode root, BSTSum left) {
		if (root.val > left.max && left.isBST) {
			// valid bst
			int sum = root.val + left.sum;
			globalMax = Math.max(globalMax, sum);
			return new BSTSum(root, true, left.max, root.val, sum);
		}
		globalMax = Math.max(globalMax, left.sum);
		return new BSTSum(root, false, left.min, root.val, left.sum);
	}

	BSTSum handleRightNode(TreeNode root, BSTSum right) {
		if (root.val < right.min && right.isBST) {
			// valid bst
			int sum = root.val + right.sum;
			globalMax = Math.max(globalMax, sum);
			return new BSTSum(root, true, root.val, right.min, sum);
		}
		globalMax = Math.max(globalMax, right.sum);
		return new BSTSum(root, false, root.val, right.min, right.sum);
	}

}

class BSTSum {
	boolean isBST;
	int max, min, sum;
	TreeNode node;

	BSTSum(TreeNode node) {
		this.node = node;
		isBST = true;
		this.max = node.val;
		this.min = node.val;
		this.sum = node.val;
	}

	BSTSum(TreeNode node, boolean isBST, int min, int max, int sum) {
		this.node = node;
		isBST = this.isBST;
		this.min = min;
		this.max = max;
		this.sum = sum;
	}
}
