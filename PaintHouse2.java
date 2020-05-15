public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // write your code here
          if (costs == null || costs.length == 0) {
            return 0;
        }
         
        int n = costs.length;
        int k = costs[0].length;
        for (int i = 1; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int val = costs[i-1][0];
            int first = Integer.MAX_VALUE,second = Integer.MAX_VALUE; 
            for(int j=0;j<k;j++){
                if(costs[i-1][j]<=first){
                    second = first; 
                    first =costs[i-1][j];
                } 
                else if (costs[i-1][j] < second) 
                    second = costs[i-1][j];
            }
            for (int j = 0; j < k; j++) {
                    costs[i][j]+= first!=costs[i-1][j]?first:second;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++) {
           min=Math.min(min, costs[n-1][i]);
        }
        return min;
    }
}
