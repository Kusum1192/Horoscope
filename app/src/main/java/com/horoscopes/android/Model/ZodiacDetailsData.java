package com.horoscopes.android.Model; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ZodiacDetailsData {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private long status;
    @SerializedName("yesterday")
    @Expose
    private Yesterday yesterday;
    @SerializedName("today")
    @Expose
    private Today today;
    @SerializedName("tomorrow")
    @Expose
    private Tomorrow tomorrow;
    @SerializedName("customAd")
    @Expose
    private CustomAd customAd;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Tomorrow getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(Tomorrow tomorrow) {
        this.tomorrow = tomorrow;
    }

    public CustomAd getCustomAd() {
        return customAd;
    }

    public void setCustomAd(CustomAd customAd) {
        this.customAd = customAd;
    }
    public class CustomAd {

        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("action_url")
        @Expose
        private String actionUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

    }
    public class Today {

        @SerializedName("luckyTime")
        @Expose
        private String luckyTime;
        @SerializedName("luckyNumber")
        @Expose
        private String luckyNumber;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("mood")
        @Expose
        private String mood;
        @SerializedName("description")
        @Expose
        private String description;

        public String getLuckyTime() {
            return luckyTime;
        }

        public void setLuckyTime(String luckyTime) {
            this.luckyTime = luckyTime;
        }

        public String getLuckyNumber() {
            return luckyNumber;
        }

        public void setLuckyNumber(String luckyNumber) {
            this.luckyNumber = luckyNumber;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
    public class Tomorrow {

        @SerializedName("luckyTime")
        @Expose
        private String luckyTime;
        @SerializedName("luckyNumber")
        @Expose
        private String luckyNumber;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("mood")
        @Expose
        private String mood;
        @SerializedName("description")
        @Expose
        private String description;

        public String getLuckyTime() {
            return luckyTime;
        }

        public void setLuckyTime(String luckyTime) {
            this.luckyTime = luckyTime;
        }

        public String getLuckyNumber() {
            return luckyNumber;
        }

        public void setLuckyNumber(String luckyNumber) {
            this.luckyNumber = luckyNumber;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
    public class Yesterday {

        @SerializedName("luckyTime")
        @Expose
        private String luckyTime;
        @SerializedName("luckyNumber")
        @Expose
        private String luckyNumber;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("mood")
        @Expose
        private String mood;
        @SerializedName("description")
        @Expose
        private String description;

        public String getLuckyTime() {
            return luckyTime;
        }

        public void setLuckyTime(String luckyTime) {
            this.luckyTime = luckyTime;
        }

        public String getLuckyNumber() {
            return luckyNumber;
        }

        public void setLuckyNumber(String luckyNumber) {
            this.luckyNumber = luckyNumber;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}