public class Solution {
    public String minWindow(String S, String T) {
        int start = 0;
        String result = "";
        while(start<S.length()){
            int j=0;
            for(int i=start; i<S.length(); i++){
                if(S.charAt(i)==T.charAt(j)){
                    if(j==0)
                        start=i;
                    j++;
                }
                
                if(j==T.length()){
                    String temp = S.substring(start,i+1);
                    if(result.equals("") || result.length()>temp.length()){
                        result=temp;
                    }
                    start++;
                    break;
                }
                if(i==S.length()-1){
                    return result;
                }
            }
        }
        return result;
    }
}
