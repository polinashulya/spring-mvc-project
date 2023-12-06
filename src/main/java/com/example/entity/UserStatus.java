package com.example.entity;

public enum UserStatus {

    ADMIN(1),
    CUSTOMER(2),
    GUEST(3);


    private int userStatusId;

    private UserStatus(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public int getUserStatusId() {
        return userStatusId;
    }


}
