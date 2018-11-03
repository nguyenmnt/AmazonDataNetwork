import java.util.*;
import java.io.*;

public class Item {
    private int id = 0, salesrank = 0;
    private double avg_ranking = 0;
    private String title = "", asin= "";
    private ArrayList<String> sim;
    private ArrayList<String> categories;
    private ArrayList<String> reviews;

    public Item(int id, String asin, String title, int salesrank, ArrayList<String> similar, ArrayList<String> categories, double avg_ranking, ArrayList<String> reviews) {
        this.id = id;
        this.asin = asin;
        this.title = title;
        this.salesrank = salesrank;
        this.sim = similar;
        this.categories = categories;
        this.avg_ranking = avg_ranking;
        this.reviews = reviews;
    }

    public int getId() {
        return this.id;
    }
    public String getAsin() {
        return this.asin;
    }
    public String getTitle() {
        return this.title;
    }
    public int getSalesrank() {
        return this.salesrank;
    }
    public double getAvgRanking() {
        return this.avg_ranking;
    }
    public ArrayList<String> getSimilars() {
        return this.sim;
    }
    public ArrayList<String> getCategories() {
        return this.categories;
    }
    public ArrayList<String> getReviews() {
        return this.reviews;
    }
}