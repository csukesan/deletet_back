package com.deletet.deletet3.HomePage;

public class NewsDTO {

    private Long id;
    private String companyName;
    private String companyIconUrl;
    private String newsDescription;
    private String newsImage;
    private int likeCount;
    private int commentCount;
    private String newsTag;

    public NewsDTO(){

    }

    public NewsDTO(Long id, String companyName, String companyIconUrl, String newsDescription, String newsImage, int likeCount, int commentCount, String newsTag) {
        this.id = id;
        this.companyName = companyName;
        this.companyIconUrl = companyIconUrl;
        this.newsDescription = newsDescription;
        this.newsImage = newsImage;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.newsTag = newsTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIconUrl() {
        return companyIconUrl;
    }

    public void setCompanyIconUrl(String companyIconUrl) {
        this.companyIconUrl = companyIconUrl;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getNewsTag() {
        return newsTag;
    }

    public void setNewsTag(String newsTag) {
        this.newsTag = newsTag;
    }
}
