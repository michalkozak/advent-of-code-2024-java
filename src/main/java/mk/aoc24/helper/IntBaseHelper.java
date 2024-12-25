package mk.aoc24.helper;

public class IntBaseHelper {

    private static final char[] DIGITS = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    private IntBaseHelper() {
    }

    public static String convertIntToBase(int no, int base, int length) {
        String intBaseString = convertIntToBase(no, base);
        return "0".repeat(length - intBaseString.length()) + intBaseString;
    }

    public static String convertIntToBase(int no, int base) {
        final StringBuilder builder = new StringBuilder();
        do {
            builder.append(DIGITS[no % base]);
            no /= base;
        } while (no > 0);
        return builder.reverse().toString();
    }

}
