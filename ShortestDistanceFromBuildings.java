public class Solution {
    public int solve(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] reach = new int[m][n];
        int buildings = 0;
        
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j] == 1){
                    bfs(A,result,reach,i,j);
                    buildings++;
                }
            }
        }
        int uttar = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 0 && reach[i][j] == buildings) {
                    uttar = Math.min(uttar, result[i][j]);
                }
            }
        }
         return uttar == Integer.MAX_VALUE ? -1 : uttar;
        
    }
    void bfs(int[][] A, int[][] result, int[][] reach, int i, int j){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i,j,0));
        int[] first = {0,0,1,-1};
        int[] second = {1,-1,0,0};
        
        int m = A.length;
        int n = A[0].length;
        while(!queue.isEmpty()){
            Node pop = queue.poll();
            
            for(int it=0;it<first.length;it++){
                int x=pop.i+first[it];
                int y = pop.j+second[it];
                if(x>=0 && y>=0 && x<m && y<n && A[x][y] ==0){
                    A[x][y]=-1;
                    queue.add(new Node(x,y,pop.val+1));
                    result[x][y]+=pop.val+1;
                    reach[x][y]++;
                }
            }
        }
        for (int x = 0; x < A.length; x++) {
        for (int y = 0; y < A[0].length; y++) {
            if (A[x][y] == -1) {
                A[x][y] = 0;
            }
        }
    }
    }
}

class Node {
    int i;
    int j;
    int val;
    Node(int i, int j, int val){
        this.i=i;
        this.j=j;
        this.val=val;
    }
}
