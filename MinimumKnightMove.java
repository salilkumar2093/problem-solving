package com.manager.parking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMove {
	public static void main(String... args) {
		MinimumKnightMove obj = new MinimumKnightMove();
		//System.out.println(obj.minKnightMoves(0, -300));
		System.out.println(obj.minCut("banana"));
	}

	int[] row = { 1, 2, -1, -2, 1, 2, -1, -2 };
	int[] col = { 2, 1, 2, 1, -2, -1, -2, -1 };

	int minKnightMoves(int x, int y) {

		Queue<Node> queue = new LinkedList<>();
		Node root = new Node(0, 0, 0);
		queue.add(root);
		Set<int[]> visited = new HashSet<>();
		Set<String> visitedStr = new HashSet<>();
		visitedStr.add(0 + "$" + 0);
		visited.add(new int[] { 0, 0 });

		while (!queue.isEmpty()) {
			Node pop = queue.poll();
			if (pop.x == Math.abs(x) && pop.y == Math.abs(y))
				return pop.move;
			for (int i = 0; i < row.length; i++) {
				int x1 = pop.x + row[i];
				int y1 = pop.y + col[i];

				if (!visitedStr.contains(x1 + "$" + y1) && x1 > -1 && y1 > -1) {
					// visited.add(new int[] { x1, y1 });
					visitedStr.add(x1 + "$" + y1);
					queue.add(new Node(x1, y1, pop.move + 1));
				}

			}
		}
		return -1;
	}

	public int minCut(String s) {
		int n = s.length(), i = 0;
		int cut[] = new int[n];
		Arrays.fill(cut, Integer.MAX_VALUE);
		cut[0] = -1;
		while (i < n) {
			lps(s, i, i, cut);
			lps(s, i, i + 1, cut);
			i++;
		}
		return cut[n-1];
	}

	void lps(String s, int left, int right, int[] cut) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			cut[right] = Math.min(cut[right], cut[left] + 1);
			left--;
			right++;
		}
	}
}

class Node {
	int x;
	int y;
	int move;

	public Node(int x, int y, int move) {
		super();
		this.x = x;
		this.y = y;
		this.move = move;
	}

}
