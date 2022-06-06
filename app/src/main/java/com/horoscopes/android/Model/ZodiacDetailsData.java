package com.horoscopes.android.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZodiacDetailsData {
    @SerializedName("date_range")
    @Expose
    private String dateRange;
    @SerializedName("current_date")
    @Expose
    private String currentDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("compatibility")
    @Expose
    private String compatibility;
    @SerializedName("mood")
    @Expose
    private String mood;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("lucky_number")
    @Expose
    private String luckyNumber;
    @SerializedName("lucky_time")
    @Expose
    private String luckyTime;
    public String getDateRange() {return dateRange;}
    public void setDateRange(String dateRange) {this.dateRange = dateRange;}

    public String getCurrentDate() {return currentDate;}
    public void setCurrentDate(String currentDate) {this.currentDate = currentDate;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getCompatibility() {return compatibility;}
    public void setCompatibility(String compatibility) {this.compatibility = compatibility; }

    public String getMood() {return mood; }
    public void setMood(String mood) {this.mood = mood;}
    public String getColor() {return color; }
    public void setColor(String color) {
        this.color = color;
    }
    public String getLuckyNumber() {
        return luckyNumber;
    }
    public void setLuckyNumber(String luckyNumber) {
        this.luckyNumber = luckyNumber;
    }
    public String getLuckyTime() {
        return luckyTime;
    }
    public void setLuckyTime(String luckyTime) {
        this.luckyTime = luckyTime;
    }
}