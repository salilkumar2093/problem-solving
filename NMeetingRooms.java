
import java.util.*;
import java.lang.*;
import java.io.*;
class Interval{
    int start;
    int dest;
    Interval(int start,int dest){
        this.start=start;
        this.dest=dest;
    }
}
class MyComp implements Comparator<Interval>{
    
   @Override
    public int compare(Interval i1,Interval i2){
        return i1.dest-i2.dest;
    }
}
class GFG {
	public static void main (String[] args) {

		Scanner in = new Scanner(System.in);
	    int T = in.nextInt();
		for(int i = 0 ; i < T ; i++){
		    int n = in.nextInt();
		    int[] start=new int[n];
	        int[] dest = new int[n];
	        List<Interval> list=new ArrayList<>();
	        
    		for(int j = 0; j<n ;j++)
    		{
    		    start[j]=in.nextInt();
    		}
    	    for(int j = 0; j<n ;j++)
    		{
    		    dest[j]=in.nextInt();
    		}
    		for(int j = 0; j<n ;j++)
    		{
    		    list.add(new Interval(start[j],dest[j]));
    		}
    		
    		calculate(list);
    		
    		System.out.println();
		}
	}
	static void calculate(List<Interval> list){
	    Map<Integer,Integer> map=new HashMap<>();
	    for(int i=0;i<list.size();i++){
	        map.put(list.get(i).dest,i);
	    }
	    
	    Collections.sort(list,new MyComp());
	    Interval val=list.get(0);
	    System.out.print((map.get(val.dest)+1)+" ");
	    for(int i=1;i<list.size();i++){
	        if(val.dest<=list.get(i).start){
	            val=list.get(i);
	            System.out.print((map.get(val.dest)+1)+" ");
	        }
	    }
	}
}
