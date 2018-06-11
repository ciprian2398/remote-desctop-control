package data;

import java.awt.event.MouseEvent;

public class Mouse {
    MouseEvent mouseEvent;

    public Mouse(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public void setMouseEvent(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
    }
}
