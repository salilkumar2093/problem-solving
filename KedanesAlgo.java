public class KedanesAlgo {
	public static void main(String... args) {
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int a2[] = { 1, 2, 3, -2, 5 };
		int a3[] = { -1, -2, -3, -2, 5 };
		int a4[] = { -1, -2, -3, -2 };

		System.out.println(compute(a4));

	}

	static int compute(int[] arr) {
		int maxSum = arr[0];
		int curr = arr[0];
		int s = 0, start = 0;
		int end = 0;
		for (int i = 1; i < arr.length; i++) {
			curr += arr[i];
			if (curr < arr[i]) {
				curr = arr[i];
				s = i;
			}
			if (maxSum < curr) {
				maxSum = curr;
				start = s;
				end = i;
			}
		}
		System.out.println(start + " indexes " + end);
		return maxSum;
	}
}
