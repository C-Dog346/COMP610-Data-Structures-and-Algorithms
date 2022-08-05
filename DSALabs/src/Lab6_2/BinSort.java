/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6_2;

/**
   Class which demonstrates how bin sort can be used to
   sort distinct integer numbers between 0 and MAX_VALUE
*/

public class BinSort
{   
   public static void main(String[] args)
   {  int[] list = {17, 2, 23, 23, 5, 31, 7, 41, 29, 19, 43, 31, 5, 11, 47, 13,
         3, 37, 0, 1, 2, 3, 98, 99, 100, 47, 6}; // distinct integer values between 0 and MAX_VALUE
      final int MAX_VALUE = 100;
      int[] counter = new int[MAX_VALUE+1];
      boolean[] flags = new boolean[MAX_VALUE+1]; //initially all false
      // determine which bins should be set to true
      for (int i=0; i<list.length; i++) {
         flags[list[i]] = true;
         counter[list[i]]++;
      }
      // use the flags to put the numbers back in the list sorted
      int flagNo = 0;
      for (int i=0; i<list.length; i++)
      {  // find the next flag that is true
         while (flagNo<flags.length && !flags[flagNo])
            flagNo++;
         //if (flags[flagNo])
        if (flagNo<counter.length)
            if (counter[flagNo] > 1) {
                 for (int j = 0; j < counter[flagNo]; j++)
                     list[i+j] = flagNo;
                 flagNo++;
                 i += counter[flagNo-1]-1;
            }
            else
                list[i] = flagNo++;
      }
      // output the results
      for (int i=0; i<list.length; i++)
         System.out.print(((i>0)?", ":"") + list[i]);
      System.out.println();
   }
}
