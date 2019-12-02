package com.company.model;

@Table(name = "users")
public class User {

    @Primary
    @Column(name = "uid")
    private Long id;

    private String username;
    private String fullname;
    private String passhash;

    private Boolean disabled;

    public User () {}
    public User(Long id, String username, String fullname, String passhash, Boolean disabled) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.passhash = passhash;
        this.disabled = disabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPasshash() {
        return passhash;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }

    public Boolean getDisabled() { return disabled; }

    public void setDisabled(Boolean disabled) { this.disabled = disabled; }
}
