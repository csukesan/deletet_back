package com.deletet.deletet3.Profile;


public class ProfileDTO {

    private Long id;
    private Long userId;
    private String imageUrl;
    private String fullName;
    private String about;
    private String tckn;
    private String address;
    private String website;
    private String phoneNumber;
    private String email;
    private String university;
    private String experiences;
    private String followingTitles;
    private int profilePoints;
    private int forumPoints;

    public ProfileDTO(Long userId,String fullName,String email){
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;

    }

    public ProfileDTO(Long id, Long userId, String imageUrl, String fullName, String about, String tckn, String address, String website, String phoneNumber, String email, String university, String experiences, String followingTitles, int profilePoints, int forumPoints) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.fullName = fullName;
        this.about = about;
        this.tckn = tckn;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.university = university;
        this.experiences = experiences;
        this.followingTitles = followingTitles;
        this.profilePoints = profilePoints;
        this.forumPoints = forumPoints;
    }

    public ProfileDTO(String imageUrl, String fullName, String about, String tckn, String address, String website, String phoneNumber, String email, String university, String experiences, String followingTitles) {
        this.imageUrl = imageUrl;
        this.fullName = fullName;
        this.about = about;
        this.tckn = tckn;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.university = university;
        this.experiences = experiences;
        this.followingTitles = followingTitles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTckn() {
        return tckn;
    }

    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getFollowingTitles() {
        return followingTitles;
    }

    public void setFollowingTitles(String followingTitles) {
        this.followingTitles = followingTitles;
    }

    public int getProfilePoints() {
        return profilePoints;
    }

    public void setProfilePoints(int profilePoints) {
        this.profilePoints = profilePoints;
    }

    public int getForumPoints() {
        return forumPoints;
    }

    public void setForumPoints(int forumPoints) {
        this.forumPoints = forumPoints;
    }
}
