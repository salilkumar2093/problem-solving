/*
x number of max elements across arrays
Given n number of array each with variable length  and a number (x), find max x number of elements across the arrays
Ex:
A1 = {100,99,98,90,81........n1}
A2= {99,97,85,80..........n2}
A3={100,98,95,90,89....n3}
A4={20,19,8...n4}
100
99


.
.
.
m arrays

Find max 5 elements: result {100,100,99,99,98}
Find max 3 elements: result {100,100,99}
*/
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
    Solution solution = new Solution();
    List<Integer> list1 = Arrays.asList(100,99,98,90,81);
    List<Integer> list2 = Arrays.asList(99,97,85,80);
    List<Integer> list3 = Arrays.asList(100,98,95,90,89);
    
    List<List<Integer>> mainList = new ArrayList<>();
    mainList.add(list1); mainList.add(list2); mainList.add(list3);
    
    System.out.println(solution.getMaxElements(mainList,3));
    System.out.println(solution.getMaxElements(mainList,5));
    System.out.println(solution.getMaxElements(mainList,0));
    System.out.println(solution.getMaxElements(mainList,-1));
    
    
    
  }
  List<Integer> getMaxElements(List<List<Integer>> mainList, int maxElements) {
    if(mainList==null || mainList.isEmpty() || maxElements<=0)
      return new ArrayList<>();
    
  PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapCustomComarator());

    for(List<Integer> currentList: mainList){  
        if(!currentList.isEmpty())
          maxHeap.add(new Node(0,currentList));
    }
    
    List<Integer> result = new ArrayList<>();
                    
    while(!maxHeap.isEmpty() && result.size()<maxElements){
        Node currentNode = maxHeap.poll();
        result.add(currentNode.list.get(currentNode.currIndex));
       
      //add again to the maxHeap
        if(currentNode.currIndex+1<currentNode.list.size()){
          maxHeap.add(new Node(currentNode.currIndex+1,currentNode.list));
        }  
      
    }         
    return result;
  }
  
}

class Node {
  int currIndex;
  List<Integer> list;
  //
  Node(int currIndex,List<Integer> list){
    this.currIndex=currIndex;
    this.list = list;
  }
   
}

class MaxHeapCustomComarator implements Comparator<Node> {
  
  public int compare(Node node1, Node node2) {
      return node2.list.get(node2.currIndex) -node1.list.get(node1.currIndex);
  }
  
  
}
