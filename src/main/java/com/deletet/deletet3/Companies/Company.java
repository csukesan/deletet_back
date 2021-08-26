package com.deletet.deletet3.Companies;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @SequenceGenerator(name="company_squence",sequenceName= "company_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "company_sequence")
    private Long id;
    private String companyName;
    private String companyUrl;
    private String companyDepartment;
    private String establishment;
    private int employeeCount;
    private String address;
    private String contact;
    private String website;
    @Column(columnDefinition = "TEXT")
    private String companyAbout;

    public Company(Long id, String companyName, String companyUrl, String companyDepartment, String establishment, int employeeCount, String address, String contact, String website, String companyAbout) {
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
}
