
package com.example.vihaan.testbooknotetaking.models.questions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option implements Parcelable {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("state")
    @Expose
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.img);
        dest.writeString(this.state);
    }

    public Option() {
    }

    protected Option(Parcel in) {
        this.text = in.readString();
        this.img = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel source) {
            return new Option(source);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
}
