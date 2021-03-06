package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "POINTS")
public class Point implements Serializable {
    @Column(name = "X")
    private double x;
    @Column(name = "Y")
    private double y;
    @Column(name = "R")
    private double r;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "HIT")
    private String hit;
    @Id
    private OffsetDateTime time;

    @Transient
    private boolean precision;
    @Transient
    private int offset;
    @Transient
    private String date;


    public Point() {
    }

    public Point(double x, double y, double r, int offset, String username) {
            String a = "";
            if (Math.abs(offset) > 1440)
                a = "Смещение не может быть больше суток!";
            else if (r < 0)
                a = "Радиус не может быть отрицательным!";
            if (!a.equals(""))
                throw new IllegalArgumentException(a);

        this.x = x;
        this.y = y;
        this.r = r;
        setPrecision(false);
        this.offset = offset;
        this.username = username;
    }

    public Point(String x, String y, String r, int offset, String username) {
        this(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(r), offset, username);
    }

    public boolean isPrecision() {
        return precision;
    }

    public void setPrecision(boolean precision) {
        this.precision = precision;
        hit = precision ? "Попадание" : "Промах";
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
        this.date = time.getDayOfMonth() + "." + time.getMonthValue() + "." + time.getYear() +
                "\n" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }

    public String getDate() {
        return date;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return x + "\n" + y + "\n" + r + "\n" + time + "\n" + hit + "\n" + username;
    }
}

