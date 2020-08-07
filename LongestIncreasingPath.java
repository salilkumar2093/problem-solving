class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
       
        Integer[][] result = new Integer[matrix.length][matrix[0].length];
 
        for(int i =0; i<m;i++){
            for(int j=0;j<n;j++){
                result[i][j] = compute(matrix, result, i , j);
            }
        }
        int max = result[0][0];
        for(int i =0; i<m;i++){
            for(int j=0;j<n;j++){
                max = Math.max(max, result[i][j]);
            }
        }
        return max;
        
    }
    int compute(int[][] matrix, Integer[][] result, int i, int j) {
        if(result[i][j] != null){
            return result[i][j];
        }
        int currValue = matrix[i][j];
        int left = isValid(currValue,i,j-1,matrix) ? compute(matrix, result, i , j-1):0;
        int right = isValid(currValue,i,j+1,matrix)? compute(matrix, result, i , j+1):0;
        int up = isValid(currValue,i-1,j,matrix)? compute(matrix, result, i-1 ,j):0;
        int down = isValid(currValue,i+1,j,matrix)? compute(matrix, result, i+1 , j):0;
        
        return result[i][j] = 1+Math.max(left,Math.max(right,Math.max(up, down)));
            
    }
    boolean isValid(int currValue, int i, int j, int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        if(i>=m || j>=n || i<0 || j<0 || currValue >= matrix[i][j])
            return false;
        return true;
    }
}
