package beans;

import databaseInfo.DatabaseManager;
import model.Point;
import model.Validator;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@SessionScoped
public class DataBean implements Serializable {
    private String lastX = "-4";
    private String lastY;
    private String lastR = "1";
    private String lastOffset = "-180";
    private String canvasX;
    private String canvasY;
    private List<Point> history;
    private String username;
    private DatabaseManager db = new DatabaseManager();

    public DataBean() {
        history = db.getList(username);
    }

    public boolean unlock() {
        try {
            double x = Double.parseDouble(lastX);
            double y = Double.parseDouble(lastY);
            double r = Double.parseDouble(lastR);
            if (y >= 3 || y <= -3 || x > 4 || x < -4)
                return true;
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }

    public void addPoint() {
        Point p = new Point(lastX, lastY, lastR, Integer.parseInt(lastOffset), username);
        if (lastX.length() > 6 || lastR.length() > 6 || lastY.length() > 6) {
            BigDecimal bigR = new BigDecimal(lastR),
                    bigX = new BigDecimal(lastX),
                    bigY = new BigDecimal(lastY);
            Validator.bigNumbersCheck(p, bigX, bigY, bigR);
        } else Validator.check(p);
        if (username != null) {
            history = db.getList(username);
            history.add(p);
            db.add(p);
        }
    }

    public void addCanvasPoint() {
        Point p = new Point(canvasX, canvasY, lastR, Integer.parseInt(lastOffset), username);
        p.setUsername(username);
        OffsetDateTime currentDate = OffsetDateTime.now();
        int offset = Integer.parseInt(lastOffset);
        int serverOffset = -180;
        offset -= serverOffset;
        currentDate = currentDate.plusMinutes(offset);
        p.setTime(currentDate);

        Validator.check(p);
        if (username != null) {
            history = db.getList(username);
            history.add(p);
            db.add(p);
        }
    }

    public void clearHistory() {
        history = new LinkedList<>();
        assert username != null;
        db.truncate(username);
    }

    public List getHistory() {
        return history;
    }

    public void setHistory(List<Point> history) {
        this.history = history;
    }

    public String getLastX() {
        return lastX;
    }

    public void setLastX(String lastX) {
        this.lastX = lastX;
    }

    public String getLastY() {
        return lastY;
    }

    public void setLastY(String lastY) {
        this.lastY = lastY;
    }

    public String getLastR() {
        return lastR;
    }

    public void setLastR(String lastR) {
        this.lastR = lastR;
    }

    public String getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(String canvasX) {
        this.canvasX = canvasX;
    }

    public String getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(String canvasY) {
        this.canvasY = canvasY;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        history = db.getList(username);
    }
}
