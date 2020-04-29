/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
public class GFG {
	public static void main (String[] args) {
		System.out.println("GfG!");
	    int arr[] = {1 , 2 , 3 , 4 , 5 , 5 }; 
	    int arr2[] = {1, 3, 2, 1, 2, 1};
        printTwoParts(arr2, arr2.length); 
		
	}
	static void  printTwoParts2(int[] arr,int n) {
	    // rolla rappa
	    
	}

	static void  printTwoParts(int[] arr,int n) {
	    int sum = 0;
	    for(int i=0;i<n;i++){
	        sum+=arr[i];
	    }
	    int leftSum = 0; 
      
        for (int i = 0 ; i < n ; i++) 
            leftSum += arr[i]; 
        int rightSum=0;
        int splitPoint=-1;
        for(int i=n-1;i>=0;i--){
            rightSum+=arr[i];
            leftSum-=arr[i];
            if(leftSum==rightSum){
                break;
            }
        }
        //condition on splitPoint
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
