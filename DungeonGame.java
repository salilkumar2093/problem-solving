class Solution {
    public int calculateMinimumHP(int[][] dung) {
		int m = dung.length;
		int n = dung[0].length;
		Integer[][] dp = new Integer[m][n];
		dp[m - 1][n - 1] = (dung[m - 1][n - 1] > 0) ? 1 : Math.abs(dung[m - 1][n - 1]) + 1;
		return solve(dung, dp, 0, 0);
	}

	boolean isValid(int[][] dung, int i, int j) {
		int m = dung.length;
		int n = dung[0].length;
		if (i >= m || j >= n)
			return false;
		return true;
	}

	int solve(int[][] dung, Integer[][] dp, int i, int j) {
		int m = dung.length;
		int n = dung[0].length;
		if (i == m - 1 && j == n - 1)
			return dp[i][j];
		if (dp[i][j] != null)
			return dp[i][j];

		Integer first = (isValid(dung, i + 1, j)) ? solve(dung, dp, i + 1, j) : Integer.MAX_VALUE;
		Integer second = (isValid(dung, i, j + 1)) ? solve(dung, dp, i, j + 1) : Integer.MAX_VALUE;
        int res = Math.min(first,second);
        return dp[i][j] = Math.max(res-dung[i][j],1);
	}
}
