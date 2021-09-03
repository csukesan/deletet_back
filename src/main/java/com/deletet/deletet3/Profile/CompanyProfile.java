package com.deletet.deletet3.Profile;

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
public class CompanyProfile {

    @Id
    @SequenceGenerator(name = "ComProfile_sequence", sequenceName = "ComProfile_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ComProfile_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(
            nullable = true,
            name = "app_user_id"
    )
    private AppUser appuser;
    private Long userId;
    private String email;
    private String fullName;


    public CompanyProfile(Long userId, String email, String fullName) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
    }
}
