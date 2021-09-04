package com.deletet.deletet3.Forum;

import java.util.Date;

public class ForumDTO {

    private Long id;
    private String fullname;
    private String title;
    private String explanation;
    private String body;
    private String languages;
    private String date;
    private String status;
    private int likecount;
    private int upcount;
    private String imgUrl;
    private String likes;



    public ForumDTO(Long id, String fullname, String title, String explanation, String body, String languages, String date, String status, int likecount, int upcount,String imgUrl, String likes) {

        this.id = id;
        this.fullname = fullname;
        this.title = title;
        this.explanation = explanation;
        this.body = body;
        this.languages = languages;
        this.date = date;
        this.status = status;
        this.likecount=likecount;
        this.upcount=upcount;
        this.imgUrl=imgUrl;
        this.likes = likes;
    }

    public ForumDTO(Long id, String fullname, String title, String explanation, String body, String languages, String date, String status, int likecount, int upcount, String imgUrl) {
        this.id = id;
        this.fullname = fullname;
        this.title = title;
        this.explanation = explanation;
        this.body = body;
        this.languages = languages;
        this.date = date;
        this.status = status;
        this.likecount = likecount;
        this.upcount = upcount;
        this.imgUrl = imgUrl;
    }

    public ForumDTO() {
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getUpcount() {
        return upcount;
    }

    public void setUpcount(int upcount) {
        this.upcount = upcount;
    }

    public Long getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
