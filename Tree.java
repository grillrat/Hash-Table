import java.io.*;
/**a binary-search-tree container d oberle 10/2021.  */  
public class Tree
{
/** Data field: a reference to the first node of the tree. */
   private TreeNode myRoot;

/** No arg constructor initializes and empty tree. */   
   public Tree()
   {
      myRoot = null;
   }
   
/**Adds a new element to the tree such that the tree is still an ordered Binary Search Tree.
 * @param  x a non-null Comparable Object.
 */   
   public void add(Comparable x)
   {
      myRoot = addHelper(myRoot, x);
   }
  
/**Helper method for add(x).
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @param   x a non-null Comparable Object.   
 * @return  the root of the ordered binary search tree after x has been added.
 */    
   private TreeNode addHelper(TreeNode root, Comparable x)
   {
   //************COMPLETE THIS METHOD****************************    
      //the root has a left subtree that is a root of a subtree. root.getRight() is a root of the right subtree that is empty
      //
      //to add: send it the root. Is x < root's value? 
      //If no, 1) add x to root's right subtree: set the root's right to a new tree that is the result after adding x to this tree
      //2) root.setRight(x): set the pointer to a new tree that is the result of adding x to the root's left/right side
      //add x to the tree that is pointed to by a root to an empty tree
      
      /*
      if(root is null)
         reassign root to new TN with value x
      else
         if(x is < root's val)
            set root's left to the tree in which x was added to
            the left subtree (recursive call)
         if(x is > root's val)
            set root's right to the tree in which x was added to
            the right subtree (recursive call)
      */
      if(root!=null) 
      {
         if(x.compareTo(root.getValue())<0)  //x less than root value
            //setLeft to tree that you get after adding x
            root.setLeft(addHelper(root.getLeft(),x)); //a root of an empty tree points to new tree that is the reuslt of adding x to root's left
         else  //x greater than root value
            root.setRight(addHelper(root.getRight(),x)); 
      } 
      else  //terminating case
         root=new TreeNode(x);  
      return root;   
   }
   
/**Removes an element from the tree such that the tree is still an ordered Binary Search Tree.
 * @param  x a non-null Comparable Object.
 */   
   public void remove(Comparable x)
   {
      myRoot = removeHelper(myRoot, x);
   }
   
