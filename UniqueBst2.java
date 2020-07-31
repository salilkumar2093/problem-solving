package com.manager.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBST2 {
	public static void main(String... args) {
		UniqueBST2 obj = new UniqueBST2();
		System.out.println(obj.generateTrees(2));
	}

	public List<TreeNode> generateTrees(int n) {
		List<TreeNode> result = new ArrayList<>();
		if (n == 0)
			return result;
		return generate(1, n, new HashMap<>());
	}

	List<TreeNode> generate(int start, int end, Map<String, List<TreeNode>> cache) {
		System.out.println("start " + start + " " + end);

		List<TreeNode> list = new ArrayList<>();
		if (start > end) {
			list.add(null);
		}
		if (start == end) {
			list.add(new TreeNode(start));
		} else {
			for (int i = start; i <= end; i++) {
				List<TreeNode> left = generate(start, i - 1, cache);
				List<TreeNode> right = generate(i + 1, end, cache);
				for (TreeNode l : left) {
					for (TreeNode r : right) {
						TreeNode root = new TreeNode(i);
						root.left = l;
						root.right = r;
						list.add(root);
					}
				}
			}
		}
		return list;
	}
}
