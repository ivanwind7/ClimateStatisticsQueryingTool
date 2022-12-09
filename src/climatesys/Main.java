package climatesys;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Build a JFrame object and set attributes
        JFrame frame = new JFrame("Climate Statistics System");
        frame.setSize(300, 460);
        frame.setResizable(false);

        // Build a JPanel and add it to frame
        JPanel panel = new JPanel();
        BuildGUI.placeComponents(panel, frame);
        frame.add(panel);

        // Set frame visible
        frame.setVisible(true);
    }
}