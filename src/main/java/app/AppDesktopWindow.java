package app;

import panel.SpecialJPanel;

import javax.swing.*;

public class AppDesktopWindow {

    private static void createDesktopWindow(SpecialJPanel specialJPanel) {
        JFrame jFrame = new JFrame("Screen");
        JScrollPane pane = new JScrollPane(specialJPanel);
        jFrame.add(pane);
        jFrame.setVisible(true);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
    }

    public static void main(String... args) {
        SpecialJPanel specialJPanel = new SpecialJPanel();

        AppDesktopWindow.createDesktopWindow(specialJPanel);
    }
}

