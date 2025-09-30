package rw.bk.javafaker;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import rw.bk.javafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class RelationshipTest extends AbstractFakerTest {

    private Faker mockFaker;
    private RandomService mockRandomService;

    @BeforeEach
    public void before() {
        super.before();
        mockFaker = mock(Faker.class);
        mockRandomService = mock(RandomService.class);
    }

    @Test
    public void anyTest() {
        assertThat(faker.relationships().any(), not(isEmptyOrNullString()));
    }

    @Test
    public void directTest() {
        assertThat(faker.relationships().direct(), not(isEmptyOrNullString()));
    }

    @Test
    public void extendedTest() {
        assertThat(faker.relationships().extended(), not(isEmptyOrNullString()));
    }

    @Test
    public void inLawTest() {
        assertThat(faker.relationships().inLaw(), not(isEmptyOrNullString()));
    }

    @Test
    public void spouseTest() {
        assertThat(faker.relationships().spouse(), not(isEmptyOrNullString()));
    }

    @Test
    public void parentTest() {
        assertThat(faker.relationships().parent(), not(isEmptyOrNullString()));
    }

    @Test
    public void siblingTest() {
        assertThat(faker.relationships().sibling(), not(isEmptyOrNullString()));
    }

    @Test
    void anyWithIllegalArgumentExceptionThrown() {
        when(mockFaker.random()).thenReturn(mockRandomService);
        when(mockRandomService.nextInt(anyInt())).thenThrow(new IllegalArgumentException());

        assertThrows(RuntimeException.class, () -> {
            new Relationships(mockFaker).any();
        });
    }

    @Test
    void anyWithSecurityExceptionThrown() {
        when(mockFaker.random()).thenReturn(mockRandomService);
        when(mockRandomService.nextInt(anyInt())).thenThrow(new SecurityException());

        assertThrows(RuntimeException.class, () -> {
            new Relationships(mockFaker).any();
        });
    }

    @Test
    void anyWithRuntimeExceptionThrown() {
        when(mockFaker.random()).thenReturn(mockRandomService);
        when(mockRandomService.nextInt(anyInt())).thenThrow(new RuntimeException("Test exception"));

        assertThrows(RuntimeException.class, () -> {
            new Relationships(mockFaker).any();
        });
    }
}