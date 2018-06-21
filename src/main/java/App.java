import javax.swing.*;

public class App {
    public App() {
        init();
    }

    private void init() {
        JFrame jFrame = new JFrame("Screen");
        SpecialJPanel specialJPanel = new SpecialJPanel();
        JScrollPane pane = new JScrollPane(specialJPanel);
        jFrame.add(pane);
        jFrame.setVisible(true);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
    }

    public static void main(String... args) {
        new StreamDemo().init();
        new App();
    }
}

