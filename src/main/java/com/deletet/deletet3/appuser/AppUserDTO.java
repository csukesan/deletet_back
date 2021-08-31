package com.deletet.deletet3.appuser;

public class AppUserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String password;
    private AppUserRole appUserRole;

    public AppUserDTO()
    {

    }
    public AppUserDTO(Long id, String fullName, String email, String password, AppUserRole appUserRole)
    {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }
}
