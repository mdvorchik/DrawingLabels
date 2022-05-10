package org.mipt.drawer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CoordinateParser coordinateParser = new CoordinateParser("coordinates.txt");
        LabelDrawer graphDrawer = new LabelDrawer(coordinateParser.nodes);

        graphDrawer.drawLabelsToFile("graph.png");
        System.out.println("Done! ");

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            }

            ImageIcon icon = new ImageIcon("labels.png");
            JOptionPane.showMessageDialog(
                    null,
                    "Labels",
                    "labels", JOptionPane.INFORMATION_MESSAGE,
                    icon);
        });
    }
}
