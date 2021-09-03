package com.deletet.deletet3.Applications;

import com.deletet.deletet3.Adverts.Adverts;
import com.deletet.deletet3.Companies.Company;
import com.deletet.deletet3.appuser.AppUser;
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
public class Application {


        @Id
        @SequenceGenerator(name="application_squence",sequenceName= "application_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE,
                generator = "application_sequence")
        private Long id;
        @ManyToOne
        @JoinColumn(
            nullable = true,
            name = "app_user_id"
        )
        private AppUser appuser;
        private Long applicantId;

        @ManyToOne
        @JoinColumn(
            nullable = true,
            name = "company_id"
        )
        private Company company;
        private Long companyid;

        @OneToOne
        @JoinColumn(
            nullable = true,
            name = "adverts_id"
        )
        private Adverts advert;
        private Long advertId;
        private String companyName;
        private String companyLocation;
        @Column(columnDefinition = "TEXT")
        private String companyIcon;
        @Column(columnDefinition = "TEXT")
        private String companyDesc;
        private String applicationDate;
        private String wayOfWorking;
        private String advertsTitle;
        @Column(columnDefinition = "TEXT")
        private String advertsDescription;
        private String advertsAbout;
        private int status;

        public Application(Long applicantId, Long companyid, Long advertId, String companyName, String companyLocation, String companyIcon, String companyDesc, String applicationDate, String wayOfWorking, String advertsTitle, String advertsDescription, String advertsAbout, int status) {
            this.applicantId = applicantId;
            this.companyid = companyid;
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

    public Application(Long applicantId, Long companyid, Long advertId, int status) {
        this.applicantId = applicantId;
        this.companyid = companyid;
        this.advertId = advertId;
        this.status = status;
    }
}

