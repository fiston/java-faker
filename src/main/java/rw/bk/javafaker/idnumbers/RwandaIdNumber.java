package rw.bk.javafaker.idnumbers;

import rw.bk.javafaker.Faker;

import java.util.regex.Pattern;

/**
 * Implementation based on the definition and the description
 * given by Rwanda National ID Agency (NIDA)
 */
public class RwandaIdNumber {
    private static final String NID_16_DIGITS = "^[1-3](19|20)\\d{2}(7|8)\\d{10}$";
    private static final Pattern NID_PATTERN = Pattern.compile(NID_16_DIGITS);

    // Valid century prefixes for generating test data
    private static final String[] ID_TYPE_PREFIXES = {"1", "2", "3"};
    private static final String[] CENTURIES = {"19", "20"};
    private static final String[] GENDER_PREFIXES = {"7", "8"};

    public String getValidNID(Faker f) {
        String candidate = "";
        while (!validRwandanNID(candidate)) {
            candidate = generateCandidate(f);
        }
        return candidate;
    }

    public String getInvalidNID(Faker f) {
        String candidate = "11920870123456789"; // Seed with a valid pattern
        while (validRwandanNID(candidate)) {
            // Generate random 16 digits that may not match the pattern
            candidate = f.numerify("################");
        }
        return candidate;
    }

    private String generateCandidate(Faker f) {

        String idTypePrefix = ID_TYPE_PREFIXES[f.random().nextInt(3)];
        String century = CENTURIES[f.random().nextInt(2)];
        String year = f.numerify("##");
        String monthPrefix = GENDER_PREFIXES[f.random().nextInt(2)];
        String remaining = f.numerify("##########");

        return idTypePrefix + century + year + monthPrefix + remaining;
    }

    boolean validRwandanNID(String nid) {
        if (nid == null || nid.isEmpty()) {
            return false;
        }

        return NID_PATTERN.matcher(nid).matches();
    }
}

