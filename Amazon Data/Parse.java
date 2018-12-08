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

            int totalBooks = 0, totalVideos = 0, totalMusic = 0, totalDVD = 0, totalAll = 0;
            double books_avg = 0, videos_avg = 0, music_avg = 0, dvd_avg = 0, all_avg = 0;

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
                        totalBooks += total;
                        books_avg += avg_ranking;
                        books.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    else if (group.equals("Music")) {
                        totalMusic += total;
                        music_avg += avg_ranking;
                        music.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    else if (group.equals("Video")) {
                        totalVideos += total;
                        videos_avg += avg_ranking;
                        videos.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    else if (group.equals("DVD")) {
                        totalDVD += total;
                        dvd_avg += avg_ranking;
                        dvds.add(new Item(id, asin, title, salesrank, similarTemp, categoriesTemp, avg_ranking, reviewsTemp));
                    }
                    totalAll += total;
                    all_avg += avg_ranking;

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

            printItems(books, booksFile);
            printItems(music, musicFile);
            printItems(videos, videosFile);
            printItems(dvds, dvdsFile);

            printItemsForGephi(videos, videosFile);
            printItemsForGephi(dvds, dvdsFile);

            System.out.println("Books: " + books_avg + " " + books.size());
            System.out.println("Music: " + music_avg + " " + music.size());
            System.out.println("Videos: " + videos_avg + " " + videos.size());
            System.out.println("DVDs: " + dvd_avg + " " + dvds.size());

            System.out.println(totalAll + " " + (books.size() + music.size() + videos.size() + dvds.size()));
            System.out.println(all_avg + " " + (books.size() + music.size() + videos.size() + dvds.size()));
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found: " + e);
        }
    }

    public static void maxNumberOfReviews(ArrayList videos, ArrayList boosk, ArrayList music, ArrayList dvds) {
        int maxNumReviewsForVideos = 0;
            int maxNumReviewsForBooks = 0;
            int maxNumReviewsForMusic = 0;
            int maxNumReviewsForDvds = 0;

            for (Item video: videos) {
                if (video.getReviews().size() > maxNumReviewsForVideos) {
                    maxNumReviewsForVideos = video.getReviews().size();
                }
            }

            for (Item book: books) {
                if (book.getReviews().size() > maxNumReviewsForBooks) {
                    maxNumReviewsForBooks = book.getReviews().size();
                }
            }

            for (Item mu: music) {
                if (mu.getReviews().size() > maxNumReviewsForMusic) {
                    maxNumReviewsForMusic = mu.getReviews().size();
                }
            }

            for (Item dvd: dvds) {
                if (dvd.getReviews().size() > maxNumReviewsForDvds) {
                    maxNumReviewsForDvds = dvd.getReviews().size();
                }
            }

            System.out.println(maxNumReviewsForVideos);
            System.out.println(maxNumReviewsForBooks);
            System.out.println(maxNumReviewsForMusic);
            System.out.println(maxNumReviewsForDvds);
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
