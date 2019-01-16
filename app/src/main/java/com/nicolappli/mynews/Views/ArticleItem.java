package com.nicolappli.mynews.Views;

public class ArticleItem {

    private int id;
    private String title, category;
    private int image;

    public ArticleItem(int id, String title, String category, int image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getImage() {
        return image;
    }
}
