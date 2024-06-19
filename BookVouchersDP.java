import java.io.*;
import java.util.*;
/* Name of the class has to be "Main" only if the class is public. */
class HelloWorld
{

    
public static void main (String[] args)
   {
       int[] costs = {12,20,7,10};
       int[] books = {10,15,5,20};
       System.out.println("Hello World!" + getRequiredEBooksCost(costs,books,50));
   }//30
   
    static int minimumCost=Integer.MAX_VALUE;
    static int count=0;
    
    static int getRequiredEBooksCost(int[] cost, int[] eBooks, int totalEBook){
        minimumCost= Integer.MAX_VALUE;
        count=0;
        //add edge cases later
       Map<String,Integer> memo = new HashMap<>();

        for(int index=0;index<cost.length;index++){
           int costss = getRequiredEBooksWithMinimumVouchers(cost,eBooks,totalEBook, cost[index],eBooks[index],memo);
        }
        System.out.println(count);
        
        return minimumCost;
    }
   
   
    static int getRequiredEBooksWithMinimumVouchers(int[] cost, int[] eBooks, int totalEBook, int totalCost, int currentEbooks, Map<String,Integer> memo){
        count++;
       //System.out.println("minimum cost"+totalCost);
        if(totalCost>minimumCost)
            return totalCost;
       
        if(currentEbooks>=totalEBook){ //   15
            minimumCost = Math.min(minimumCost,totalCost);
            return totalCost;
        }
        String key = currentEbooks+ "."+totalCost;
        if(memo.containsKey(key) && memo.get(key) <= totalCost)
            return memo.get(key);
            
        
        memo.put(key, totalCost);
        int currentCost = Integer.MAX_VALUE;
        for(int index=0;index<cost.length;index++){
            getRequiredEBooksWithMinimumVouchers(cost,eBooks,totalEBook, totalCost+cost[index],currentEbooks+eBooks[index],memo);
        }
        //memo.put(key,currentCost);
        return memo.get(key);
    }
}
