import models.Second;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecondTest {

    public static int square(int n) {
        return n * n;
    }
    public static int cube(int n) {
        return n * n * n;
    }
    public static int divide(int n) {
        return n / n;
    }
    @Test
    public void squareTest() {
        assertEquals(25, square(5));
    }

    @Test
    public void cubeTest() {
        assertEquals(27, cube(3));
    }

    @Test
    public void divideTest() {
        assertEquals(1, divide(4));
    }

    @Test
    public void Second() {
        Second testSecond = new Second("Kamau", "30", "Good");
        assertEquals("Kamau", testSecond.getName());
    }
    @Test
    public void Third() {
        Second testSecond = new Second("Kamau", "30", "Good");
        assertEquals("30", testSecond.getSecond());

    }
    @Test
    public void anotherOne() {
        Second testSecond = new Second("Kamau", "30", "Good");
        assertEquals("Good", testSecond.getStatus());
    }

}
