
package com.example.vihaan.testbooknotetaking.models.questions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Solution implements Parcelable {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("img")
    @Expose
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.img);
    }

    public Solution() {
    }

    protected Solution(Parcel in) {
        this.text = in.readString();
        this.img = in.readString();
    }

    public static final Parcelable.Creator<Solution> CREATOR = new Parcelable.Creator<Solution>() {
        @Override
        public Solution createFromParcel(Parcel source) {
            return new Solution(source);
        }

        @Override
        public Solution[] newArray(int size) {
            return new Solution[size];
        }
    };
}
