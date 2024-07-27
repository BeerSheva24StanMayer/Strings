package telran.strings;

import java.util.regex.Pattern;

public class Strings {
    public static String firstName() {
        // regex for strings startin with
        // capital letter and rest as a
        // lower case letters
        // minimal length is 5 letters
        return "[A-Z][a-z]{4,}";
    }

    public static String javaVariable() {
        // TODO
        // regular expression for testing synthax
        // of Java variable names
        return "^((_|\\$)+[A-Za-z0-9]+)|^((?!.*^int$|.*^float$|.*^String$)([A-Za-z]+((\\$|\\w)+)?))";
    }
}
