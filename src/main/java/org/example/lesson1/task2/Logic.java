package org.example.lesson1.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Logic {
    public static void printTop5Hashtags(ArrayList<String> list) {
        // Creating HashMap with our Hashtags as keys,
        // and number of appearance as value
        HashMap<String, Integer> count = new HashMap<>();

        //This loop splits our list, into separate lines of text
        for (String line : list) {
            //Creating set of hashtags in every line
            HashSet<String> hashtagsInLine = new HashSet<>();
            findAllHashtagsInALine(line, hashtagsInLine);

            //checking, whether our hashtag was unique
            for (String s : hashtagsInLine) {
                //if it was unique, adding it to our map with value "1"
                if (!count.containsKey(s)) {
                    count.put(s, 1);
                } else {
                    //if we already had it, we should just add 1 to our number of appearance
                    count.put(s, count.get(s) + 1);
                }
            }
        }
        //At this point, I wasn't sure, if I am allowed to use Stream API to sort our map by value
        //Because of that, I have done this task with 2 for-loops

        //Creating a set with our hashtags
        Set<String> allTags = count.keySet();

        //We only need top-5 hashtags
        printTop5HashtagsFromHashMap(count, allTags);
    }

    /**
     * We're splitting our line by " " separator, and then we're checking whether our word starts with "#" and if it does,
     * we're adding it to our set
     *
     * @param value The line of text that we're currently processing
     * @param hashtagsInLine This is a set of hashtags that we will be returning.
     */
    private static void findAllHashtagsInALine(String value, HashSet<String> hashtagsInLine) {
        //Splitting our words by " " separator
        String[] line = value.split(" ");
        for (String s : line) {
            //checking whether our word starts with "#"
            if (s.startsWith("#")) {
                //If true, adding this hashtag to our set
                hashtagsInLine.add(s);
            }

        }
    }

    /**
     * We iterate through the map and find the key with the highest value. We then print that key and value, and remove it
     * from the map. We repeat this process 5 times
     *
     * @param count a HashMap that stores the hashtag as the key and the number of times it appears as the value
     * @param allTags a set of all the hashtags in the file
     */
    private static void printTop5HashtagsFromHashMap(HashMap<String, Integer> count, Set<String> allTags) {
        for (int i = 0; i < 5; i++) {

            int max = 0;
            String maxString = "";
            //looking throw our map
            for (String s : allTags) {
                //if value of any key is higher than max value, we assign new max value
                int n = count.get(s);
                if (n > max) {
                    max = n;
                    //we should also remember the key to our max value
                    maxString = s;
                }

            }
            //after every iteration we should delete our max value from the map
            count.remove(maxString);
            //Printing our max value in a nice format
            System.out.println((i + 1) + ". " + maxString + " (" + max + ")");
        }
    }
}
