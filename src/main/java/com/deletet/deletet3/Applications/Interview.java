package com.deletet.deletet3.Applications;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Interview {

    @Id
    @SequenceGenerator(name="interview_squence",sequenceName= "interview_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "interview_sequence")
    private Long id;
    private String applicantName;
    private Long applicantId;
    private String applicantIcon;
    private Long companyName;
    private Long companyId;
    private String companyIcon;
    private String title;
    private String applicationDesc;
    private String companyOfficer;
    private String companyDepartment;
    private String position;
    private String interDate;
    private String location;
    private String ApplicationStatus;
    private String interStatus;


    public Interview(String applicantName, Long applicantId, String applicantIcon, Long companyName, Long companyId, String companyIcon, String title, String applicationDesc, String companyOfficer, String companyDepartment, String position, String interDate, String location, String applicationStatus, String interStatus) {
        this.applicantName = applicantName;
        this.applicantId = applicantId;
        this.applicantIcon = applicantIcon;
        this.companyName = companyName;
        this.companyId = companyId;
        this.companyIcon = companyIcon;
        this.title = title;
        this.applicationDesc = applicationDesc;
        this.companyOfficer = companyOfficer;
        this.companyDepartment = companyDepartment;
        this.position = position;
        this.interDate = interDate;
        this.location = location;
        ApplicationStatus = applicationStatus;
        this.interStatus = interStatus;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantIcon() {
        return applicantIcon;
    }

    public void setApplicantIcon(String applicantIcon) {
        this.applicantIcon = applicantIcon;
    }

    public Long getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Long companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyIcon() {
        return companyIcon;
    }

    public void setCompanyIcon(String companyIcon) {
        this.companyIcon = companyIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicationDesc() {
        return applicationDesc;
    }

    public void setApplicationDesc(String applicationDesc) {
        this.applicationDesc = applicationDesc;
    }

    public String getCompanyOfficer() {
        return companyOfficer;
    }

    public void setCompanyOfficer(String companyOfficer) {
        this.companyOfficer = companyOfficer;
    }

    public String getCompanyDepartment() {
        return companyDepartment;
    }

    public void setCompanyDepartment(String companyDepartment) {
        this.companyDepartment = companyDepartment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInterDate() {
        return interDate;
    }

    public void setInterDate(String interDate) {
        this.interDate = interDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        ApplicationStatus = applicationStatus;
    }

    public String getInterStatus() {
        return interStatus;
    }

    public void setInterStatus(String interStatus) {
        this.interStatus = interStatus;
    }


    public Date StringToDate(String s){

        Date result = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result  = dateFormat.parse(s);
        }

        catch(ParseException e){
            e.printStackTrace();
        }
        return result ;
    }
}
