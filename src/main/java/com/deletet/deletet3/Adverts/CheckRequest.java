package com.deletet.deletet3.Adverts;

public class CheckRequest {

    Long advertid;
    Long userid;

    public CheckRequest(Long advertid, Long userid) {
        this.advertid = advertid;
        this.userid = userid;
    }

    public Long getAdvertid() {
        return advertid;
    }

    public void setAdvertid(Long advertid) {
        this.advertid = advertid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
