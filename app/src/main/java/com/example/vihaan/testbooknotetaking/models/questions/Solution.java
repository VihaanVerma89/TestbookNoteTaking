
package com.example.vihaan.testbooknotetaking.models.questions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Solution {

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

}
