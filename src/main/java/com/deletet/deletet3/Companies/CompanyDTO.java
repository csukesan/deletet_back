package com.deletet.deletet3.Companies;

public class CompanyDTO {


    private Long id;
    private String companyName;
    private String companyUrl;
    private String companyDepartment;
    private String establishment;
    private int employeeCount;
    private String address;
    private String contact;
    private String website;
    private String companyAbout;

    public CompanyDTO()
    {

    }

    public CompanyDTO(Long id, String companyName, String companyUrl, String companyDepartment, String establishment, int employeeCount, String address, String contact, String website, String companyAbout) {
        this.id = id;
        this.companyName = companyName;
        this.companyUrl = companyUrl;
        this.companyDepartment = companyDepartment;
        this.establishment = establishment;
        this.employeeCount = employeeCount;
        this.address = address;
        this.contact = contact;
        this.website = website;
        this.companyAbout = companyAbout;
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

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyDepartment() {
        return companyDepartment;
    }

    public void setCompanyDepartment(String companyDepartment) {
        this.companyDepartment = companyDepartment;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyAbout() {
        return companyAbout;
    }

    public void setCompanyAbout(String companyAbout) {
        this.companyAbout = companyAbout;
    }
}
