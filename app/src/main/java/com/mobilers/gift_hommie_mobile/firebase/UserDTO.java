package com.mobilers.gift_hommie_mobile.firebase;

import com.google.firebase.Timestamp;

public class UserDTO {
    private String uId;
    private String username;
    private Timestamp createdTimestamp;

    public UserDTO() {
    }

    public UserDTO(String uId, String username) {
        this.uId = uId;
        this.username = username;
        this.createdTimestamp = Timestamp.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "uId='" + uId + '\'' +
                ", username='" + username + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                '}';
    }
}
