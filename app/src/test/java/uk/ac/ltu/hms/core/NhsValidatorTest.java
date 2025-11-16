package uk.ac.ltu.hms.core;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

import uk.ac.ltu.hms.NhsValidator;

public class NhsValidatorTest {
    @Test public void validExamplePasses() {
        assertTrue(NhsValidator.isValid("9434765919"));
    }
    @Test public void wrongCheckDigitFails() {
        assertFalse(NhsValidator.isValid("9434765910")); // flipped last digit
    }
    @Test public void invalidInputsFail() {
        assertFalse(NhsValidator.isValid(null));
        assertFalse(NhsValidator.isValid("123"));
    }
}
