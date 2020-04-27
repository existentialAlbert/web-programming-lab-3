package beans;

import databaseInfo.DatabaseManager;
import model.Point;
import model.Validator;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    private boolean lastHit;
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
        Point p = new Point(lastX, lastY, lastR);
        p.setUsername(username);
        Validator.check(p, lastX, lastY, lastR, lastOffset);
        lastHit = p.isHit();
        if (username != null) {
            history = db.getList(username);
            history.add(p);
            db.add(p, username);
        }
    }

    public void addCanvasPoint() {
        Point p = new Point(canvasX, canvasY, lastR);
        p.setUsername(username);
        Validator.check(p, canvasX, canvasY, lastR, lastOffset);
        lastHit = p.isHit();
        if (username != null) {
            history = db.getList(username);
            history.add(p);
            db.add(p, username);
        }
    }

    public void clearHistory() {
        history = new LinkedList<>();
        assert username!=null;
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

    public String getLastOffset() {
        return lastOffset;
    }

    public void setLastOffset(String lastOffset) {
        this.lastOffset = lastOffset;
    }

    public boolean isLastHit() {
        return lastHit;
    }

    public void setLastHit(boolean lastHit) {
        this.lastHit = lastHit;
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
