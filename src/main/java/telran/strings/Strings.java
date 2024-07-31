package telran.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {
    static final String keyWords[] = { "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while" };

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
        return "[A-Za-z$]|[A-Z-a-z$_][\\w\\d$]+";
    }

    public static String number0_300() {
        return "[1-9]\\d?|[1-2]\\d\\d|300|0";
    }

    public static String ipV4octet() {
        return "([0-1]?\\d{1,2}|2([0-4]\\d|5[0-5]))";
    }

    public static String ipV4Address() {
        String octet = ipV4octet();
        return String.format("%s(\\.%s){3}", octet, octet);
    }

    public static String stringWithJavaNames(String names) {
        String[] tokens = names.split("\\s+");
        int i = getJavaNameIndex(tokens, -1);
        String res = "";
        if (i >= 0) {
            res = tokens[i];
        }
        while ((i = getJavaNameIndex(tokens, i)) > 0) {
            res += " " + tokens[i];
        }
        ;
        return res;
    }

    private static int getJavaNameIndex(String[] tokens, int i) {
        i++;
        while (i < tokens.length && !isJavaName(tokens[i])) {
            i++;
        }
        return i < tokens.length ? i : -1;
    }

    private static boolean isJavaName(String string) {
        return string.matches(javaVariable()) && Arrays.binarySearch(keyWords, string) < 0;
    }

    public static boolean isArithmeticExpression(String someExpression) {
        someExpression = lowExprDepth(someExpression);
        boolean arithmeticFlag = false;
        while (someExpression != null && !someExpression.matches("^\\(someExpr$|^someExpr\\)$")
                && !arithmeticFlag && !someExpression.isEmpty()) {
            if (someExpression.matches("^someExpr$")) {
                arithmeticFlag = true;
            } else {
                someExpression = lowExprDepth(someExpression);
            }
        }
        return arithmeticFlag;
    }

    public static String lowExprDepth(String expression) {
        String group1 = "[^\\(\\)\\+\\-\\/\\*]+";
        String group2 = "[\\+\\-\\/\\*]";
        String regex = String.format("(\\(%1$s(%2$s%1$s)+\\))|(%1$s(%2$s%1$s)+)|(\\(%1$s\\))(?!$)|(?!^)(\\(%1$s\\))",
                group1, group2);
        expression = expression.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        String expressionOut = "";
        while (matcher.find() && expression != null) {
            if (isFoundGroup(matcher.group())) {
                expressionOut = expression.replace(matcher.group(), "someExpr");
            } else {
                expressionOut = null;
            }
        }
        return expressionOut;
    }

    public static Boolean isFoundGroup(String string) {
        string = string.replaceAll("\\(|\\)", "");
        String[] groupElements = string.split("\\+|\\-|\\/|\\*");
        boolean elementFlag = true;
        int i = 0;
        while (i < groupElements.length && elementFlag) {
            if (!groupElements[i].matches("^\\d+$")) {
                elementFlag = isJavaName(groupElements[i]);
            }
            i++;
        }
        return elementFlag;
    }
}
