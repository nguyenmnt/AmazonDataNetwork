import java.util.*;
import java.io.*;

public class Save {
    public static void main(String args[]) {
        try {
			File file = new File(args[0]);
            Scanner sc = new Scanner(file);
            int id = 0, salesrank = 0, categoriesNum = 0, total = 0, similarNum = 0;
            double avg_ranking = 0;
            String title = "", asin= "", group = "";
            ArrayList<String> similar = new ArrayList<>();
            Set<String> categories = new HashSet<>();
            ArrayList<String> reviews = new ArrayList<>();
            
            while (sc.hasNextLine()) {
                System.out.println("here");
                String line = sc.nextLine();
                // if (line.equals("")) { continue; }
                Scanner in = new Scanner(line);
                String temp = in.next();
                if (temp.equals("Id")) {
                    id = Integer.parseInt(in.next());
                }
                else if (temp.equals("ASIN")) {
                    asin = in.next();
                }
                else if (temp.equals("title")) {
                    while(in.hasNext()) {
                        title.concat(in.next() + " ");
                    }
                    title = title.trim();
                }
                else if (temp.equals("salesrank")) {
                    salesrank = Integer.parseInt(in.next());
                }
                else if (temp.equals("similar")) {
                    similarNum = Integer.parseInt(in.next());
                    for (int i = 0; i < similarNum; i++) {
                        similar.add(in.next());
                    }
                }
                else if (temp.equals("categories")) {
                    categoriesNum = Integer.parseInt(in.next());
                    for (int i = 0; i < categoriesNum; i++) {
                        String tempLine = sc.nextLine();
                        String results[] = tempLine.split("   ");
                        for (String element : results) {
                            categories.add(element);
                        }
                    }
                }
                else if (temp.equals("reviews")) {
                    in.next();
                    total = Integer.parseInt(in.next());
                    for (int i = 0; i < 4; i++) {
                        in.next();
                    }
                    avg_ranking = Double.parseDouble(in.next());
                    for (int i = 0; i < total; i++) {
                        reviews.add(sc.nextLine());
                    }
                    System.out.print(asin);
                    for (int i = 0; i < similar.size(); i++) {
                        if (i == 0) {
                            System.out.print(" sm ");
                        }
                        System.out.print(similar.get(i) + " ");
                    }
                    System.out.println();
                    similar.clear();
                    categories.clear();
                    reviews.clear();
                }
                in.close();
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found: " + e);
        }
    }
}