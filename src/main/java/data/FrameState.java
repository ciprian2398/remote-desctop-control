package data;

import java.awt.*;
import java.io.Serializable;

public class FrameState implements Serializable {
    private Point point;
    private Dimension dimension;

    public FrameState(Point point, Dimension dimension) {
        this.point = point;
        this.dimension = dimension;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
