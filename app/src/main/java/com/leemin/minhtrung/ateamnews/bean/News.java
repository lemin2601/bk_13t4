package com.leemin.minhtrung.ateamnews.bean;

public class News {
    private int nid;
    private String title;
    private String description;
    private String detail;
    private String link ;
    private String datacreate;
    private String writer;

    private int cid ;
    private String cname;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDatacreate() {
        return datacreate;
    }

    public void setDatacreate(String datacreate) {
        this.datacreate = datacreate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public News() {

    }

    public News(int nid, String title, String description, String detail, String link, String datacreate, String writer, int cid) {
        this.nid = nid;
        this.title = title;
        this.description = description;
        this.detail = detail;
        this.link = link;
        this.datacreate = datacreate;
        this.writer = writer;
        this.cid = cid;
    }

    public News(int nid, String title, String description, String detail, String link, String datacreate, String writer, int cid, String cname) {
        this.nid = nid;
        this.title = title;
        this.description = description;
        this.detail = detail;
        this.link = link;
        this.datacreate = datacreate;
        this.writer = writer;
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return this.nid + "-" + this.title + "-" + this.link + "-" + this.cname + this.cid;
    }
}