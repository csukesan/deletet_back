package com.deletet.deletet3.Forum;

public class ForumLikeRequest {

    private String userid;

    public ForumLikeRequest(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ForumLikeRequest() {
    }
}
