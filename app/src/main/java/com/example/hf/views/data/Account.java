package com.example.hf.views.data;

import java.util.Date;

/**
 * POJO class of an user account
 */
public class Account {

  private int id;
  private String givenName;
  private String familyName;
  private Date dob;
  private String address;
  private String email;
  private String phoneNumber;
  private Date lastOrderSince;

  public Account(int id, String givenName, String familyName, Date dob, String address,
                 String email, String phoneNumber, Date lastOrderSince) {
    this.id = id;
    this.givenName = givenName;
    this.familyName = familyName;
    this.dob = dob;
    this.address = address;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.lastOrderSince = lastOrderSince;
  }

  public int getId() {
    return id;
  }

  public String getGivenName() {
    return givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public Date getDob() {
    return dob;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Date getLastOrderSince() {
    return lastOrderSince;
  }

}
