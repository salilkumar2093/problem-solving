package com.manager.parking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MinMaxHeap {
	LinkedList<Integer> list = new LinkedList<>();

	public static void main(String[] args) {
		int[] input = { 3, 4, 0, 10, 5, 1, 2 };
		int[] input2 = { 5, 3, 17, 10, 84, 19, 6, 22, 9 };

		MinMaxHeap obj = new MinMaxHeap();
		obj.constructHeap(input2);
		System.out.println(obj.poll());
		System.out.println(obj.poll());
		System.out.println(obj.poll());

	}

	void constructHeap(int[] input) {
		for (int i = 0; i < input.length; i++) {
			list.add(input[i]);
			heapifyUp(i);
		}
		System.out.println(list);
	}

	int poll() {
		int element = list.remove(0);
		list.addFirst(list.removeLast());
		heapifyDown(0);
		System.out.println("POLL Call " + list);
		return element;
	}

	void heapifyUp(int index) {
		while (index >= 0 && list.get(index) < list.get(getParent(index))) {
			int parentIndex = getParent(index);
			swap(index, parentIndex);
			index = parentIndex;
		}
	}

	void heapifyDown(int index) {
		if (index >= list.size() - 1)
			return;

		int element = list.get(index);
		int leftChild = getLeftChild(index);
		int rightChild = getRightChild(index);

		if (leftChild < list.size() && element > list.get(leftChild)) {
			swap(index, leftChild);
			heapifyDown(leftChild);

		} else if (rightChild < list.size() && element > list.get(rightChild)) {
			swap(index, rightChild);
			heapifyDown(rightChild);
		}
	}

	int getParent(int x) {
		return x / 2;
	}

	int getLeftChild(int x) {
		return 2 * x + 1;
	}

	int getRightChild(int x) {
		return 2 * x + 2;
	}

	void swap(int index1, int index2) {
		int temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

}

/*



*/
