class Solution {
    // tcm total chinese medicine
    public int minCut(String s) {
        int n = s.length(),i=0;
        int[] cut = new int[n+1];
        Arrays.fill(cut, Integer.MAX_VALUE);
        cut[0] = -1;
        while(i<n){
            lps(cut,s,i,i);
            lps(cut,s,i,i+1);
            i++;
        }
        return cut[n];
    }
    void lps (int[] cut, String s, int left, int right){
        while(left>=0 && right<s.length() &&s.charAt(left)==s.charAt(right)) {
            cut[right+1] = Math.min(cut[right+1],cut[left]+1);
            left--;right++;
        }
    }
}

/*
a a b
   b a n a n a
-1 0 1 2 1 3 2
left 0, right 0 , dp[right+1]=[1] dp = -1+1=0
left=1,right=1 dp[right+1]=[2] = 0+1 = 1;
left =2, right = 2 dp[right+1]=[3] = 1+1=2
   //left=1, right =3, dp[right+1] = [3] = 0+1
left =3, right =3 dp[right+1] = [4] = 2+1 =3 or 1, 1
   //left = 2, right = 4, dp[right+1]= [5] = 2
   //left = 1, right = 5, dp[right+1]= [6] = 1,
   
*/
