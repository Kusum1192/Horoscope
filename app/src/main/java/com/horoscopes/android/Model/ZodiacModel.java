package com.horoscopes.android.Model;

public class ZodiacModel {
int ZodiacSign;
String ZodiacName;
String ZodiacContent;
int id;



    public ZodiacModel(int zodiacSign, String zodiacName, int id, String zodiacContent ) {
        ZodiacSign = zodiacSign;
        ZodiacName = zodiacName;
        ZodiacContent = zodiacContent;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZodiacSign() {
        return ZodiacSign;
    }

    public void setZodiacSign(int zodiacSign) {
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
