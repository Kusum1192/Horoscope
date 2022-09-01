package com.horoscopes.android.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppOpen {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private long status;
    @SerializedName("forceUpdate")
    @Expose
    private boolean forceUpdate;
    @SerializedName("appUrl")
    @Expose
    private String appUrl;
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

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
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
}
