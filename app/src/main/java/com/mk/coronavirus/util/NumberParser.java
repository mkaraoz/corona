package com.mk.coronavirus.util;

import android.util.Log;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberParser {
    public static int toInt(String number) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number n = format.parse(number);
            return n.intValue();
        } catch (ParseException e) {
            Log.e("_MK", e.getMessage(), e);
        }
        return 0;
    }
}
