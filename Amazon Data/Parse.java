import java.util.*;
import java.io.*;

public class Parse {
    public static void main(String args[]) {
        try {
			File file = new File(args[0]);
            Scanner sc = new Scanner(file);
            int id = 0, salesrank = 0, categoriesNum = 0, total = 0, similarNum = 0;
            double avg_ranking = 0;
            String title = "", asin= "", group = "";
            ArrayList<String> similar = new ArrayList<>();
            ArrayList<String> categories = new ArrayList<>();
            ArrayList<String> reviews = new ArrayList<>();

            ArrayList<Item> books = new ArrayList<>();
            ArrayList<Item> music = new ArrayList<>();
            ArrayList<Item> videos = new ArrayList<>();
            ArrayList<Item> dvds = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("")) { continue; }
                if (line.contains("discontinued product")) { continue; }
                Scanner in = new Scanner(line);
                String temp = in.next();
                if (temp.equals("Id")) {
                    id = Integer.parseInt(in.next());
                }
                else if (temp.equals("ASIN")) {
                    asin = in.next();
                }
                else if (temp.equals("title")) {
                    title = in.nextLine();
                }
                else if(temp.equals("group")) {
                    group = in.next();
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
                        categories.add(tempLine);
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

                    ArrayList<String> similarTemp = new ArrayList<>(similar);
                    ArrayList<String> categoriesTemp = new ArrayList<>(categories);
                    ArrayList<String> reviewsTemp = new ArrayList<>(reviews);

                    if (group.equals("Book")) {
                        books.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    else if (group.equals("Music")) {
                        music.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    else if (group.equals("Video")) {
                        videos.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    else if (group.equals("DVD")) {
                        dvds.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    similar.clear();
                    categories.clear();
                    reviews.clear();
                }
                in.close();
            }
            sc.close();
            String booksFile = "books_part2.txt";
            String musicFile = "music_part2.txt";
            String videosFile = "videos_part2.txt";
            String dvdsFile = "dvds_part2.txt";

            // printItems(books, booksFile);
            // printItems(music, musicFile);
            // printItems(videos, videosFile);
            // printItems(dvds, dvdsFile);

            printItemsForGephi(videos, videosFile);
            printItemsForGephi(dvds, dvdsFile);



        }
        catch (FileNotFoundException e) {
            System.out.println("file not found: " + e);
        }
    }

    public static void printItems(ArrayList<Item> items, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    writer.print(item.getAsin());
                    ArrayList<String> temp = item.getSimilars();
                    for (int j = 0; j < temp.size(); j++) {
                        if (j == 0) { writer.print(" sm "); }
                        writer.print(temp.get(j) + " ");
                    }
                    writer.println();
                }
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void printItemsForGephi(ArrayList<Item> items, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    ArrayList<String> temp = item.getSimilars();
                    if (temp.size() > 0) {
                        for (int j = 0; j < temp.size(); j++) {
                            writer.print(item.getAsin());
                            writer.print(";");
                            writer.print(temp.get(j));
                            writer.println();
                        }
                    }
                    else {
                        writer.print(item.getAsin());
                        writer.println();
                    }
                }
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}