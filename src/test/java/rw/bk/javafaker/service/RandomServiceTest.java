package rw.bk.javafaker.service;

import rw.bk.javafaker.AbstractFakerTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pmiklos
 */

public class RandomServiceTest extends AbstractFakerTest {

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("default random", new RandomService()),
                Arguments.of("seeded random", new RandomService(new Random(1234L))),
                Arguments.of("null random (uses default)", new RandomService(null))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void testDefaultHex(String description, RandomService randomService) {
        String hex = randomService.hex();
        assertNotNull(hex);
        assertEquals(8, hex.length());
        assertTrue(hex.matches("[0-9A-F]+"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void testHex(String description, RandomService randomService) {
        String hex = randomService.hex(16);
        assertNotNull(hex);
        assertEquals(16, hex.length());
        assertTrue(hex.matches("[0-9A-F]+"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void testIntInRange(String description, RandomService randomService) {
        int result = randomService.nextInt(5, 10);
        assertTrue(result >= 5 && result <= 10);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void testLongMaxBoundary(String description, RandomService randomService) {
        long result = randomService.nextLong(Long.MAX_VALUE);
        assertTrue(result >= 0 && result < Long.MAX_VALUE);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void testLongWithinBoundary(String description, RandomService randomService) {
        long result = randomService.nextLong(100);
        assertTrue(result >= 0 && result < 100);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void testPositiveBoundariesOnly(String description, RandomService randomService) {
        assertThrows(IllegalArgumentException.class, () -> {
            randomService.nextLong(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            randomService.nextLong(-1);
        });
    }

    @Test
    public void testHexWithZeroLength() {
        RandomService randomService = new RandomService();
        String hex = randomService.hex(0);
        assertEquals("", hex);
    }

    @Test
    public void testHexWithNegativeLength() {
        RandomService randomService = new RandomService();
        String hex = randomService.hex(-5);
        assertEquals("", hex);
    }

    @Test
    public void testNextInt() {
        RandomService randomService = new RandomService();
        int result = randomService.nextInt(10);
        assertTrue(result >= 0 && result < 10);
    }

    @Test
    public void testNextLong() {
        RandomService randomService = new RandomService();
        long result = randomService.nextLong();
        assertNotNull(result);
    }

    @Test
    public void testNextDouble() {
        RandomService randomService = new RandomService();
        double result = randomService.nextDouble();
        assertTrue(result >= 0.0 && result < 1.0);
    }

    @Test
    public void testNextBoolean() {
        RandomService randomService = new RandomService();
        Boolean result = randomService.nextBoolean();
        assertNotNull(result);
    }
}
