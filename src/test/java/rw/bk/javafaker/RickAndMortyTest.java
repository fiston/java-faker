package rw.bk.javafaker;

import org.junit.jupiter.api.Test;

import static rw.bk.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class RickAndMortyTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.rickAndMorty().character(), matchesRegularExpression("^([\\w'-.]+ ?){2,}$"));
    }

    @Test
    public void location() {
        assertThat(faker.rickAndMorty().location(), matchesRegularExpression("^([\\w-.]+ ?){2,}$"));
    }

    @Test
    public void quote() {
        assertThat(faker.rickAndMorty().quote(), not(isEmptyOrNullString()));
    }
}
