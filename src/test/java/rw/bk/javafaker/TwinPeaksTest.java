package rw.bk.javafaker;

import org.junit.jupiter.api.Test;

import static rw.bk.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TwinPeaksTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.twinPeaks().character(), matchesRegularExpression("^([\\w']+ ?){2,}$"));
    }

    @Test
    public void location() {
        assertThat(faker.twinPeaks().location(), matchesRegularExpression("^[A-Za-z0-9'&,\\- ]+$"));
    }

    @Test
    public void quote() {
        assertThat(faker.twinPeaks().quote(), not(isEmptyOrNullString()));
    }
}
