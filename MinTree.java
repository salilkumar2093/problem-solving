package com.manager.parking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;

public class MinTree {
	public static void main(String... args) {
		System.out.println(findMinHeightTrees(4, new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } }));
	}

	public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
		Set<Integer>[] list1 = new Set[n];
		int[] degree = new int[n];
		for (int i = 0; i < n; i++) {
			list1[i] = new HashSet<>();
		}
		for (int i = 0; i < edges.length; i++) {
			list1[edges[i][0]].add(edges[i][1]);
			list1[edges[i][1]].add(edges[i][0]);
		}
		List<Integer> leaves = new ArrayList<>();
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			result.add(i);
			if (list1[i].size() == 1)
				leaves.add(i);
		}

		while (leaves.size() > 2) {
			// identify and remove all leaf nodes
			List<Integer> newLeaves = new ArrayList<>();
			for (int val : leaves) {
				int neighbor = list1[val].iterator().next();
				if (neighbor < list1.length && !list1[neighbor].isEmpty()) {
					list1[neighbor].remove(val);

					if (list1[neighbor].size() == 1) {
						newLeaves.add(neighbor);
					}
				}
			}
			leaves = newLeaves;
		}
		return new ArrayList<>(leaves);
	}

}
