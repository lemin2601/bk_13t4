package com.leemin.minhtrung.ateamnews.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin on 5/9/2017.
 */

public class Category implements Serializable {

    private int cid;
    private String name ;
    private int active;
    private String linkrss;


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getLinkrss() {
        return linkrss;
    }

    public void setLinkrss(String linkrss) {
        this.linkrss = linkrss;
    }

    public Category() {
    }

    public Category(int cid, String name, int active, String linkrss) {
        this.cid = cid;
        this.name = name;
        this.active = active;
        this.linkrss = linkrss;
    }

    @Override
    public String toString() {
        return this.cid + "-" + this.name + "-"  + this.active + "-" + this.linkrss;
    }

    public ArrayList<Category> getListCat() {
        //get dl mới
        ArrayList<Category> outList = new ArrayList<>();
        Category category = new Category(0, "Trang chủ", 1, "http://vnexpress.net/rss/tin-moi-nhat.rss");
        outList.add(category);
        category = new Category(0, "Thời sự", 1, "http://vnexpress.net/rss/thoi-su.rss");
        outList.add(category);
        category = new Category(0, "Thế giới", 1, "http://vnexpress.net/rss/the-gioi.rss");
        outList.add(category);
        category = new Category(0, "Kinh doanh", 0, "http://vnexpress.net/rss/kinh-doanh.rss");
        outList.add(category);
        category = new Category(0, "Thể thao", 1, "http://vnexpress.net/rss/the-thao.rss");
        outList.add(category);
        category = new Category(0, "Giải trí", 0, "http://vnexpress.net/rss/giai-tri.rss");
        outList.add(category);
        category = new Category(0, "Sức khỏe", 1, "http://vnexpress.net/rss/suc-khoe.rss");
        outList.add(category);
        category = new Category(0, "Giáo dục", 1, "http://vnexpress.net/rss/giao-duc.rss");
        outList.add(category);
//        category = new Category(0, "Làm đẹp", 0, "http://www.24h.com.vn/upload/rss/lamdep.rss");
//        outList.add(category);
//        category = new Category(0, "Phim", 1, "http://www.24h.com.vn/upload/rss/phim.rss");
//        outList.add(category);
//        category = new Category(0, "Thế giới", 1, "http://www.24h.com.vn/upload/rss/tintucquocte.rss");
//        outList.add(category);
//        category = new Category(0, "", 1, "");
//        outList.add(category);

        return outList;
    }
}
