package model;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Validator {
    private static boolean inArea(Point point, Predicate<Double> x, Predicate<Double> y) {
        return x.test(point.getX()) && y.test(point.getY());
    }

    private static boolean inArea(Point point, BiFunction<Double, Double, Boolean> condition) {
        return condition.apply(point.getX(), point.getY());
    }

    public static boolean bigNumbersCheck(Point p, BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal zero = new BigDecimal("0");
        boolean hit = false;
        if (x.compareTo(zero) <= 0 && y.compareTo(zero) < 0)
            hit = x.compareTo(r.divide(new BigDecimal(-2), 1)) >= 0 &&
                    y.compareTo(r.multiply(new BigDecimal(-1))) >= 0;
        else if (x.compareTo(zero) >= 0 && y.compareTo(zero) >= 0)
            hit = r.multiply(r).compareTo(x.multiply(x).add(y.multiply(y))) >= 0;
        else if (x.compareTo(zero) <= 0 && y.compareTo(zero) >= 0)
            hit = y.compareTo(r.add(x)) <= 0;
        p.setPrecision(hit);
        return hit;
    }

    public static boolean check(Point point) {
        boolean hit = false;
        double x = point.getX(), y = point.getY();
        if (x >= 0 && y >= 0)
            hit = inArea(point, (a, b) -> a * a + b * b <= point.getR() * point.getR());
        if (x > 0 && y < 0)
            hit = inArea(point, a -> false, b -> false);
        if (x <= 0 && y < 0)
            hit = inArea(point, a -> a >= -point.getR() / 2, b -> b >= -point.getR());
        if (x <= 0 && y >= 0)
            hit = inArea(point, (a, b) -> b <= point.getR() + a);
        point.setPrecision(hit);
        return hit;
    }
}
