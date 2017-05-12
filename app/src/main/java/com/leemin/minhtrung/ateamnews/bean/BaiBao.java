package com.leemin.minhtrung.ateamnews.bean;

import java.io.Serializable;

/**
 * Created by leemin on 3/27/17.
 */
// TODO (8):   Khởi tạo class chưa phần tử con, --> list
public class BaiBao implements Serializable {
    String title;
    String link;
    String image;

    public BaiBao(String title, String link, String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BaiBao{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
