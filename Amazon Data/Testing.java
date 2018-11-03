import java.util.*;
import java.io.*;

public class Testing {
    public static void main(String args[]) {
        // String[] results = "Books[283155]   Subjects[1000]   Religion & Spirituality[22]   Christianity[12290]   Clergy[12360]   Preaching[12368]   ".split("   ");
        // for (String element : results) {
        //     System.out.println(element);
        // }

        String line = "title   Patterns of Preaching A Sermon Sampler";
        Scanner scan = new Scanner(line);
        String temp =  "";
        while (scan.hasNext()) {
            temp = temp.concat(scan.next() + " ");
        }
        System.out.println(temp);
    }
}