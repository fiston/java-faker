package rw.bk.javafaker;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class MountainTest extends AbstractFakerTest {
    @Test
    public void testMountainName() {
        String mountainName = faker.mountain().name();
        assertThat(mountainName, not(isEmptyOrNullString()));
    }

    @Test
    public void testMountainLeague() {
        String mountainLeague = faker.mountain().range();
        assertThat(mountainLeague, not(isEmptyOrNullString()));
    }
}
