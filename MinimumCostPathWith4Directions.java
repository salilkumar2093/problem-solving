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
        boolean[][] visited=new boolean[n][n];
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comp());
        queue.add(new Node(0,0,arr[0][0]));
        
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            if((curNode.x==(n-1)) && (curNode.y==(n-1))){
                System.out.println(curNode.sum);
                return;
            }
            for(int i=0;i<dx.length;i++){
                int x = curNode.x+dx[i];
                int y = curNode.y+dy[i];
                
                if(isValid(x,y,n,visited)){
                    int sum = curNode.sum+arr[x][y];
                    queue.add(new Node(x,y,sum));
                    visited[x][y]= true;
                }
            }
        }
    }
    static boolean isValid(int x, int y, int n, boolean[][] visited) {
        if(x<0 || y<0 || x==n || y==n || visited[x][y])
            return false;
        return true;
    }
    
}

class Comp implements Comparator<Node> {

    public int compare(Node p1, Node p2) {

        return (p1.sum-p2.sum);
    }
}
class Node {
    int x; 
    int y;
    int sum;
    Node(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}
