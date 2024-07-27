package telran.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.strings.Strings.*;

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


        assertFalse("___".matches(javaVariable()));       
        assertFalse("{___}".matches(javaVariable()));
        assertFalse("varname[_".matches(javaVariable()));
        assertFalse("|".matches(javaVariable()));
        assertFalse("1".matches(javaVariable()));
        assertFalse("1A".matches(javaVariable()));
        assertFalse("".matches(javaVariable()));
        assertFalse("__||".matches(javaVariable()));
        assertFalse("float".matches(javaVariable()));
        assertFalse("int".matches(javaVariable()));
        assertFalse("String".matches(javaVariable()));

    }
}
