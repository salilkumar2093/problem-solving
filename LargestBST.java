class SubTree {
	int max;
	int min;
	boolean isBST = false;
	int size;

	public SubTree(int max, int min, boolean isBST, int size) {
		super();
		this.max = max;
		this.min = min;
		this.isBST = isBST;
		this.size = size;
	}

}
class Solution{
    
    static int largestBst(Node node)
    {
        // Write your code here
        
        if(node==null)
        return 0;
        if(node.left==null && node.right==null)
            return 1;

		SubTree s = recur(node);
        System.out.println(s.size);
        return s.size;
    }
    static SubTree recur (Node node) {
        if(node == null)
            return null;
        
        SubTree left = recur(node.left);
        SubTree right = recur(node.right);
        //System.out.println(node.data + "  "+left + " "+right);
        if (left == null && right == null)
			return new SubTree(node.data, node.data, true, 1);

        int min = node.data;
        int max = node.data;
        boolean bst = true;
        int leftSize=0;
        int rightSize=0;
        if(left!=null){
            if (!left.isBST || node.data < left.max)
				bst = false;
            min = Math.min(min,left.min);
            max = Math.max(max,left.max);
            leftSize= left.size;
        }
        
        if(right!=null){
            if (!right.isBST || node.data > right.min)
				bst = false;
			min = Math.min(min,right.min);
            max = Math.max(max,right.max);
			rightSize= right.size;
        }
        if(!bst)
            return new SubTree(0, 0, false, Math.max(leftSize, rightSize));
        
		return new SubTree(max, min, true, 1+leftSize + rightSize);
        
    }
    
}
