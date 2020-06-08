/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
	    Scanner s=new Scanner(System.in);
		int t=s.nextInt();
		while(t>0){
		    int n=s.nextInt();
		    int[]arr=new int[n];
		    int[]dept=new int[n];
		    for(int i=0;i<n;i++)
		        arr[i]=s.nextInt();
		    for(int i=0;i<n;i++)
		        dept[i]=s.nextInt();
		    
		    System.out.println(findMinPlatforms(arr,dept,n));
		    t--;
		}
	}
		static int findMinPlatforms(int[] arrival, int[] departure, int size){
		    int platformsRequred = 1, max=1;
		    Arrays.sort(arrival);
		    Arrays.sort(departure);
		    int i=1,j=0;
		    while(i<size && j<size){
		        if(arrival[i]<=departure[j]){
		            platformsRequred++;
		            max = Math.max(max,platformsRequred);
		            i++;
		        }else{
		            platformsRequred--;
		            j++;
		        }
		    }
		    
		    return max;
		}
    static int findMinPlatformsInOrderN(int[] arrival, int[] departure, int size){
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < size; i++) { 
            map.put(arrival[i],'a');
            map.put(departure[i],'d');
        } 
      
        int result = 0; 
        int plat_needed = 0; 
        for (int key=0;key<2360;key++) { 
            if(map.containsKey(key))
                if (map.get(key) == 'a') 
                    plat_needed++; 
                else
                    plat_needed--; 
      
            if (plat_needed > result) 
                result = plat_needed; 
        } 
        return result; 
	}
		}
}
