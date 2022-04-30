package com.textifly.tazzatv.ui.Home.AllNews.Model;

public class AllNewsNewsModel {
    String title,image,publishedAt;

    public AllNewsNewsModel(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
