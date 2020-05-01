package com.manager.parking;

public class RodCuttingProblem {
	public static void main(String args[]) {
		RodCuttingProblem cr = new RodCuttingProblem();
		int[] price = { 2, 5, 7, 8 };
		int r = cr.maxValue(price, 5);
		System.out.println(r);
	}

	public int maxValue(int price[], int n) {
		int max[] = new int[n + 1];

		for (int i = 1; i < price.length; i++) {
			for (int j = i; j < max.length; j++) {
				max[j] = Math.max(max[j], max[j - i] + price[i - 1]);
			}
		}
		return max[n];
	}
}

