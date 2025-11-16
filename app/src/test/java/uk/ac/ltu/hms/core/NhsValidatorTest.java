package uk.ac.ltu.hms.core;

import org.junit.Test;
import static org.junit.Assert.*;

import uk.ac.ltu.hms.NhsValidator;

public class NhsValidatorTest {
    private NhsValidator NhsValidator;

    @Test public void validExamplePasses() {
        assertTrue(NhsValidator.isValid("9434765919"));
    }
    @Test public void wrongCheckDigitFails() {
        assertFalse(NhsValidator.isValid("9434765910"));
    }
    @Test public void invalidInputsFail() {
        assertFalse(NhsValidator.isValid(null));
        assertFalse(NhsValidator.isValid("123"));
    }
}
