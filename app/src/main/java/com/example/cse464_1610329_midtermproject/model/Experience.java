package com.example.cse464_1610329_midtermproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Experience implements Parcelable {

    String label;
    String company_name;
    String title;
    String highlight1;
    String highlight2;
    String highlight3;
    String start_date;
    String end_date;

    public Experience(String label, String company_name, String title, String highlight1, String highlight2, String highlight3, String start_date, String end_date) {
        this.label = label;
        this.company_name = company_name;
        this.title = title;
        this.highlight1 = highlight1;
        this.highlight2 = highlight2;
        this.highlight3 = highlight3;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    protected Experience(Parcel in) {
        label = in.readString();
        company_name = in.readString();
        title = in.readString();
        highlight1 = in.readString();
        highlight2 = in.readString();
        highlight3 = in.readString();
        start_date = in.readString();
        end_date = in.readString();
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHighlight1() {
        return highlight1;
    }

    public void setHighlight1(String highlight1) {
        this.highlight1 = highlight1;
    }

    public String getHighlight2() {
        return highlight2;
    }

    public void setHighlight2(String highlight2) {
        this.highlight2 = highlight2;
    }

    public String getHighlight3() {
        return highlight3;
    }

    public void setHighlight3(String highlight3) {
        this.highlight3 = highlight3;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(label);
        parcel.writeString(company_name);
        parcel.writeString(title);
        parcel.writeString(highlight1);
        parcel.writeString(highlight2);
        parcel.writeString(highlight3);
        parcel.writeString(start_date);
        parcel.writeString(end_date);
    }
}