   //from Trees ex. 3
   public boolean hasOneChild(TreeNode root)
   {
      boolean state = false;
      if (root != null)
         state = ((root.getLeft()!=null && root.getRight()==null) ||
                      (root.getLeft()==null && root.getRight()!=null));
      return state;
   }
   
/**Helper method for remove(x).
 * @param   root is the root of a tree (or subtree for recursive calls).  
 * @param   x a non-null Comparable Object. 
 * @return  the root of the ordered binary search tree after x has been removed.
 */   
   private TreeNode removeHelper(TreeNode root, Comparable x)
   {
   //************COMPLETE THIS METHOD*****************************         
      if(root!=null)
      {
         TreeNode d = searchHelper(root, x);
         TreeNode p = searchParent(root, x);
         if(isLeaf(d))
         {
            if(p==null) //d is THE root (last node in the tree and it is being removed)
               root=null;
            else 
               if(p.getLeft()==d) //if d is a left child of p
                  p.setLeft(null);
               else 
                  if(p.getRight()==d) //if d is a right child of p
                     p.setRight(null); 
            return root;
         }
         else if(hasOneChild(d)) // (ex)
         {
            //4 subcases to have p take custody of d's child
            if(root!=null) //inner root?
            {
               if(hasOneChild(root.getLeft()))
               {
                  d = root.getLeft();
                  if(d.getLeft()!=null)
                     root.setLeft(d.getLeft());
                  else // d has right child
                     root.setLeft(d.getRight());
                  return root;
               }
               if(hasOneChild(root.getRight()))
               {
                  d = root.getRight();
                  if(d.getLeft()!=null)
                     root.setRight(d.getLeft());
                  else
                     root.setRight(d.getRight());
                  return root;
               }
               hasOneChild(root.getLeft());
               hasOneChild(root.getRight());
               return root;
            }
         }
         else if(d==null)
            return root;
         else if(d.getLeft()!=null&&d.getRight()!=null)
         {
            //start m at d's left, then move right while its right is not null
            TreeNode m = d.getLeft();  //largest value in d's left subtree
            while(m.getRight()!=null)
               d.getRight();  //RECURSION????
            Comparable temp = m.getValue();
            removeHelper(root,temp);
            d.setValue(temp);
            return root;
         }
      }
      return root;    
   }
   
/** Displays  the elements of the tree such that they are displayed in prefix order. */  
   public void showPreOrder()
   {
      preOrderHelper(myRoot);
      System.out.println();  
   }
   
/**Helper method for showPreOrder().
 * Because the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls).  
 */   
   private void preOrderHelper(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************
   //PREORDER: RT, LT, Ro
   //Root of the tree
   //Left subtree w/ new inner root
      //repeats until it gets to leaf (terminating case)
   //Right subtree w/ new inner root
      //repeats root left right until it gets to leaf (terminating case)          
      
      if(root!=null)
      {
         System.out.print(root.getValue() + " ");    
         preOrderHelper(root.getLeft());
         preOrderHelper(root.getRight());
      }      
   }
   
/** Displays  the elements of the tree such that they are displayed in infix order. */ 
   public void showInOrder()
   {
      inOrderHelper(myRoot);  //recursive method that does work is private
      System.out.println();
   }

/**Helper method for showInOrder().
 * Because the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls).  
 */   
   private void inOrderHelper(TreeNode root)   //if public, user would have access to root (which it doesn' tneed). client only needs to know what they send 
   {
      if(root!=null)
      {
         inOrderHelper(root.getLeft());
         System.out.print(root.getValue() + " ");    
         inOrderHelper(root.getRight());
      }
   }
      
/** Displays  the elements of the tree such that they are displayed in postfix order. */ 
   public void showPostOrder()
   {
      postOrderHelper(myRoot);
      System.out.println();   
   }
   
/**Helper method for showPostOrder(). 
 * Because the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls).  
 */   
   private void postOrderHelper(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************  
      if(root!=null)
      {
         postOrderHelper(root.getLeft());
         postOrderHelper(root.getRight());
         System.out.print(root.getValue() + " ");    
      }
   }
   
/**Searches for an element in the tree.
 * @param   x a non-null Comparable Object.
 * @return  true if x is found; false if x is not found in the tree
 */    
   public boolean contains(Comparable x)
   {
      if (searchHelper(myRoot, x)==null)
         return false;
      return true;
   }

/**Helper method for contains(x). UPDATED FOR HASH TABLE
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (subroots for recursive calls).
 * @param   x a non-null Comparable Object.
 * @return  a pointer to the TreeNode that contains the value x; returns null if not found
 */      
   private TreeNode searchHelper(TreeNode root, Comparable x)
   {
      if(root==null)
         return null;
      if(x.equals(root.getValue()))
         return root;
      if(x.compareTo(root.getValue())<0)
         return(searchHelper(root.getLeft(),x));
      return(searchHelper(root.getRight(),x));
   }

/**Helper method for removeHelper(root, x).
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (subroots for recursive calls).
 * @param   x a non-null Comparable Object.
 * @return  a pointer to the parent of the node that contains the value x; returns null if not found
 */    
   private TreeNode searchParent(TreeNode root, Comparable x)
   {
   //************COMPLETE THIS METHOD*****************************
   
      //searchParent - oneKid methods will be used in removeHelper method 
      if(root==null)
         return null;    
      if(root.getLeft()!=null&&root.getLeft().getValue().equals(x))    //if(root is a parent to a left child that has x)
         return root;
      if(root.getRight()!=null&&root.getRight().getValue().equals(x))
         return root;
      if(x.compareTo(root.getValue())<0)  //what you're looking for < root's value
         return searchParent(root.getLeft(),x); //search in left-subtree
      else  //what you're looking for > root's value
         return searchParent(root.getRight(),x); //search in right-subtree   
   }
   
/**Helper method for removeHelper(root, x).
 * @param   root is the root of a tree.
 * @return  true if root has no children; returns false if root has 1 or 2 children
 */ 
   private boolean isLeaf(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************  
      if(root==null)
         return false;
      if(root.getLeft()==null&&root.getRight()==null)
         return true;
      return false;     //temporary return statement to keep things compiling
   }
      
/**Helper method for removeHelper(root, x).
 * @param   root is the root of a tree.
 * @return  true if root has exactly one child; returns false if root has 0 or 2 children
 */
   private boolean oneKid(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************
      // if((root.getLeft()!=null&&root.getRight()==null)||(root.getLeft()==null&&root.getRight()!=null))
         // return true;
         
      //From Tree Ex 3
      boolean state = false;
      if (root != null)
         state = ((root.getLeft()!=null && root.getRight()==null) ||
                      (root.getLeft()==null && root.getRight()!=null));
      return state;
   }
      
/**Returns the number of logical elements stored in the tree.
 * @return the number of nodes in the tree.
 */ 
   public int size()
   {
      return sizeHelper(myRoot);
   }
   
/**Helper method for size().
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @return  the size of the tree that starts at root 
 */    
   private int sizeHelper(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************  
      if(root==null)
         return 0;
      return 1+(sizeHelper(root.getLeft()))+(sizeHelper(root.getRight())); 
         
   }

/**Returns the number of levels in the tree.  
 * An empty tree is height -1.  A 1-node tree is height 0.
 * A 2-node tree and a balanced 3-node tree are height 1.
 * @return the height/depth/number of levels of the tree.
 */          
   public int height()
   {
      return heightHelper(myRoot);
   }

/**Helper method for height().
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @return the height/depth/number of levels of the tree that starts at root.
 */   
   public int heightHelper(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************
      if(isLeaf(root))  //root points to leaf
         return 0;
      if(root==null) //height of empty tree
         return -1;
   //if neither above is true, height must be >= 1
      //height of any tree with more than one node (?) = height of whichever side is longer
      //return 1 + the larger of the two (between the height of the left and the height of the right)
      //Math.max(x, y)  will return the larger of the two between x and y. 
      return 1+Math.max((heightHelper(root.getLeft())),(heightHelper(root.getRight()))); 
   }
   
/**EXTRA CREDIT: returns true if p is an ancestor of c.
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @param   p a non-null Comparable Object that can be found in the tree. 
 * @param   c a non-null Comparable Object that can be found in the tree.  
 * @return  true if p is an ancestor of c; return false if not or one/both can not be found in the tree.
 */    
   public boolean isAncestor(TreeNode root, Comparable p, Comparable c)
   {
   //************COMPLETE THIS METHOD***************************
   /* @return  a pointer to the TreeNode that contains the value x; returns null if not found
   */   
   //search within tree rooted at p for child c
      TreeNode pointer=searchHelper(root, p);
      if(pointer.getLeft()==c||pointer.getRight()==c)
         return true;
      return false;     //temporary return statement to keep things compiling
   }
   
/**EXTRA CREDIT: displays all elements of the tree at a particular depth/level/height.
 * level 0 will show the root.  level 1 will show all elements that are children of the root.
 * A level that is less than 0 or greater than the max depth will display nothing.
 * @param   root is the root of a tree. 
 * @param   level is the depth in which you want to see all the elements of. 
 */ 
   public void printLevel(TreeNode root, int level)
   {
      //count 1 in recursive traversal if node has a child
      //return root;
      //if(root.getLeft()==null&root.getRight()==null)  
   }
 
/**Nothing to see here...move along.*/     
   private String temp;
/**Helper method for toString().
 * @param   root is the root of a tree (or subtree for recursive calls). 
 */ 
   private void inOrderHelper2(TreeNode root)   
   {
      if(root!=null)
      {
         inOrderHelper2(root.getLeft());
         temp += (root.getValue() + ", "); 
         inOrderHelper2(root.getRight());
      }
   }
   
   //ADDED FOR HASH TABLE
   public Comparable findAndReturn(Comparable x)
   {
      TreeNode found=searchHelper(myRoot,x);
      if(found!=null)
         return found.getValue();
      return null;
   }
      
/**Returns a String of all the elements in the tree in the form [a1, a2, a3, . . . , an] in order
 * @return String of all the in-oder tree elements separated by a comma
 */
   @Override
   public String toString()
   {
      temp="";
      inOrderHelper2(myRoot);
      if(temp.length() > 1)
         temp = temp.substring(0, temp.length()-2);
      return "[" + temp + "]";
   }
}