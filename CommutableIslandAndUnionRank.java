class GraphEdge{
    int e1;
    int e2;
    int weight;
    GraphEdge(int e1,int e2,int weight){
        this.e1=e1;
        this.e2=e2;
        this.weight=weight;
    }
}
class MyComp implements Comparator<GraphEdge>{
    public int compare(GraphEdge g1,GraphEdge g2){
        return g1.weight-g2.weight;
    }
}

public class Solution {
    public int solve(int A, int[][] B) {
        if(B.length==0 || B[0].length==0)
            return 0;
        List<GraphEdge> list=new ArrayList<>();
        
        for(int i=0;i<B.length;i++){
            list.add(new GraphEdge(B[i][0],B[i][1],B[i][2]));
        }
        Collections.sort(list,new MyComp());
        return traverse(A,list);        
    }
    
    int traverse (int V,List<GraphEdge> list){
        int totalWeight=0;
        int ed=0;
        Graph g=new Graph(V+1);
        for(GraphEdge edge:list){
            if(ed==V-1)
                return totalWeight;
            int root1=g.findRoot(edge.e1);
            int root2=g.findRoot(edge.e2);
            if(root1!=root2){
                ed++;
                totalWeight+=edge.weight;
                g.union(edge.e1,edge.e2);
            }
        }
         return totalWeight;
    }
}
class Graph 
{ 
    private int V;   // No. of vertices 
    int[] papa=null;
    int[] rank = null;
    
    // Constructor 
    Graph(int v) { 
        papa=new int[v];
        rank=new int[v];

        for(int i=0; i<v; ++i) {
            papa[i]=i;
            rank[i]=i;
        }
    } 
    void union(int x,int y){
        if(x>y){
            union(y,x);
            return;
        }
        
        int root1=findRoot(x);
        int root2=findRoot(y);
        if(root1==root2)
            return;
        if(rank[root1]>rank[root2]){
            papa[root2]=root2;
            rank[root1]+=rank[root2];
        }else{
            papa[root2]=root2;
            rank[root2]+=rank[root1];
        }
    }
    int findRoot(int bachha){
        if(papa[bachha]!=bachha){
            return findRoot(papa[bachha]);
        }
        
        return papa[bachha];
    }
}
        
