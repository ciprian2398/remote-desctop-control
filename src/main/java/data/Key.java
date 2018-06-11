package data;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Key implements Serializable {
    private KeyEvent keyEvent;

    public Key(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }

    public KeyEvent getKeyEvent() {
        return keyEvent;
    }

    public void setKeyEvent(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }
}
