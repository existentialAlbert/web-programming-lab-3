package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import javax.persistence.*;

@Entity
@Table(name = "POINTS")
public class Point implements Serializable {
    @Column(name = "X")
    private double x;
    @Column(name = "Y")
    private double y;
    @Column(name = "R")
    private double r;
    @Transient
    private boolean hit;
    @Id
    private OffsetDateTime time;
    @Column(name = "PRECISION")
    private String precision;
    @Transient
    private int offset;
    @Transient
    private String Date;
    @Column(name = "USERNAME")
    private String username;

    public Point() {
    }
    private Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        hit = false;
    }

    public Point(String x, String y, String r) {
        this(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(r));
    }

    public void check(Predicate<Double> x, Predicate<Double> y) {
        hit = x.test(this.x) && y.test(this.y);
        precision = hit ? "Попадание" : "Промах";
    }

    public void check(BiFunction<Double, Double, Boolean> condition) {
        hit = condition.apply(x, y);
        precision = hit ? "Попадание" : "Промах";
    }

    public void check(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(new BigDecimal("0")) > 0 && y.compareTo(new BigDecimal("0")) < 0)
            hit = false;
        else if (x.compareTo(new BigDecimal("0")) < 0 && y.compareTo(new BigDecimal("0")) < 0)
            hit = x.compareTo(r.divide(new BigDecimal("-2"), 1)) >= 0 && y.compareTo(r.multiply(new BigDecimal("-1"))) >= 0;
        else if (x.compareTo(new BigDecimal("0")) > 0 && y.compareTo(new BigDecimal("0")) > 0)
            hit = y.compareTo(r.subtract(x)) <= 0;
        else if (x.compareTo(new BigDecimal("0")) < 0 && y.compareTo(new BigDecimal("0")) > 0)
            hit = r.multiply(r).compareTo(x.multiply(x).add(y.multiply(y))) >= 0;
        else if (x.compareTo(new BigDecimal("0")) == 0 || y.compareTo(new BigDecimal("0")) == 0)
            hit = x.compareTo(r) <= 0 && y.compareTo(r) <= 0;
        precision = hit ? "Попадание" : "Промах";
    }

    public boolean isHit() {
        return hit;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public String getDate() {
        setDate("");
        return Date;
    }

    public void setDate(String date) {
        Date = time.getDayOfMonth() + "." + time.getMonthValue() + "." + time.getYear() +
                "\n" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

