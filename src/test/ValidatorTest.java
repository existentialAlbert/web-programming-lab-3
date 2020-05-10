package test;

import model.Point;
import model.Validator;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ValidatorTest {
    private Point p;

    private boolean checkPoint(double x, double y, double r) {
        p = new Point(x, y, r, -180, "test");
        return (Validator.check(p));
    }

    private boolean checkLongValuedPoint(String x, String y, String r) {
        p = new Point(x, y, r, -180, "test");
        return Validator.bigNumbersCheck(p, new BigDecimal(x), new BigDecimal(y), new BigDecimal(r));
    }

    /**
     * Проверка попадания в 1 четверть для длинных чисел
     */
    @Test
    public void bigNumbersFirstQuarterCheck() {
        assertFalse(checkLongValuedPoint("3.000000000000000000000000000000000001", "0", "3"));
        assertFalse(checkLongValuedPoint("0", "3.0000000000000000000000000000000000000001", "3"));
        assertTrue(checkLongValuedPoint("1", "1", "" + Math.sqrt(2)));
        assertFalse(checkLongValuedPoint("1", "1", "" + (Math.sqrt(2 - 0.000000001) - 0.000000001)));
    }

    /**
     * Проверка попадания во 2 четверть для длинных чисел
     */
    @Test
    public void bigNumbersSecondQuarterCheck() {
        assertFalse(checkLongValuedPoint("-1.5000000000000000000000000000000000001", "1.5", "3"));
        assertTrue(checkLongValuedPoint("-1.4999999999999999999999999999999999999", "1.5", "3"));
        assertTrue(checkLongValuedPoint("-1", "1", "3"));
        assertTrue(checkLongValuedPoint("-1", "1", "3"));

    }

    /**
     * Проверка попадания в 3 четверть для длинных чисел
     */
    @Test
    public void bigNumbersThirdQuarterCheck() {
        assertTrue(checkLongValuedPoint("0", "-2", "2"));
        assertTrue(checkLongValuedPoint("-1", "-3", "3"));
        assertFalse(checkLongValuedPoint("-1.5000000000000000000000000000000000001", "-3", "3"));
        assertFalse(checkLongValuedPoint("0", "3.0000000000000000000000000000000000000001", "3"));
    }

    /**
     * Проверка попадания в 4 четверть для длинных чисел
     */
    @Test
    public void bigNumbersForthQuarterCheck() {
        assertFalse(checkLongValuedPoint("0.000000000000000000000000000000000001", "-0.0000000000000000000000000001", "3"));
        assertTrue(checkLongValuedPoint("0", "-0.0000000000000000000000000000000000000001", "3"));
        assertFalse(checkLongValuedPoint("0.000000000000000000000000000000000001", "-0.0000000000000000000000000001", "3"));

    }

    /**
     * Проверка попадания в 1 четверть
     */
    @Test
    public void checkFirstQuarter() {
        assertFalse(checkPoint(0, 3.01, 3));
        assertFalse(checkPoint(3, 2.5, 3));
        assertTrue(checkPoint(1, 1, 1.415));
        assertTrue(checkPoint(0, 0, 0));
        assertTrue(checkPoint(0, 3, 3));
    }

    /**
     * Проверка попадания во 2 четверть
     */
    @Test
    public void checkSecondQuarter() {
        assertFalse(checkPoint(-3.01, 0, 3));
        assertTrue(checkPoint(-1, 1, 2));
    }

    /**
     * Проверка попадания в 3 четверть
     */
    @Test
    public void checkThirdQuarter() {
        assertTrue(checkPoint(-1.5, -3, 3));
        assertTrue(checkPoint(0, -3, 3));
        assertTrue(checkPoint(0, -1.5, 2));
        assertFalse(checkPoint(-1.25, 1, 2));
    }

    /**
     * Проверка попадания в 4 четверть
     */
    @Test
    public void checkForthQuarter() {
        assertTrue(checkPoint(0, -1, 3));
        assertTrue(checkPoint(1, 0, 3));
        for (double i = -0.1; i >= -3; i -= 0.1) {
            assertFalse(checkPoint(-i, i, 3));
        }
    }
}