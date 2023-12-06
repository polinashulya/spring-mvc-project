package com.example.entity;

public class Contacts {

    private String contactsId;
    private String email;
    private long phoneNumber;

    public Contacts() {
    }

    public Contacts(String contactsId, String email, long phoneNumber) {
        this.contactsId = contactsId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getContactsId() {
        return contactsId;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
