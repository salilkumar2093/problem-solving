/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);

		int t=sc.nextInt();

		while(t>0) {

		    int n=sc.nextInt();

		    int[][] a=new int[n][n];
		    for(int i=0; i<n; i++) {

		        for(int j=0; j<n; j++)
		            a[i][j]=sc.nextInt();
		    }

            solve(a, n);

		    t--;
		}	
	    
	}
	private static void solve(int[][] arr, int n) {
	    int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};

        int[][] result=new int[n][n];
        result[0][0] = arr[0][0];
        for(int i=0; i<n; i++)
            Arrays.fill(result[i], Integer.MAX_VALUE);
        result[0][0]=arr[0][0];
        boolean[][] vis=new boolean[n][n];
        PriorityQueue<Node> pq = new PriorityQueue<>(10, new Comp());
        pq.add(new Node(0,0,arr[0][0]));
        vis[0][0] = true;
        while(!pq.isEmpty()){
            Node curNode=pq.poll();

            if((curNode.x==(n-1)) && (curNode.y==(n-1)))
                break;
            for(int i =0;i<dx.length; i++){
                int newX = curNode.x + dx[i];
                int newY = curNode.y + dy[i];
                if(!isValid(newX,newY, n))
                    continue;
                if(vis[newX][newY])
                    continue;
                if(result[newX][newY]> result[curNode.x][curNode.y]+arr[newX][newY])
                   result[newX][newY] = result[curNode.x][curNode.y]+arr[newX][newY];
                pq.add(new Node(newX, newY, result[newX][newY]));
                vis[newX][newY] = true;
            }
        }
        System.out.println(result[n-1][n-1]);
        
        
	}
	static boolean isValid(int x, int y, int n) {

        return ((x>=0) && (y>=0) && (x<n) && (y<n));
    }

}
class Comp implements Comparator<Node> {

    public int compare(Node p1, Node p2) {

        return (p1.currSum-p2.currSum);
    }
}
class Node {
    int x; 
    int y;
    //int level;
    int currSum;
    Node(int x, int y, int currSum) {
        this.x=x;
        this.y=y;
       // this.level = level;
        this.currSum = currSum;
    }
}
