class GfG{
	public static void kthSmallest(int[][] mat,int n,int k){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Compare()); 
    
        for(int i =0; i<n;i++){
            pq.add(new Node(0,i,mat[0][i]));
        }
        int val=0;
        for(int i =0;i<k;i++){
            Node pop = pq.poll();
            val = pop.value;
            if(pop!=null && ((pop.i)+1)<n){
                pq.add(new Node(pop.i+1,pop.j,mat[pop.i+1][pop.j]));
            }
        }
        System.out.println(val);    
	    
	}
}

class Compare implements Comparator<Node>{
    public int compare(Node a, Node b){
        return a.value-b.value;
    }
}

class Node {
    int i;
    int j;
    int value;
    Node(int i, int j, int value){
        this.i=i;
        this.j=j;
        this.value=value;
    }
}

