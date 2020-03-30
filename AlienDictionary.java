// { Driver Code Starts
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.*;

class GFG {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.next());
		while(t-- > 0)
		{
		    int n = Integer.parseInt(sc.next());
		    int k = Integer.parseInt(sc.next());
		    
		    String[] words = new String[n];
		    
		    for(int i=0;i<n;i++)
		    {
		        words[i] = sc.next();
		    }
		    
		    Solution T = new Solution();
		  //  System.out.println(T.printOrder(words,k));
		    String order = T.printOrder(words,k);
		    
		    String temp[] = new String[n];
		    for(int i=0;i<n;i++)
		        temp[i] = words[i];
		    
		    Arrays.sort(temp, new Comparator<String>(){
		    
		      @Override
                public int compare(String a, String b) {
                    int index1 = 0;
                    int index2 = 0;
                    for(int i = 0; i < Math.min(a.length(), b.length()) 
                                        && index1 == index2; i++) {
                        index1 = order.indexOf(a.charAt(i));
                        index2 = order.indexOf(b.charAt(i));
                    }
                
                    if(index1 == index2 && a.length() != b.length()) 
                    {
                        if(a.length() < b.length())
                            return 1;
                        else
                            return 0;
                    }
                
                    if(index1 < index2)
                        return 1;
                    else
                        return 0;
                        
                }
		    });
		    
		    int flag = 1;
		    for(int i=0;i<n;i++)
		    {
		        if(!words[i].equals(temp[i]))
	            {
	                flag = 0;
	                break;
	            }
		    }
		    
		    System.out.println(flag);
		}
	}
	
}
// } Driver Code Ends
//User function Template for Java

class Solution
{
    public String printOrder(String [] words,int k)
    {
        // Write your code here
        Graph graph = new Graph(k);
        
        for(int i =0;i<words.length-1;i++){
            String word1 = words[i]; 
            String word2 = words[i+1]; 
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) 
            { 
                if (word1.charAt(j) != word2.charAt(j)) { 
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j)- 'a'); 
                    break; 
                } 
            }         
        }
        return graph.printSorted();
        
    }
}

class Graph {
    private final LinkedList<Integer>[] adjacencyList; 
  
    Graph(int nVertices) { 
        adjacencyList = new LinkedList[nVertices]; 
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
        { 
            adjacencyList[vertexIndex] = new LinkedList<>(); 
        } 
    }
    void addEdge(int start, int end) 
    { 
        adjacencyList[start].add(end); 
    } 
  
    private int getNoOfVertices() 
    { 
        return adjacencyList.length; 
    } 
    void topologicalSort(int curr,Stack<Integer> stack, boolean[] visited){
        visited[curr]=true;
        for(Integer no:adjacencyList[curr]){
            if(!visited[no]){
                topologicalSort(no,stack,visited);
            }
        }   
        stack.push(curr);
    }
    String printSorted(){
        boolean[] visited = new boolean[adjacencyList.length];
        for (int i = 0; i < getNoOfVertices(); i++) 
        { 
            visited[i] = false; 
        } 
        Stack<Integer> stack = new Stack<>();
        for(int i =0;i<adjacencyList.length;i++){
            if(!visited[i]){
                topologicalSort(i,stack,visited);
            }
        }
        //System.out.println();
        String str="";
        while(!stack.isEmpty()){
            str+= (char)('a' + stack.pop()) + " ";
        }
        //System.out.println(str);
        return str;
    }
  
}
