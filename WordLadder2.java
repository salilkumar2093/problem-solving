package com.manager.parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {
	public static void main(String... args) {
		String[] arr = { "a", "b", "c" };
		int[] arr2 = { 84, -37, 32, 40, 95 };

		// System.out.println(findLadders("a", "c", Arrays.asList(arr)));
		System.out.println(shortestSubarray(arr2, 200));

	}

	//
	// sum [] = [ 0, 84, 43,
	public static int shortestSubarray(int[] A, int K) {
		int length = A.length + 1;
		int[] sum = new int[A.length + 1];
		for (int i = 1; i < sum.length; i++) {
			sum[i] += sum[i - 1] + A[i - 1];
		}
		Deque<Integer> deque = new LinkedList<Integer>();
		deque.addLast(0);
		for (int i = 1; i < sum.length; i++) {
			while (!deque.isEmpty() && (sum[i] - sum[deque.peekFirst()]) >= K) {
				length = Math.min(length, i - deque.pollFirst());
			}
			while (!deque.isEmpty() && sum[deque.peekLast()] >= sum[i]) {
				deque.pollLast();
			}
			
			deque.addLast(i);
		}
		return length <= A.length ? length : -1;
	}

	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		// base checks
		Set<String> wordSet = new HashSet<>(wordList);
		Set<String> usedSet = new HashSet<>();

		List<List<String>> result = new ArrayList<>();
		Map<String, LadderNode> wordMap = new HashMap<>();
		if (!wordSet.contains(endWord))
			return result;
		LadderNode root = new LadderNode(1, beginWord);
		Queue<LadderNode> queue = new LinkedList<>();
		queue.add(root);
		wordMap.put(beginWord, root);
		while (!queue.isEmpty()) {
			LadderNode node = queue.poll();
			String word = node.word;
			if (word.equals(endWord)) {
				break;
			}
			for (int i = 0; i < word.length(); i++) {
				for (char ch = 'a'; ch <= 'z'; ch++) {
					char[] chars = word.toCharArray();
					if (ch == chars[i])
						continue;
					chars[i] = ch;
					String childStr = new String(chars);
					if (wordSet.contains(childStr) && (!wordMap.containsKey(childStr)
							|| wordMap.containsKey(childStr) && wordMap.get(childStr).level > node.level)) {
						LadderNode child = null;
						if (wordMap.containsKey(childStr)) {
							child = wordMap.get(childStr);
						} else {
							child = new LadderNode(node.level + 1, childStr);
							wordMap.put(childStr, child);
						}
						node.nextWords.add(child);
						queue.add(child);
					}
				}
			}
		}
		List<String> list = new ArrayList<>();
		list.add(root.word);
		addResult(root, result, list, endWord);
		return result;
	}

	static void addResult(LadderNode root, List<List<String>> result, List<String> temp, String endWord) {
		if (root == null)
			return;
		if (root.nextWords.isEmpty() && root.word.equals(endWord)) {
			result.add(new ArrayList<>(temp));
			return;
		}
		for (LadderNode node : root.nextWords) {
			temp.add(node.word);
			addResult(node, result, temp, endWord);
			temp.remove(temp.size() - 1);
		}
	}
}

class LadderNode {
	int level;
	String word;
	List<LadderNode> nextWords;

	LadderNode(int level, String word) {
		this.level = level;
		this.word = word;
		nextWords = new ArrayList<>();
	}
}
