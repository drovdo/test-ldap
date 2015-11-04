package com.user.userModel;

public class UserModelSecurity {

    private String username;
    private String accessRights;

    public UserModelSecurity(String username, String accessRights) {
        this.username = username;
        this.accessRights = accessRights;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }
}
