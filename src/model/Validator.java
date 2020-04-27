package model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Validator {
    public static void check(Point p, String X, String Y, String R, String Offset) {
        OffsetDateTime currentDate = OffsetDateTime.now();
        double x = Double.parseDouble(X);
        double y = Double.parseDouble(Y);
        int offset = Integer.parseInt(Offset);
        int serverOffset = -180;
        offset -= serverOffset;
        currentDate = currentDate.plusMinutes(offset);
        p.setTime(currentDate);

        if (X.length() > 6 ||
                R.length() > 6 ||
                Y.length() > 6) {
            BigDecimal bigR = new BigDecimal(R),
                    bigX = new BigDecimal(X),
                    bigY = new BigDecimal(Y);
            p.check(bigX, bigY, bigR);
        }
        if (x >= 0 && y >= 0)
            p.check((a, b) -> a * a + b * b <= p.getR() * p.getR());
        if (x > 0 && y < 0)
            p.check(a -> false, b -> false);
        if (x < 0 && y < 0)
            p.check(a -> a >= -p.getR() / 2, b -> b >= -p.getR());
        if (x <= 0 && y >= 0)
            p.check((a, b) -> b <= p.getR() + a);
    }
}
