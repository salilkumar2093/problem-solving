import java.util.*;
import java.lang.*;
import java.io.*;

class StockSpan {
	public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);

		int t=sc.nextInt();

		while(t>0) {

		    int n=sc.nextInt();

		    int[] a=new int[n];
		    for(int i=0; i<n; i++) {
		        a[i]=sc.nextInt();
		    }

            solve(a, n);

		    t--;
		}	
	    
	}
	private static void solve(int[] arr, int n) {
	  int res[] = new int[n];
      Stack<Integer> stack = new Stack<>();
      res[0] = 1;
      stack.push(0);
      for(int i=1;i<n;i++){
          res[i] = 1;
          while(!stack.isEmpty() && arr[stack.peek()]<=arr[i]){
              res[i]+=res[stack.pop()];
          }
          stack.push(i);
      }
      for(int i =0;i<n;i++){
          System.out.print(res[i] + " ");

      }
      System.out.println();
	}


}
