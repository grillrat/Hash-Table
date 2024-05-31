import java.util.*;
import java.io.FileOutputStream;
import java.io.*;

public class HashDriver
{
   //if file is empty, write into file. if not, read from file.
   //System.setOut puts println in file rather than run window
   //A PrintStream adds the ability to print representations of various data values conveniently to another output stream.
   public static void writeToFile(Tree[] hashTable,String fileName) throws IOException //make public static in driver
   {
      try
      {
         System.setOut(new PrintStream(new FileOutputStream(fileName)));  //can make file name an arg
         for(int i=0;i<=9999;i++)      
         {      
            DataItem item=new DataItem(i);   //DataItems 0 - 9999 w/ random words
            System.out.println(item);  //print each DataItem
         }
         System.out.flush();    
         System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
      }
      catch (IOException ex3)			//file is corrupted or doesn't exist
      {
         System.out.println("Something went wrong with the file"); //file of random DataItems
      }	
   }   
   
//read in the file into a hashed container class
//user will type in a word, program will return four digit number associated with word
//use random word to produce hash-code, which is used to determine location in container class
//hash container type = array of trees
   public static Tree[] readFile(String fileName) throws IOException //EDIT: Array of TreeNodes (roots) you can add to
   {
      Tree[] list = new Tree[1000];   
      try
      { 
         Scanner input=new Scanner(new FileReader(fileName));  //hashData.txt?
         while(input.hasNextLine())
         {
            String line=input.nextLine();
            String[] numWord=line.split(" ");  //if number and word are separated by a space
            int i=Integer.parseInt(numWord[0]);
            DataItem temp=new DataItem(i,numWord[1]);
            int index=temp.hashCode()%list.length; 
         //try to store temp in list at position index, resolve collision if there is one
            Tree t = new Tree();
            if(list[index]==null)   //if list[hashCode] is empty, put temp into it // if tree there is null, add it. if not, add to tree
            {
               t.add(temp);
               list[index]=t; //list is an array of trees
            }
            else  //list[hashCode] is not empty
            //add collision to tree at index
               list[index].add(temp);
         
         /*
         Split the line and retrieve the number and the word
         DataItem temp = new DataItem(with newly found number and word)
         int index = temp.hashCode()%(number of items in list)
         //try to store temp in list at the position index - resolve collisions if there is one
         */
         }
         input.close();
      }
      catch (IOException ex3)			//file is corrupted or doesn't exist
      {
         System.out.println("Something went wrong with the file"); //file of random DataItems
      }	
      return list;
   }

   
   public static void main(String[] arg)throws IOException
   {
      Scanner input = new Scanner(System.in);
      Tree[] hashTable=new Tree[1000]; 
      //File hashData=new File("hashData.txt");
      //writeToFile(hashTable,"hashData.txt"); 	
      hashTable=readFile("hashData.txt"); // Tree[] of Data Items
      
      //SEARCH
      System.out.println("Enter a word to search for:");
      String search=input.nextLine();
      DataItem temp=new DataItem(0,search);   //temp: 0 search
      int index=Math.abs(temp.hashCode())%hashTable.length; //index of temp        
      Tree ourTree=hashTable[index];   //Potential element of table is element of OG hashTable at index of search
      DataItem foundItem=(DataItem)(ourTree.findAndReturn(temp)); //value of found as DataItem
      if(foundItem != null)
      {
         System.out.println("The number is "+foundItem.getNumber());
      }
      else
      {
         System.out.println("Word Not Found");
      } 
   }//end main
}//end HashDriver