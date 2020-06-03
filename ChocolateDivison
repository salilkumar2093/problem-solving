public class Solution {
    /**
     * @param sweetness: an integer array
     * @param K: an integer
     * @return:  return the maximum total sweetness of the piece
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        long low = Integer.MAX_VALUE;
        long high = 0;
        
        for(int value : sweetness){
            low = Math.min(low, value);
            high+= value;
        }
        
        while(low<=high){
            long mid = low + (high-low)/2;
            boolean possible= isDivisonPossible(sweetness, K, mid);
            if(possible){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return  (int)high;
    }
    boolean isDivisonPossible(int[] sweetness, int K, long sum) {
        long temp = 0, count = 0;
        
        for(int i=0;i<sweetness.length;i++){
            if((temp+sweetness[i])>=sum){
                count++;
                temp=0;
            }else{
                temp+=sweetness[i];
            }
        }
        return count > K;
    }
}
/*
9, 45, 26 
9, 26 17
9, 17 13



*/
