package com.dexwin.currencyconverter.Utils;

public class AppUtils {

    /**
     * This method rounds a given double value to 2 decimal places.
     *
     * @param value The double value to be rounded.
     * @return The rounded value to 2 decimal places.
     */
    public static Double convertDoubleTo2dp(Double value) {
        if (value == null) {
            throw new IllegalArgumentException("Input value cannot be null");
        }
        return Math.round(value * 100.0) / 100.0;
    }

}
