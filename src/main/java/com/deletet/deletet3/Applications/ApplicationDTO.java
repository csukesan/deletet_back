package com.deletet.deletet3.Applications;

public class ApplicationDTO {

    private Long id;
    private Long applicantId;
    private Long companyId;
    private Long advertId;
    private String companyName;
    private String companyLocation;
    private String companyIcon;
    private String companyDesc;
    private String applicationDate;
    private String wayOfWorking;
    private String advertsTitle;
    private String advertsDescription;
    private String advertsAbout;
    private int status;
    private String applicantName;
    private String applicantImg;
    private String applicantAbout;
    private String applicantExp;


    public ApplicationDTO()
    {

    }

    public ApplicationDTO(Long id, Long applicantId, Long companyId, Long advertId, String companyName, String companyLocation, String companyIcon, String companyDesc, String applicationDate, String wayOfWorking, String advertsTitle, String advertsDescription, String advertsAbout, int status) {
        this.id = id;
        this.applicantId = applicantId;
        this.companyId = companyId;
        this.advertId = advertId;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.companyIcon = companyIcon;
        this.companyDesc = companyDesc;
        this.applicationDate = applicationDate;
        this.wayOfWorking = wayOfWorking;
        this.advertsTitle = advertsTitle;
        this.advertsDescription = advertsDescription;
        this.advertsAbout = advertsAbout;
        this.status = status;
    }

    public ApplicationDTO(Long applicantId, Long companyId, Long advertId, int status) {
        this.applicantId = applicantId;
        this.companyId = companyId;
        this.advertId = advertId;
        this.status = status;
    }

    public ApplicationDTO(int status)
    {
        this.status = status;
    }

    public ApplicationDTO(Long companyId, Long advertId) {
        this.companyId = companyId;
        this.advertId = advertId;
    }

    public ApplicationDTO(Long id, Long applicantId, Long companyId, Long advertId, String companyName, String companyLocation, String companyIcon, String companyDesc, String applicationDate, String wayOfWorking, String advertsTitle, String advertsDescription, String advertsAbout, int status, String applicantName, String applicantImg, String applicantAbout, String applicantExp) {
        this.id=id;
        this.applicantId = applicantId;
        this.companyId = companyId;
        this.advertId = advertId;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.companyIcon = companyIcon;
        this.companyDesc = companyDesc;
        this.applicationDate = applicationDate;
        this.wayOfWorking = wayOfWorking;
        this.advertsTitle = advertsTitle;
        this.advertsDescription = advertsDescription;
        this.advertsAbout = advertsAbout;
        this.status = status;
        this.applicantName = applicantName;
        this.applicantImg = applicantImg;
        this.applicantAbout = applicantAbout;
        this.applicantExp=applicantExp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
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

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getWayOfWorking() {
        return wayOfWorking;
    }

    public void setWayOfWorking(String wayOfWorking) {
        this.wayOfWorking = wayOfWorking;
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

    public String getAdvertsAbout() {
        return advertsAbout;
    }

    public void setAdvertsAbout(String advertsAbout) {
        this.advertsAbout = advertsAbout;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantImg() {
        return applicantImg;
    }

    public void setApplicantImg(String applicantImg) {
        this.applicantImg = applicantImg;
    }

    public String getApplicantAbout() {
        return applicantAbout;
    }

    public void setApplicantAbout(String applicantAbout) {
        this.applicantAbout = applicantAbout;
    }


    public String getApplicantExp() {
        return applicantExp;
    }

    public void setApplicantExp(String applicantExp) {
        this.applicantExp = applicantExp;
    }
}
