package telran.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.strings.Strings.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class RegularExpressionsTests {
    @Test
    void javaVariableTest() {
        // TODO
        assertTrue("varname".matches(javaVariable()));
        assertTrue("varName".matches(javaVariable()));
        assertTrue("$varname".matches(javaVariable()));
        assertTrue("_varname".matches(javaVariable()));
        assertTrue("$$$__varname".matches(javaVariable()));
        assertTrue("var_Name".matches(javaVariable()));
        assertTrue("varnamE".matches(javaVariable()));
        assertTrue("var_n_ame".matches(javaVariable()));
        assertTrue("varname_$".matches(javaVariable()));
        assertTrue("varNAme".matches(javaVariable()));
        assertTrue("A".matches(javaVariable()));
        assertTrue("A1".matches(javaVariable()));
        assertTrue("_$$$$A1".matches(javaVariable()));
        assertTrue("floattt".matches(javaVariable()));
        assertTrue("int12".matches(javaVariable()));
        assertTrue("String_".matches(javaVariable()));
        assertTrue("___".matches(javaVariable()));
        assertTrue("___123".matches(javaVariable()));
        assertTrue("$$$$".matches(javaVariable()));

        assertFalse("{___}".matches(javaVariable()));
        assertFalse("varname[_".matches(javaVariable()));
        assertFalse("|".matches(javaVariable()));
        assertFalse("1".matches(javaVariable()));
        assertFalse("1A".matches(javaVariable()));
        assertFalse("".matches(javaVariable()));
        assertFalse("__||".matches(javaVariable()));
        assertFalse("_".matches(javaVariable()));
    }
    @Test
    void number0_300TestTrue() {      
        String regex = Strings.number0_300();
        assertTrue("0".matches(regex));
        assertTrue("300".matches(regex));
        assertTrue("250".matches(regex));
        assertTrue("25".matches(regex));
        assertTrue("12".matches(regex));
        assertTrue("299".matches(regex));
        assertTrue("199".matches(regex));
        assertTrue("111".matches(regex));
    }
    void number0_300TestFalse() {      
        String regex = Strings.number0_300();
        assertFalse("00".matches(regex));
        assertFalse("301".matches(regex));
        assertFalse("01".matches(regex));
        assertFalse("1(".matches(regex));
        assertFalse("100".matches(regex));
        assertFalse("20".matches(regex));
        assertFalse("100".matches(regex));
        assertFalse("410".matches(regex));

    }
    @Test
    void ipV4octetTrueTest() {
        String regex = Strings.ipV4octet();
        assertTrue("0".matches(regex));
        assertTrue("000".matches(regex));
        assertTrue("10".matches(regex));
        assertTrue("100".matches(regex));
        assertTrue("255".matches(regex));
        assertTrue("199".matches(regex));
        assertTrue("001".matches(regex));

    }
    @Test
    void ipV4octetFalseTest() {
        String regex = Strings.ipV4octet();
        assertFalse("256".matches(regex));
        assertFalse("1000".matches(regex));
        assertFalse("12bla".matches(regex));
        assertFalse(".234".matches(regex));
        assertFalse("255.".matches(regex));
        assertFalse("-1".matches(regex));
        assertFalse("0001".matches(regex));
        assertFalse(" ".matches(regex));
    }
    @Test
    void ipV4AddressTrueTest() {
        String regex = Strings.ipV4Address();
        assertTrue("0.0.0.0".matches(regex));
        assertTrue("100.100.100.100".matches(regex));
        assertTrue("255.255.255.255".matches(regex));
    }
    @Test
    void ipV4AddressFalseTest() {
        String regex = Strings.ipV4Address();
        assertFalse("0.0.0,0".matches(regex));
        assertFalse("1.1.1".matches(regex));
        assertFalse("23.23.23. ".matches(regex));
        assertFalse("1234.50.18.18".matches(regex));
        assertFalse("0".matches(regex));
        assertFalse("0._.0.0".matches(regex));
        assertFalse("0._.0 0".matches(regex));
    }
    @Test
    void stringWithJavaNamesTest() {
        String names = "123  1a   abs int     enum null lmn";
        String expected = "abs lmn";
        assertEquals(expected, Strings.stringWithJavaNames(names));
    }
    @Test
    void isArithmeticExpressionTest() {
        assertTrue(isArithmeticExpression("12+111"));
        assertTrue(isArithmeticExpression("12   - 10"));
        assertTrue(isArithmeticExpression("(64) /     11"));
        assertTrue(isArithmeticExpression("(45) *(18)"));
        assertTrue(isArithmeticExpression("10 - expression"));
        assertTrue(isArithmeticExpression("expression  +  (other)"));
        assertTrue(isArithmeticExpression("(34 - (expression))"));
        assertTrue(isArithmeticExpression("(int1 - int2/(int3))*89"));
        assertTrue(isArithmeticExpression("__56$ + 16      / (11) * $18 * float12$3"));
        assertTrue(isArithmeticExpression("(6) - 8 + int56"));
        assertTrue(isArithmeticExpression("3 / unb17 + (_3)"));
        assertTrue(isArithmeticExpression("(((1150+5)/(3*(inn-(aaaa)  +45/345)))    *(a434 -a643434* __ -  45))"));

        assertFalse(isArithmeticExpression(""));
        assertFalse(isArithmeticExpression("(_4 - /+expression)"));
        assertFalse(isArithmeticExpression("(4)"));
        assertFalse(isArithmeticExpression("4"));
        assertFalse(isArithmeticExpression("assembly"));
        assertFalse(isArithmeticExpression("asdfdf + 1_"));
        assertFalse(isArithmeticExpression("\"(((1150+5)/(3*(inn-(25aa)  +45/345)))    *(a434 -a64mnmn4* __ -  4_5))\""));
        assertFalse(isArithmeticExpression("(((1150+5)/(3*(.....-(aaaa)  +45/345)))    *(a434 -a643434* __ -  45))"));
        assertFalse(isArithmeticExpression("sdsint"));
        assertFalse(isArithmeticExpression("(6-uri))"));
        assertFalse(isArithmeticExpression(":';;s___"));
        
    }
}
