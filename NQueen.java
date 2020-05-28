/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;


class GFG {
	public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 0 ; t < T ; t++) {
		    int N = sc.nextInt();
		  		List<List<Integer>> result = new ArrayList<>();
		    for (int i = 0; i < N; i++) {
    			int[][] arr = new int[N][N];
    			arr[i][0] = 1;
    			List<Integer> list = new ArrayList<>();
    			list.add(i + 1);
    			check(arr, list, 1, result);
		    }
		    for(List<Integer> res:result)
    		    System.out.print(res + " ");
    		System.out.println();
	    }
	}
	static void check(int[][] arr, List<Integer> list, int col, List<List<Integer>> result) {
	    if(list.size()==arr.length){
	        result.add(new ArrayList<>(list));
	        return;
	    }
	    for(int i=0;i<arr.length;i++){
	        if(isValid(arr,i, col)) {
	            arr[i][col] = 1;
	            list.add(i+1);
	            check(arr,list,col+1,result);
	            list.remove(list.size()-1);
	            arr[i][col] = 0;
	        }
	    }
	}
	static boolean isValid(int[][] arr, int row, int col){
	    int n = arr.length;
	    int p = row-1;
	    int q = col-1;
	    
	    //diagnol check both up back
	    while(p>=0 && q>=0){
	        if(arr[p][q]==1)
	            return false;
	        p--;
	        q--;
	    }
	    //diagnol check both up and down
	    p = row+1;
	    q = col-1;
	    while(p<n && q>=0){
	        if(arr[p][q]==1)
	            return false;
	        p++;
	        q--;
	    }
	    
	    //col check
	    p = row;
		q = col-1;
	    while(q>=0){
	        if(arr[p][q]==1)
	            return false;
	        q--;
	    }
	    return true;
	}
}
