
package com.example.vihaan.testbooknotetaking.models.questions;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question implements Parcelable {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;
    @SerializedName("solution")
    @Expose
    private Solution solution;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.img);
        dest.writeString(this.tags);
        dest.writeList(this.options);
        dest.writeParcelable(this.solution, flags);
    }

    public Question() {
    }

    protected Question(Parcel in) {
        this.text = in.readString();
        this.img = in.readString();
        this.tags = in.readString();
        this.options = new ArrayList<Option>();
        in.readList(this.options, Option.class.getClassLoader());
        this.solution = in.readParcelable(Solution.class.getClassLoader());
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
