import java.util.*;

//create a large text file consisting of 10,000 DataItems (each a unique number and a random word)
public class DataItem implements Comparable
{
   //2 arg constructor, toString, compareTo, hashCode(), get and set methods
   private int number;
   private String word;
   public DataItem(int n)
   {
      number=n;
      word=generateRandomWord();
   }
   
   public DataItem(int n,String w)
   {
      number=n;
      word=w;
   }
   
   //gets word at int value???
   public String getWord()
   {
      return word;
   }
   
   public int getNumber()
   {
      return number;
   }
   
   public void setNumber(int n)   //or set word at hash table index?
   { 
      number=n;
   }
   
   public void setWord(String w)
   {
      word=w;
   }
      
   //DataItems will compare to each other by the word
   public int compareTo(Object arg) 
   {
      DataItem temp = (DataItem)(arg);
      return this.word.compareTo(temp.word);
   }
   
   public boolean equals(Object arg) 
   {
      DataItem temp = (DataItem)(arg);
      return this.word.equals(temp.word);
   }
 
   //start w/ random consonant and alternate btw random vowels and random consonants
   //Extension: try to compose random words that sound more like regular English words
   private String generateRandomWord()
   {
      String [] vowels = {"a","e","i","o","u"};	
      String [] consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"};	
   
      String ans="";
      int length=(int)(Math.random()*6+3);   
      for(int i=0;i<length;i++)
      {
         if(i%2!=0)
         {
            int vIndex=(int)(Math.random()*(vowels.length));
            ans+=vowels[vIndex];
         }
         else
         {
            int cIndex=(int)(Math.random()*(consonants.length));
            ans+=consonants[cIndex];
         }     
      }
      return ans;
   }//end generateRandomWord()
   
   //hashCode = sum of ASCII values of each letter in name * its index position in name
   public int hashCode()
   {
      //or return Mathlabs(word.hashCode());
      int sum=0;
      for(int i=0;i<word.length();i++)
      {
         char letter=word.charAt(i);
         sum+=(int)(letter)*i;
      }
      return sum;
   }      
         
   public String toString()  //"number word"
   {
      return number+" "+word;
   }
}//end class