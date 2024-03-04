package com.mobilers.gift_hommie_mobile.util;

import java.util.Locale;

public class Util {
    public static String formatPriceVND(double price) {
        return String.format(Locale.US, "%.3fđ", price/1000);
    }
}
