/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0 ; i < T ; i++){
		    int n = sc.nextInt();
		    int wajan = sc.nextInt();
		    int[] wajans = new int[n];
		    int[] values = new int[n];
		    Integer[][] dp=new Integer[n+1][wajan+1];
    		for(int j = 0; j<n ;j++)
    		{
    			values[j] = sc.nextInt();
    		}
    		for(int j = 0; j<n ;j++)
    		{
    			wajans[j] = sc.nextInt();
    		}
    		System.out.println(recur(n,wajan,values,wajans,dp));
		   
		}		
	    
	}
	private static int recur(int n, int weight, int[] values, int[] wajans, Integer[][] dp){
	    if(dp[n][weight]!=null) {
	         //System.out.println("yayy");
	         return dp[n][weight];
	    }
	    int result  = 0;
	    
	    if(weight<0 || n<=0)
	        result =  0;
	    else if(wajans[n-1]>weight) {
	        result= recur(n-1,weight,values,wajans,dp);
	    }
	    else{
    	    int inc = values[n-1]+recur(n-1,weight-wajans[n-1],values,wajans,dp);
    	    int notInc = recur(n-1,weight,values,wajans,dp);
    	    result =Math.max(inc,notInc);
	    }
	    dp[n][weight] = result;
	    return result;
	}
}
