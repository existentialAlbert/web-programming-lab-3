package test;

import model.Point;
import model.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    void bigNumbersCheck() {
        double x = 0,
                y = -0.00000000000000000000000000000001,
                r = 2;
        Point p = new Point(x, y, r, -180, "fdf");
        Validator.check(p);
        assertFalse(p.isHit());
    }

    @Test
    void check() {
        double x = 0,
                y = -0.01,
                r = 0;
        Point p = new Point(x, y, r, -180, "fdf");

        assertTrue(Validator.check(p));
        x = -3.01;
        y = 0;
        r = 3;
        p = new Point(x, y, r, -180, "sfdf");

        assertFalse(Validator.check(p));
        x = -0.5;
        y = 0.5;
        r = 1;
        p = new Point(x, y, r, -180, "sfdf");

        assertTrue(Validator.check(p));
    }
}