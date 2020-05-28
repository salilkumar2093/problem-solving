public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] prerequisites) {
        boolean[] visited = new boolean[n];
        boolean[] rec=new boolean[n];
        LinkedList<Integer>[] list = new LinkedList[n];

        insertIntoList(prerequisites, n, list);
        
        if(cycleDetection(visited,list,0,-1))
            return false;
        for(boolean b: visited){
            if(!b)
                return false;
        }
        return true;
    }
    void insertIntoList(int[][] prerequisites, int n,LinkedList<Integer>[] list){
        for(int i=0;i<n;i++)
            list[i]=new LinkedList<>();
        for(int i=0;i<prerequisites.length;i++){
            list[prerequisites[i][0]].add(prerequisites[i][1]);
            list[prerequisites[i][1]].add(prerequisites[i][0]);
        }
    }
 
   boolean cycleDetection(boolean[] visited, LinkedList<Integer>[] list, int v, int parent){
        visited[v]=true;
        for(int i : list[v]){
            if(!visited[i]) {
                if(cycleDetection(visited,list,i, v))
                    return true;
            }
            //vertex ka bachha 
            else if(i!=parent)
                return true;
        }
        return false;
    }
    
}
