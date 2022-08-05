package Lab6_1;

/**
   A class which includes common algorithms for sorting arrays
   of Comparable objects in place.
   Note the elements of the array are presumed to be non-null
*/

public class ArraySorter<E extends Comparable>
{
    public int counter = 0;
    public double totalTime = 0;
    
   public void selectionSort(E[] list)
   {  long start = System.nanoTime();
       int indexMin; // index of least element
      E temp; // temporary reference to an element for swapping
      for (int i=0; i<list.length-1; i++)
      {  // find the least element that has index>=i
         indexMin = i;
         for (int j=i+1; j<list.length; j++)
         {  if (list[j].compareTo(list[indexMin])<0) 
               indexMin = j;
            counter++;
         }
         // swap the element at indexMin with the element at i
         temp = list[indexMin];
         list[indexMin] = list[i];
         list[i] = temp;
      }
        long end = System.nanoTime();
      totalTime = end-start;
   }
   
   public void insertionSort(E[] list)
   {  long start = System.nanoTime();
       E elementInsert;
      for (int i=1; i<list.length; i++)
      {  // get the element at index i to insert at some index<=i
         elementInsert = list[i];
         // find index where to insert element to maintain 0..i sorted
         int indexInsert = i;
         while (indexInsert>0 && 
            list[indexInsert-1].compareTo(elementInsert)>0)
         {  // shift element at insertIndex-1 along one to make space
            list[indexInsert] = list[indexInsert-1];
            indexInsert--;
            counter++;
         }
         // insert the element
         list[indexInsert] = elementInsert;
      }
            long end = System.nanoTime();
      totalTime = end-start;
   }
   
   public void bubbleSort(E[] list)
   {  long start = System.nanoTime();
       E temp; // temporary reference to an element for swapping
      for (int i=list.length-1; i>=0; i--)
      {  // pass through indices 0..i and bubble (swap) adjacent
         // elements if out of order
         for (int j=0; j<i; j++)
         {  if (list[j].compareTo(list[j+1])>0)
            {  // swap the elements at indices j and j+1
               temp = list[j+1];
               list[j+1] = list[j];
               list[j] = temp;
               counter++;
            }
         }         
      }
      long end = System.nanoTime();
      totalTime = end-start;
   }
   
   public void quickSort(E[] list)
   {  quickSortSegment(list, 0, list.length);
   }
   
   // recursive method which applies quick sort to the portion
   // of the array between start (inclusive) and end (exclusive)
   private void quickSortSegment(E[] list, int start, int end)
   {  long start_ = System.nanoTime();
       if (end-start>1) // then more than one element to sort
      {  // partition the segment into two segments
         int indexPartition = partition(list, start, end);
         // sort the segment to the left of the partition element
         quickSortSegment(list, start, indexPartition);
         // sort the segment to the right of the partition element
         quickSortSegment(list, indexPartition+1, end);
      }
         long end_ = System.nanoTime();
      totalTime = end_-start_;
   }
   
   // use the index start to partition the segment of the list
   // with the element at start as the partition element
   // separating the list segment into two parts, one less than
   // the partition, the other greater than the partition
   // returns the index where the partition element ends up
   private int partition(E[] list, int start, int end)
   {  long start_ = System.nanoTime();
       E temp; // temporary reference to an element for swapping
      E partitionElement = list[start];
      int leftIndex = start; // start at the left end
      int rightIndex = end-1; // start at the right end
      // swap elements so elements at left part are less than
      // partition element and at right part are greater
      while (leftIndex<rightIndex)
      {  // find element starting from left greater than partition
         while (list[leftIndex].compareTo(partitionElement) <= 0
            && leftIndex<rightIndex) {
            leftIndex++; // this index is on correct side of partition
            counter++;
         }
         // find element starting from right less than partition
         while (list[rightIndex].compareTo(partitionElement) > 0) {
            rightIndex--; // this index is on correct side of partition
            counter++;
         }
         if (leftIndex<rightIndex)
         {  // swap these two elements
            temp = list[leftIndex];
            list[leftIndex] = list[rightIndex];
            list[rightIndex] = temp;
            counter++;
         }
         counter++;
      }
      // put the partition element between the two parts at rightIndex
      list[start] = list[rightIndex];
      list[rightIndex] = partitionElement;
      long end_ = System.nanoTime();
      totalTime = end_-start_;
      return rightIndex;
   }
   
   public void mergeSort(E[] list)
   {  mergeSortSegment(list, 0, list.length);
   }

   // recursive method which applies merge sort to the portion
   // of the array between start (inclusive) and end (exclusive)
   private void mergeSortSegment(E[] list, int start, int end)
   {  long start_ = System.nanoTime();
       int numElements = end-start;
      if (numElements>1)
      {  int middle = (start+end)/2;
         // sort the part to the left of middle
         mergeSortSegment(list, start, middle);
         // sort the part to the right of middle
         mergeSortSegment(list, middle, end);
         // copy the two parts elements into a temporary array
         E[] tempList = (E[])(new Comparable[numElements]); //unchecked
         for (int i=0; i<numElements; i++)
            tempList[i] = list[start+i];
         // merge the two sorted parts from tempList back into list
         int indexLeft = 0; // current index of left part
         int indexRight = middle-start; // current index of right part
         for (int i=0; i<numElements; i++)
         {  // determine which element to next put in list
            if (indexLeft<(middle-start))//left part still has elements
            {  if (indexRight<(end-start))// right part also has elem
               {  if (tempList[indexLeft].compareTo
                     (tempList[indexRight])<0) // left element smaller 
                     list[start+i] = tempList[indexLeft++];
                  else // right element smaller
                     list[start+i] = tempList[indexRight++];
                  counter++;
               }
               else // take element from left part
                  list[start+i] = tempList[indexLeft++];
               counter++;
            }
            else // take element from right part
               list[start+i] = tempList[indexRight++];
            counter++;
         } 
      }
            long end_ = System.nanoTime();
      totalTime = end_-start_;
   }
   
   // driver main method to test one of the algorithms
    public static void main(String[] args) {  
        ArraySorter<String> stringSorter = new ArraySorter<String>();
        String[] stringList = {"cow", "fly", "dog", "bat", "fox", "cat", "eel",
          "ant"};
        stringSorter.quickSort(stringList);
        // output the results
        for (int i=0; i<stringList.length; i++)
            System.out.print(((i>0)?", ":"") + stringList[i]);
        System.out.println("");
        System.out.println(stringSorter.counter);
        System.out.println(stringSorter.totalTime);
        System.out.println("");
        
        ArraySorter<String> almostSorter = new ArraySorter<String>();
        String[] almostList = {"ant", "bat", "cow", "cat", "dog", "eel", "fly",
          "fox"};
        almostSorter.quickSort(almostList);
        // output the results
        for (int i=0; i<almostList.length; i++)
            System.out.print(((i>0)?", ":"") + almostList[i]);
        System.out.println("");
        System.out.println(almostSorter.counter);
        System.out.println(almostSorter.totalTime);
        System.out.println("");
        
        ArraySorter<String> decreasingSorter = new ArraySorter<String>();
        String[] decreasingList = {"fox", "fly", "eel", "ant", "dog", "cow", "cat",
          "bat"};
        decreasingSorter.quickSort(decreasingList);
        // output the results
        for (int i=0; i<decreasingList.length; i++)
            System.out.print(((i>0)?", ":"") + decreasingList[i]);
        System.out.println("");
        System.out.println(decreasingSorter.counter);
        System.out.println(decreasingSorter.totalTime);
        System.out.println("");
    }
}
