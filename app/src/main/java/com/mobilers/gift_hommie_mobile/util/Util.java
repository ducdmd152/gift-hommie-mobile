package com.mobilers.gift_hommie_mobile.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static String formatPriceVND(double price) {
        return String.format(Locale.US, "%.3fđ", price/1000);
    }
    public static boolean validatePhoneNumber(String phoneNumber) {
        String pattern = "^(\\+84|0)(3[2-9]|5[2689]|7[06789]|8[1-9]|9[0-9])(\\d{7})$";
        return phoneNumber.matches(pattern);
    }

    public static boolean validateEmail(String email) {
        String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
}
