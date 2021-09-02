package com.deletet.deletet3.Adverts;

import com.deletet.deletet3.appuser.AppUserRole;

public class AdvertsDTO {

    private Long id;
    private Long categoryId;
    private Long companyId;
    private String companyName;
    private String companyLocation;
    private String companyIcon;
    private String companyDesc;
    private String advertsDate;
    private String wayOfWorking;
    private String advertsAbout;
    private String advertsTitle;
    private String advertsDescription;

    public AdvertsDTO()
    {

    }

    public AdvertsDTO(Long id, Long categoryId, Long companyId, String companyName, String companyLocation, String companyIcon, String companyDesc, String advertsDate, String wayOfWorking, String advertsAbout, String advertsTitle, String advertsDescription) {
        this.id = id;
        this.categoryId = categoryId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.companyIcon = companyIcon;
        this.companyDesc = companyDesc;
        this.advertsDate = advertsDate;
        this.wayOfWorking = wayOfWorking;
        this.advertsAbout = advertsAbout;
        this.advertsTitle = advertsTitle;
        this.advertsDescription = advertsDescription;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCompanyIcon() {
        return companyIcon;
    }

    public void setCompanyIcon(String companyIcon) {
        this.companyIcon = companyIcon;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getAdvertsDate() {
        return advertsDate;
    }

    public void setAdvertsDate(String advertsDate) {
        this.advertsDate = advertsDate;
    }

    public String getWayOfWorking() {
        return wayOfWorking;
    }

    public void setWayOfWorking(String wayOfWorking) {
        this.wayOfWorking = wayOfWorking;
    }

    public String getAdvertsAbout() {
        return advertsAbout;
    }

    public void setAdvertsAbout(String advertsAbout) {
        this.advertsAbout = advertsAbout;
    }

    public String getAdvertsTitle() {
        return advertsTitle;
    }

    public void setAdvertsTitle(String advertsTitle) {
        this.advertsTitle = advertsTitle;
    }

    public String getAdvertsDescription() {
        return advertsDescription;
    }

    public void setAdvertsDescription(String advertsDescription) {
        this.advertsDescription = advertsDescription;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
