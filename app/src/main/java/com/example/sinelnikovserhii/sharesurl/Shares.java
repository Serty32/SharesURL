package com.example.sinelnikovserhii.sharesurl;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sinelnikovserhii on 03.05.17.
 */

public class Shares implements Serializable {

    @SerializedName("Name") private String name;
    @SerializedName("Bid") private String cost;
    @SerializedName("symbol") private String index;
    @SerializedName("DividendYield") private  String dividend;
    @SerializedName("Change") private String changeCourse;
    @SerializedName("DaysLow") private String daysLow;
    @SerializedName("DaysHigh") private String daysHigh;
    @SerializedName("YearLow") private String yearLow;
    @SerializedName("YearHigh") private String yearHigh;
    @SerializedName("Open") private float open;

    public Shares(){}

    public String getIndex() {return index;}

    public String getName() {
        return  name;
    }

    public String getCost() {
        return  cost;
    }

    public String getChangeCourse() {return changeCourse;}

    public String getDaysHigh() {return daysHigh;}

    public String getDaysLow() {return daysLow;}

    public String getDividend() {return dividend;}

    public String getYearHigh() {return yearHigh;}

    public String getYearLow() {return yearLow;}

    public float getOpen() {
        return open;
    }


}
