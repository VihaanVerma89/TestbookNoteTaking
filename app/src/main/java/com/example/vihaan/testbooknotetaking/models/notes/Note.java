package com.example.vihaan.testbooknotetaking.models.notes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "notes")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int doubt, tricks, concepts;
    private String text, imageUri;
    private Long timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getDoubt() {
        return doubt;
    }

    public void setDoubt(int doubt) {
        this.doubt = doubt;
    }

    public int getTricks() {
        return tricks;
    }

    public void setTricks(int tricks) {
        this.tricks = tricks;
    }

    public int getConcepts() {
        return concepts;
    }

    public void setConcepts(int concepts) {
        this.concepts = concepts;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Note() {
        int value = 2 %10;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.doubt);
        dest.writeInt(this.tricks);
        dest.writeInt(this.concepts);
        dest.writeString(this.text);
        dest.writeString(this.imageUri);
        dest.writeValue(this.timeStamp);
    }

    protected Note(Parcel in) {
        this.id = in.readInt();
        this.doubt = in.readInt();
        this.tricks = in.readInt();
        this.concepts = in.readInt();
        this.text = in.readString();
        this.imageUri = in.readString();
        this.timeStamp = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
