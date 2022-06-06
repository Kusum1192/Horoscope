package com.horoscopes.android.Model;

public class TarotCardModel {
    int TarotCardIcon;
    String TarotCardName;


    public TarotCardModel(int tarotCardIcon, String tarotCardName) {
        TarotCardIcon = tarotCardIcon;
        TarotCardName = tarotCardName;

    }

    public int getTarotCardIcon() {
        return TarotCardIcon;
    }

    public void setTarotCardIcon(int tarotCardIcon) {
        TarotCardIcon = tarotCardIcon;
    }

    public String getTarotCardName() {
        return TarotCardName;
    }

    public void setTarotCardName(String tarotCardName) {
        TarotCardName = tarotCardName;
    }


}
