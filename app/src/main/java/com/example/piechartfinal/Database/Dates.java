package com.example.piechartfinal.Database;

import android.os.Bundle;
import java.util.Date;

public class Dates {

    public static final String COL_ID = "ID";
    public static final String COL_DATE_NOW = "DATE_NOW";

    private Integer mID;
    private String mDATE_NOW;

    public Dates() {
    }

    public Dates(Integer ID, String DATE_NOW) {
        this.mID = ID;
        this.mDATE_NOW = DATE_NOW;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer ID) {
        this.mID = ID;
    }

    public String getDATE_NOW() {
        return mDATE_NOW;
    }

    public void setDATE_NOW(String DATE_NOW) {
        this.mDATE_NOW = DATE_NOW;
    }


    public Bundle toBundle() { 
        Bundle b = new Bundle();
        b.putInt(COL_ID, this.mID);
        b.putString(COL_DATE_NOW, this.mDATE_NOW);
        return b;
    }

    public Dates(Bundle b) {
        if (b != null) {
            this.mID = b.getInt(COL_ID);
            this.mDATE_NOW = b.getString(COL_DATE_NOW);
        }
    }

    @Override
    public String toString() {
        return "Dates{" +
            " mID=" + mID +
            ", mDATE_NOW='" + mDATE_NOW + '\'' +
            '}';
    }


}
