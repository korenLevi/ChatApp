package com.hit.dm;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private static Long lastId = Long.valueOf(0);
    public String username;
    public String fullName;
    public String password;
    public String email;
    public String gender;
    public String phoneNo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public User() {
        this.id = ++lastId;
    }

    @Override
    public String toString() {
        return id + "," + username + "," + fullName  + "," + password + "," + email + "," + gender + "," + phoneNo;
    }
}
