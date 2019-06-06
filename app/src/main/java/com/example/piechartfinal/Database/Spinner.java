package com.example.piechartfinal.Database;

import android.os.Bundle;
import java.util.Date;

public class Spinner {

    public static final String COL_ID = "ID";
    public static final String COL_MOOD = "MOOD";
    public static final String COL_COUNTER = "COUNTER";
    public static final String COL_USED = "USED";
    public static final String COL_RATING = "RATING";

    private Integer mID;
    private String mMOOD;
    private Integer mCOUNTER;
    private Integer mUSED;
    private Integer mRATING;

    public Spinner() {
    }

    public Spinner(Integer ID, String MOOD, Integer COUNTER, Integer USED, Integer RATING) {
        this.mID = ID;
        this.mMOOD = MOOD;
        this.mCOUNTER = COUNTER;
        this.mUSED = USED;
        this.mRATING = RATING;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer ID) {
        this.mID = ID;
    }

    public String getMOOD() {
        return mMOOD;
    }

    public void setMOOD(String MOOD) {
        this.mMOOD = MOOD;
    }

    public Integer getCOUNTER() {
        return mCOUNTER;
    }

    public void setCOUNTER(Integer COUNTER) {
        this.mCOUNTER = COUNTER;
    }

    public Integer getUSED() {
        return mUSED;
    }

    public void setUSED(Integer USED) {
        this.mUSED = USED;
    }

    public Integer getRATING() {
        return mRATING;
    }

    public void setRATING(Integer RATING) {
        this.mRATING = RATING;
    }


    public Bundle toBundle() { 
        Bundle b = new Bundle();
        b.putInt(COL_ID, this.mID);
        b.putString(COL_MOOD, this.mMOOD);
        b.putInt(COL_COUNTER, this.mCOUNTER);
        b.putInt(COL_USED, this.mUSED);
        b.putInt(COL_RATING, this.mRATING);
        return b;
    }

    public Spinner(Bundle b) {
        if (b != null) {
            this.mID = b.getInt(COL_ID);
            this.mMOOD = b.getString(COL_MOOD);
            this.mCOUNTER = b.getInt(COL_COUNTER);
            this.mUSED = b.getInt(COL_USED);
            this.mRATING = b.getInt(COL_RATING);
        }
    }

    @Override
    public String toString() {
        return "Spinner{" +
            " mID=" + mID +
            ", mMOOD='" + mMOOD + '\'' +
            ", mCOUNTER=" + mCOUNTER +
            ", mUSED=" + mUSED +
            ", mRATING=" + mRATING +
            '}';
    }


}
