package com.example.bookbub.model;

import java.io.Serializable;

public class Book implements Serializable {
    String profile_id;

    public String getExixtingCompanions() {
        return exixtingCompanions;
    }

    public void setExixtingCompanions(String exixtingCompanions) {
        this.exixtingCompanions = exixtingCompanions;
    }

    String exixtingCompanions;
    public String getCompanions() {
        return companions;
    }

    public void setCompanions(String companions) {
        this.companions = companions;
    }

    String companions;
    private int originalPosition; // add this field

    public String getPid() {
        return profile_id;
    }

    public void setPid(String pid) {
        this.profile_id = pid;
    }

    String first_name;
    String middle_name;
    String last_name;
    String gender;
    String mobile_number;
    String profile_status;
    String manglik;
    String occupation;
    String income;
    String physicalpath;
    String origin_family;
    public Book() {
    }

    public Book(String profile_id,String first_name, String middle_name, String last_name, String gender, String mobile_number, String profile_status, String manglik, String occupation, String income, String physicalpath, String origin_family,String companions,String exixtingCompanions,int originalPosition) {
        this.exixtingCompanions=exixtingCompanions;
        this.companions=companions;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.gender = gender;
        this.profile_id=profile_id;
        this.mobile_number = mobile_number;
        this.profile_status = profile_status;
        this.manglik = manglik;
        this.occupation = occupation;
        this.income = income;
        this.physicalpath = physicalpath;
        this.origin_family = origin_family;
        this.originalPosition=originalPosition;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getProfile_status() {
        return profile_status;
    }

    public void setProfile_status(String profile_status) {
        this.profile_status = profile_status;
    }

    public String getManglik() {
        return manglik;
    }

    public void setManglik(String manglik) {
        this.manglik = manglik;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getPhysicalpath() {
        return physicalpath;
    }

    public void setPhysicalpath(String physicalpath) {
        this.physicalpath = physicalpath;
    }

    public String getOrigin_family() {
        return origin_family;
    }

    public void setOrigin_family(String origin_family) {
        this.origin_family = origin_family;
    }
    public int getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(int originalPosition) {
        this.originalPosition = originalPosition;
    }
}