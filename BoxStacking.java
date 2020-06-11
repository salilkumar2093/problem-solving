public class Solution {
    public int solve(ArrayList<ArrayList<Integer>> A) {
        if(A.size()==0 || A.get(0).size()==0)
            return 0;
        Box[] boxes = getBoxes(A);
        return getHeight(boxes);
    }
    int getHeight(Box[] boxes) {
        int[] heights = new int[boxes.length];
        heights[boxes.length-1]= boxes[boxes.length-1].h;
        
        for(int i= boxes.length-2; i>=0;i--){
            int max=0;
            for(int j=i+1;j<boxes.length;j++){
                if(boxes[i].l>boxes[j].l && boxes[i].w>boxes[j].w){
                    max = Math.max(max, heights[j]);
                }
            }
            heights[i]=max+boxes[i].h;
        }   
        
        int maxHeight = 0;
        for(int height: heights){
            maxHeight = Math.max(maxHeight,height);
        }
        return maxHeight;
    }
    
    Box[] getBoxes(ArrayList<ArrayList<Integer>> A){
        Box[] boxes = new Box[A.size()*3];
        int index = 0;
        for(ArrayList<Integer> inputBox: A){
            int height = inputBox.get(0);
            int width = inputBox.get(1);
            int length = inputBox.get(2);
            boxes[index++] = new Box(height,Math.max(width,length),Math.min(width,length));
            boxes[index++] = new Box(width,Math.max(length,height),Math.min(length,height));
            boxes[index++] = new Box(length,Math.max(width,height),Math.min(width,height));
            
        }
        Arrays.sort(boxes, new SortByArea());
        return boxes;
    }
}
class Box{
    int h;
    int l;
    int w;
    long area;
    Box(int h, int w, int l) {
        this.h=h;
        this.l=l;
        this.w=w;
        this.area= (long)l*(long)w;
    }
   
}
class SortByArea implements Comparator<Box> {
    public int compare(Box b1, Box b2) {
        return -Long.compare(b1.area, b2.area);
    }
}
