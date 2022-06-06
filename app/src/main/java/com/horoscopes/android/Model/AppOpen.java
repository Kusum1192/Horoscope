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
}
