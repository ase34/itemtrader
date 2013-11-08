package de.ase34.itemtrader.util;

public class NumberUtils {

    public static boolean isInRange(int min, int number, int max) {
        return min <= number && max >= number;
    }

    public static int greatestCommonDivisor(int a, int b) {
        if (b == 0)
            return a;
        else
            return greatestCommonDivisor(b, a % b);
    }

}
