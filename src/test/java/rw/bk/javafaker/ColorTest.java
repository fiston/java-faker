package rw.bk.javafaker;

import org.junit.jupiter.api.Test;

import static rw.bk.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class ColorTest extends AbstractFakerTest {

    @Test
    public void testName() {
        assertThat(faker.color().name(), matchesRegularExpression("(\\w+ ?){1,2}"));
    }

    @Test
    public void testHex() {
        assertThat(faker.color().hex(), matchesRegularExpression("^#[0-9A-F]{6}$"));
    }

    @Test
    public void testHexNoHashSign() {
        assertThat(faker.color().hex(false), matchesRegularExpression("^[0-9A-F]{6}$"));
    }
}
