/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
public class GFG {
	public static void main (String[] args) {
		System.out.println("GfG!");
		int[] dimension1={0,-1,1,0};
		int[] dimension2={-1,0,0,1};
		
		char[][] matrix =  { 
            {'O', 'O', 'O', 'O', 'G'}, 
            {'O', 'W', 'W', 'O', 'O'}, 
            {'O', 'O', 'O', 'W', 'O'}, 
            {'G', 'W', 'W', 'W', 'O'}, 
            {'O', 'O', 'O', 'O', 'G'} 
        }; 
        int m = 5, n =5;
        int[][] output =new int[m][n];
        System.out.println(matrix[1][0]);
        Queue<Node> queue = new LinkedList<Node>();
        //boolean[][] visited = new boolean[m][n];
        
        for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                output[i][j] = -1;
                if(matrix[i][j]=='G'){
                    //visited[i][j]=true;
                    output[i][j] = 0;
                    queue.add(new Node(i,j,0));
                }   
            }
        }
        
        // BFS Starts
        while(!queue.isEmpty()){
            Node pop = queue.poll();
            //visited[pop.i][pop.j]=true;
            
            for(int i =0;i<dimension1.length;i++){
                int x=pop.i+dimension1[i];
                int y=pop.j+dimension2[i];
                if(isSafe(x,y,m,n,matrix,output)){
                    queue.add(new Node(x,y,pop.val+1));
                    output[x][y]=pop.val+1;
                }
            }
        }
         for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                System.out.print(output[i][j]+ " ");
            }
            System.out.println();
          }
        
	}
	static boolean isSafe(int x, int y, int m, int n, char[][] matrix, int[][] output){
	    if(x<0 || y<0 || x>=m || y>=n)
	        return false;
	    if(matrix[x][y] !='O' || output[x][y]!=-1)
	        return false;
	   return true;
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
