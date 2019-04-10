package Container;
package Container;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Container {

    public final int MAX_ARRAY_SIZE = 20;
    public ArrayList<String>[] myHashArray;
    public static int numArrayResized;
    public static long lastTimeToResize = 0;
    public static long totalTimeToResize = 0;

    public Container(int size) {
        myHashArray = (ArrayList<String>[]) new ArrayList[size];
    }

    /**
     * Adds the string to the container
     *
     * @param str the string to add
     * @return true if add is successfull, else false
     */
    public boolean add(String str) {

        //determine index into hash table (array)
        int index = Math.abs(str.hashCode()) % myHashArray.length;

        //determine if the ArrayList exists, if not, create one
        if (myHashArray[index] == null) {
            myHashArray[index] = new ArrayList<String>();
        }
        //verify the str does not exist in the ArrayList
        boolean contains = false;
        for (String val : myHashArray[index]) {
            if (val.equals(str)) {
                contains = true;
            }
        }
        //if it does not exist, call add
        if (!contains) {
            myHashArray[index].add(str);
        }

        //if the current ArrayList size() is greater than MAX_ARRAY_SIZE, call resizeContainer()
        if (myHashArray.length > MAX_ARRAY_SIZE) {
            resizeContainer();
        }

        return true;
    }

    /**
     * Return true if str is currently in our Container
     *
     * @param str the string to check for duplicates
     * @return true if str is currently in our Container
     */
    public boolean contains(String str) {
        int index = Math.abs(str.hashCode()) % myHashArray.length;
        if (myHashArray[index] == null)
            return false;
        return myHashArray[index].contains(str);
    }

    /**
     * Return the number of Strings in our Container
     *
     * @return the number of Strings in our Container
     */
    public int size() {
        int number = 0;
        for (int i = 0; i < myHashArray.length; i++) {
            if (myHashArray[i] != null) {
                number += myHashArray[i].size();
            }
        }
        return number;
    }


    /**
     * Creates a new hash table (array) that is twice the size and add all the current strings into the new hash table
     */
    public void resizeContainer() {

        long startTime = System.nanoTime();
        //stats
        ArrayList<String>[] tempHashArray = myHashArray;
        myHashArray = (ArrayList<String>[]) new ArrayList[myHashArray.length];
        for (ArrayList<String> tempList : tempHashArray) {
            if (tempList != null) {
                for (String strTemp : tempList) {
                    add(strTemp);
                }
            }
        }

        numArrayResized++;
        System.out.println(numArrayResized);
        long endTime = System.nanoTime();
        lastTimeToResize = endTime - startTime;
        totalTimeToResize += lastTimeToResize;
    }

    /**
     * Returns the object as a string
     *
     * @return the string form of the object
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < myHashArray.length; i++) {
            s += ": ";
            if (myHashArray[i] == null) {
                s += " null";
            } else {
                for (String val : myHashArray[i]) {
                    s += val + " ";
                }
                s += "\n";
            }
        }
        return s;
    }

    /**
     * Returns the number of times the array has been resized
     *
     * @return numArrayResized
     */
    public int numArrayResized() {
        return (numArrayResized);
    }

    /**
     * Returns the statistics of the container
     *
     * @return the statistics of the container
     */
    public String getContainerStats() {
        System.out.println(numArrayResized);
        String str = "number of times static array was resized: " + numArrayResized + "\n";
        str += String.format("last time it took to resize array: %.2f usecs\n", lastTimeToResize / 1000.0);
        str += String.format("total time it took to resize array: %.2f usecs\n", totalTimeToResize / 1000.0);
        str += String.format("static array size: %d,  number of elements: %d\n", myHashArray.length, size());

        int max = 0;
        double avg = 0;
        int cntNulls = 0;

        str += String.format("max ArrayList size: %d,  average AL size: %.2f\n", max, avg);
        str += String.format("AL size threshold: %d,  num null entries: %d\n", MAX_ARRAY_SIZE, cntNulls);
        return str;
    }

    public static void main(String[] args) {
        Container myDictionary = new Container(5);
        System.out.println(myDictionary);
        myDictionary.add("dog");
        myDictionary.add("cat");
        myDictionary.add("hog");
        myDictionary.add("frog");
        myDictionary.add("bee");
        myDictionary.add("abc");
        myDictionary.add("flea");
        System.out.println(myDictionary);

        int dupCnt = 0;
        for (int k = 0; k < 50; k++) {
            String str = "";
            for (int j = 0; j < 3; j++)
                str += (char) (Math.random() * 10 + 'a');

            boolean result = myDictionary.add(str);
            if (!result)
                dupCnt++;

            if (k + 1 % 20 == 0)
                System.out.println(myDictionary);
        }
        System.out.println(myDictionary);
        System.out.println("duplicate cnt: " + dupCnt);
        System.out.println("Container cnt: " + myDictionary.size());


    }

}
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Container {

    public final int MAX_ARRAY_SIZE = 19;
    public ArrayList<String>[] myHashArray;
    public static int numArrayResized;
    public static long lastTimeToResize = 0;
    public static long totalTimeToResize = 0;

    public Container(int size) {
        myHashArray = (ArrayList<String>[]) new ArrayList[size];
    }

    /**
     * Adds the string to the container
     *
     * @param str the string to add
     * @return true if add is successfull, else false
     */
    public boolean add(String str) {

        //determine index into hash table (array)
        int index = Math.abs(str.hashCode()) % myHashArray.length;

        //determine if the ArrayList exists, if not, create one
        if (myHashArray[index] == null) {
            myHashArray[index] = new ArrayList<String>();
        }
        //verify the str does not exist in the ArrayList
        boolean contains = false;
        for (String val : myHashArray[index]) {
            if (val.equals(str)) {
                contains = true;
            }
        }
        //if it does not exist, call add
        if (!contains) {
            myHashArray[index].add(str);
        }

        //if the current ArrayList size() is greater than MAX_ARRAY_SIZE, call resizeContainer()
        if (myHashArray.length > MAX_ARRAY_SIZE) {
            resizeContainer();
        }

        return true;
    }

    /**
     * Return true if str is currently in our Container
     *
     * @param str the string to check for duplicates
     * @return true if str is currently in our Container
     */
    public boolean contains(String str) {
        int index = Math.abs(str.hashCode()) % myHashArray.length;
        if (myHashArray[index] == null)
            return false;
        return myHashArray[index].contains(str);
    }

    /**
     * Return the number of Strings in our Container
     *
     * @return the number of Strings in our Container
     */
    public int size() {
        int number = 0;
        for (int i = 0; i < myHashArray.length; i++) {
            if (myHashArray[i] != null) {
                number += myHashArray[i].size();
            }
        }
        return number;
    }


    /**
     * Creates a new hash table (array) that is twice the
     * size and add all the current strings into the new hash table
     */
    public void resizeContainer() {

        long startTime = System.nanoTime();
        //stats
        ArrayList<String>[] tempHashArray = myHashArray;
        myHashArray = (ArrayList<String>[]) new ArrayList[myHashArray.length];
        for (ArrayList<String> tempList : tempHashArray) {
            if (tempList != null) {
                for (String strTemp : tempList) {
                    add(strTemp);
                }
            }
        }

        numArrayResized++;
        System.out.println(numArrayResized);
        long endTime = System.nanoTime();
        lastTimeToResize = endTime - startTime;
        totalTimeToResize += lastTimeToResize;
    }

    /**
     * Returns the object as a string
     * @return the string form of the object
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < myHashArray.length; i++) {
            s += ": ";
            if (myHashArray[i] == null) {
                s += " null";
            } else {
                for (String val : myHashArray[i]) {
                    s += val + " ";
                }
                s += "\n";
            }
        }
        return s;
    }

    /**
     * Returns the number of times the array has been resized
     * @return numArrayResized
     */
    public int numArrayResized() {
        return (numArrayResized);
    }

    /**
     * Returns the statistics of the container
     * @return the statistics of the container
     */
    public String getContainerStats() {
        System.out.println(numArrayResized);
        String str = "number of times static array was resized: " + numArrayResized + "\n";
        str += String.format("last time it took to resize array: %.2f usecs\n", lastTimeToResize / 1000.0);
        str += String.format("total time it took to resize array: %.2f usecs\n", totalTimeToResize / 1000.0);
        str += String.format("static array size: %d,  number of elements: %d\n", myHashArray.length, size());

        int max = 0;
        double avg = 0;
        int countNulls = 0;

        str += String.format("max ArrayList size: %d,  average AL size: %.2f\n", max, avg);
        str += String.format("AL size threshold: %d,  num null entries: %d\n", MAX_ARRAY_SIZE, countNulls);
        return str;
    }

    public static void main(String[] args) {
        Container myDictionary = new Container(5);
        System.out.println(myDictionary);
        myDictionary.add("dog");
        myDictionary.add("cat");
        myDictionary.add("hog");
        myDictionary.add("frog");
        myDictionary.add("bee");
        myDictionary.add("abc");
        myDictionary.add("flea");
        System.out.println(myDictionary);

        int dupCount = 0;
        for (int k = 0; k < 50; k++) {
            String str = "";
            for (int j = 0; j < 3; j++)
                str += (char) (Math.random() * 10 + 'a');

            boolean result = myDictionary.add(str);
            if (!result)
                dupCount++;

            if (k + 1 % 20 == 0)
                System.out.println(myDictionary);
        }
        System.out.println(myDictionary);
        System.out.println("duplicate count: " + dupCount);
        System.out.println("Container count: " + myDictionary.size());


    }

}
