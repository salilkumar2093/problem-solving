static int getPartition(int[] arr, int n) {
	    ///check left max 
	    /// check right min and if they meet at the same index
	    int[] prevLeftMax = new int[n]; 
        prevLeftMax[0] = Integer.MIN_VALUE; 
        
	    int rightMin = arr[n-1];

	    for(int i=1;i<n;i++){
	        prevLeftMax[i] = Math.max(prevLeftMax[i-1],arr[i-1]);
	    }
	    for(int i=n-2;i>=0;i--){
	        if(arr[i]>prevLeftMax[i] && arr[i]<rightMin)
	            return arr[i];
	        rightMin = Math.min(rightMin,arr[i]);
	    }
	    return -1;
	}
