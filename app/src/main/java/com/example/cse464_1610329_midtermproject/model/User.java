package com.example.cse464_1610329_midtermproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

   String name;
   String email;
   String phoneNumber;
   String social1;
   String social2;
   String summary;

    public User(String name, String email, String phoneNumber, String social1, String social2, String summary) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.social1 = social1;
        this.social2 = social2;
        this.summary = summary;
    }
    public User(){}

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        social1 = in.readString();
        social2 = in.readString();
        summary = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSocial1() {
        return social1;
    }

    public void setSocial1(String social1) {
        this.social1 = social1;
    }

    public String getSocial2() {
        return social2;
    }

    public void setSocial2(String social2) {
        this.social2 = social2;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(phoneNumber);
        parcel.writeString(social1);
        parcel.writeString(social2);
        parcel.writeString(summary);
    }
}
