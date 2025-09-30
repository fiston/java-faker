package rw.bk.javafaker;

import org.junit.jupiter.api.Test;

import static rw.bk.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class HipsterTest extends AbstractFakerTest {

    @Test
    public void word() {
        assertThat(faker.hipster().word(), matchesRegularExpression("^([\\w-+&']+ ?)+$"));
    }
}
