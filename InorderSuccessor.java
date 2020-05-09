
class Tree
{
	public Node inorderSuccessor(Node root,Node k){
         
            Node successor = null;
            Node ptr = k.right;
            while(ptr!=null){ 
                successor = ptr;
                ptr = ptr.left;
            }
 
          if(successor != null){
            return successor; 
          }
        Node parent=null;
        while(root!=null){
            if(k.data<root.data){
                 parent=root;
                 root=root.left;
            }else
                root=root.right;
        }
        return parent;
    }
   
}
