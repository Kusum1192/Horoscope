package com.horoscopes.android.Model;

public class ZodiacModel {
String ZodiacSign;
String ZodiacName;
String ZodiacContent;
int id;



    public ZodiacModel(String zodiacSign, String zodiacName, int id, String zodiacContent ) {
        this.ZodiacSign = zodiacSign;
        this.ZodiacName = zodiacName;
        this.ZodiacContent = zodiacContent;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZodiacSign() {
        return ZodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        ZodiacSign = zodiacSign;
    }

    public String getZodiacName() {
        return ZodiacName;
    }

    public void setZodiacName(String zodiacName) {
        ZodiacName = zodiacName;
    }
    public String getZodiacContent() {
        return ZodiacContent;
    }

    public void setZodiacContent(String zodiacContent) {
        ZodiacContent = zodiacContent;
    }
}
