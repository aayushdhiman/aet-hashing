package Container;

import java.io.*;
import java.util.*;

public class Dictionary {

    /**
     * Performs a binary search on the ArrayList <code>list</code> for the String <code>word</code>
     * @param word the word to search for in the list
     * @param list the list to look for the word in
     * @return the location of the word in the list
     */
    public static int binarySearch(String word, ArrayList<String> list) {
        int first = 0;
        int end = list.size()-1;
        int mid = (first+end)/2;

        while(!list.get(mid).equals(word)){
            String s = list.get(mid);
            if (s.compareTo(word)>0){
                end = mid-1;
            }
            else {
                first = mid+1;
            }
            if (first > end){
                return -1;
            }
            mid = (first+end)/2;
        }
        return mid;
    }

    /**
     * Makes sure that the ArrayList arr is sorted
     * @param arr the array to verify for sorting
     */
    public static void verifyIsSorted(ArrayList<String> arr) {
        for (int k=0; k<arr.size()-1; k++)
            if (arr.get(k).compareTo(arr.get(k+1)) > 0)
            {
                System.out.println("not Sorted......" + k);
                return;
            }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner infile = new Scanner( new File("src/Container/words.txt") );

        //populate wordlist array, then close file
        ArrayList<String> wordlist = new ArrayList<String>();
        while (infile.hasNext()) {
            wordlist.add(infile.next());
        }

        infile.close();
        verifyIsSorted(wordlist);

        Container myContainer = new Container(20);
        for (String str : wordlist)
            myContainer.add(str);


        //compare binary search with a hash lookup (via the Container)
        long arrayListTime = 0;
        long hashListTime = 0;
        int numIterations = 100000;
        for (int k=0; k<numIterations; k++)
        {
            int randomIndex = (int)(Math.random() * wordlist.size());
            String randomStr = wordlist.get(randomIndex);

            long start = System.nanoTime();
            int index = binarySearch(randomStr, wordlist );
            long end = System.nanoTime();
            arrayListTime += end - start;

            start = System.nanoTime();
            myContainer.contains(randomStr);
            end = System.nanoTime();
            hashListTime += end - start;
        }

        System.out.printf("wordlist len: %d\naverages:\n   binary: %.2f\n   hash: %.2f\n",
                wordlist.size(),
                (double)arrayListTime/numIterations,
                (double)hashListTime/numIterations);

        System.out.println( myContainer.getContainerStats() );
    }

}

