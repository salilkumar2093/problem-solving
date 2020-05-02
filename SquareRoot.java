double squareRoot(int number, int precision) 
    { 
        int start = 0, end = number; 
        int mid; 
  
        // variable to store the answer 
        double ans = 0.0; 
  
        // for computing integral part 
        // of square root of number 
        while (start <= end)  
        { 
            mid = (start + end) / 2; 
              
            if (mid * mid == number)  
            { 
                ans = mid; 
                break; 
            } 
  
            if (mid * mid < number) { 
                start = mid + 1; 
                ans = mid; 
            } 
            else { 
                end = mid - 1; 
            } 
        } 
        double increment = 0.1; 
        for (int i = 0; i < precision; i++) { 
            while (ans * ans <= number) { 
                ans += increment; 
            } 
  
            ans = ans - increment; 
            increment = increment / 10; 
        } 
        return (float)ans; 
    } 
  
    // Driver code 
    public static void main(String[] args) 
    { 
        // Function calling 
        System.out.println(squareRoot(50, 3)); 
  
        // Function calling 
        System.out.println(squareRoot(10, 4)); 
    } 
    
