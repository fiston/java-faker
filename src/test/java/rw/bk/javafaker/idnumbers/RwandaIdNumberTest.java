package rw.bk.javafaker.idnumbers;

import rw.bk.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class RwandaIdNumberTest {

    private Faker faker;
    private RwandaIdNumber rwandaIdNumber;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        rwandaIdNumber = new RwandaIdNumber();
    }

    @Test
    public void testGetValidNID_shouldReturnValidFormat() {
        String nid = rwandaIdNumber.getValidNID(faker);

        assertNotNull(nid);
        assertEquals(16, nid.length());
        assertTrue(rwandaIdNumber.validRwandanNID(nid),
                "Generated NID should be valid: " + nid);
    }

    @Test
    public void testGetValidNID_shouldStartWithValidPrefix() {
        String nid = rwandaIdNumber.getValidNID(faker);

        String firstChar = nid.substring(0, 1);
        assertThat(firstChar, isOneOf("1", "2", "3"));
    }

    @Test
    public void testGetValidNID_shouldHaveValidCentury() {
        String nid = rwandaIdNumber.getValidNID(faker);

        String century = nid.substring(1, 3);
        assertThat(century, isOneOf("19", "20"));
    }

    @Test
    public void testGetValidNID_shouldHaveValidGenderPrefix() {
        String nid = rwandaIdNumber.getValidNID(faker);

        String genderPrefix = nid.substring(5, 6);
        assertThat(genderPrefix, isOneOf("7", "8"));
    }

    @Test
    public void testGetValidNID_multipleGeneration_allValid() {
        for (int i = 0; i < 100; i++) {
            String nid = rwandaIdNumber.getValidNID(faker);
            assertTrue(rwandaIdNumber.validRwandanNID(nid),
                    "Generated NID should be valid: " + nid);
        }
    }

    @Test
    public void testGetInvalidNID_shouldReturnInvalidFormat() {
        String nid = rwandaIdNumber.getInvalidNID(faker);

        assertNotNull(nid);
        assertFalse(rwandaIdNumber.validRwandanNID(nid),
                "Generated NID should be invalid: " + nid);
    }

    @Test
    public void testGetInvalidNID_multipleGeneration_allInvalid() {
        for (int i = 0; i < 50; i++) {
            String nid = rwandaIdNumber.getInvalidNID(faker);
            assertFalse(rwandaIdNumber.validRwandanNID(nid),
                    "Generated NID should be invalid: " + nid);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1197071234567890",  // Valid: starts with 1, 1970s, gender 7
            "1198780123456789",  // Valid: starts with 1, 1987, gender 8
            "2200571234567890",  // Valid: starts with 2, 2005, gender 7
            "2202380123456789",  // Valid: starts with 2, 2023, gender 8
            "3195271234567890",  // Valid: starts with 3, 1952, gender 7
            "3201980123456789"   // Valid: starts with 3, 2019, gender 8
    })
    public void testValidRwandanNID_validPatterns_shouldReturnTrue(String nid) {
        assertTrue(rwandaIdNumber.validRwandanNID(nid),
                "NID should be valid: " + nid);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0197071234567890",  // Invalid: starts with 0
            "4197071234567890",  // Invalid: starts with 4
            "1187071234567890",  // Invalid: century is 18
            "1217071234567890",  // Invalid: century is 21
            "1197091234567890",  // Invalid: gender prefix is 9
            "1197061234567890",  // Invalid: gender prefix is 6
            "119707123456789",   // Invalid: too short (15 digits)
            "11970712345678901", // Invalid: too long (17 digits)
            "119707123456789a",  // Invalid: contains letter
            "1197-71234567890"   // Invalid: contains hyphen
    })
    public void testValidRwandanNID_invalidPatterns_shouldReturnFalse(String nid) {
        assertFalse(rwandaIdNumber.validRwandanNID(nid),
                "NID should be invalid: " + nid);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testValidRwandanNID_nullOrEmpty_shouldReturnFalse(String nid) {
        assertFalse(rwandaIdNumber.validRwandanNID(nid));
    }

    @Test
    public void testValidRwandanNID_allIdTypePrefixes_shouldBeValid() {
        String[] testNIDs = {
                "1197071234567890",  // Type 1
                "2197071234567890",  // Type 2
                "3197071234567890"   // Type 3
        };

        for (String nid : testNIDs) {
            assertTrue(rwandaIdNumber.validRwandanNID(nid),
                    "NID with prefix should be valid: " + nid);
        }
    }

    @Test
    public void testValidRwandanNID_allCenturies_shouldBeValid() {
        String[] testNIDs = {
                "1197071234567890",  // 19xx
                "1207071234567890"   // 20xx
        };

        for (String nid : testNIDs) {
            assertTrue(rwandaIdNumber.validRwandanNID(nid),
                    "NID with century should be valid: " + nid);
        }
    }

    @Test
    public void testValidRwandanNID_allGenderPrefixes_shouldBeValid() {
        String[] testNIDs = {
                "1197071234567890",  // Gender 7 (male)
                "1197081234567890"   // Gender 8 (female)
        };

        for (String nid : testNIDs) {
            assertTrue(rwandaIdNumber.validRwandanNID(nid),
                    "NID with gender prefix should be valid: " + nid);
        }
    }

    @Test
    public void testValidRwandanNID_edgeCaseYears_shouldBeValid() {
        String[] testNIDs = {
                "1190071234567890",  // Year 00
                "1199971234567890",  // Year 99
                "2200071234567890",  // Year 00
                "2209971234567890"   // Year 99
        };

        for (String nid : testNIDs) {
            assertTrue(rwandaIdNumber.validRwandanNID(nid),
                    "NID with edge case year should be valid: " + nid);
        }
    }

    @Test
    public void testPattern_format_shouldMatch16Digits() {
        // Format: [1-3][19|20][YY][7|8][10 digits]
        String validNID = "1197071234567890";

        assertEquals('1', validNID.charAt(0));  // ID type prefix
        assertEquals("19", validNID.substring(1, 3));  // Century
        assertEquals("70", validNID.substring(3, 5));  // Year
        assertEquals('7', validNID.charAt(5));  // Gender prefix
        assertEquals(10, validNID.substring(6).length());  // Remaining digits
    }

    @Test
    public void testGetValidNID_withSeededFaker_shouldBeConsistent() {
        Faker seededFaker = new Faker(new java.util.Random(12345L));
        RwandaIdNumber idNumber = new RwandaIdNumber();

        String nid1 = idNumber.getValidNID(seededFaker);

        seededFaker = new Faker(new java.util.Random(12345L));
        String nid2 = idNumber.getValidNID(seededFaker);

        assertEquals(nid1, nid2, "Same seed should generate same NID");
    }

    @Test
    public void testGetValidNID_uniqueness_shouldGenerateDifferentNIDs() {
        java.util.Set<String> generatedNIDs = new java.util.HashSet<>();

        for (int i = 0; i < 1000; i++) {
            String nid = rwandaIdNumber.getValidNID(faker);
            generatedNIDs.add(nid);
        }

        // Should have high uniqueness (allow some duplicates due to randomness)
        assertThat(generatedNIDs.size(), greaterThan(950));
    }
}