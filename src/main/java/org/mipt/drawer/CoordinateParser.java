package org.mipt.drawer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoordinateParser {
    List<Node> nodes = new ArrayList<>();

    public CoordinateParser(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int size = scanner.nextInt();
        int i = 0;
        for (; i < size; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            List<PositionType> availablePos = new ArrayList<>();
            availablePos.add(getPosTypeFromNumber(scanner.nextInt()));
            availablePos.add(getPosTypeFromNumber(scanner.nextInt()));
            Node node = new Node(i, x, y, width, height, availablePos, availablePos.get(0));
            nodes.add(node);
        }

        addBorders(i);
    }

    private void addBorders(int i) {
        List<PositionType> availablePos1 = new ArrayList<>();
        availablePos1.add(getPosTypeFromNumber(2));
        availablePos1.add(getPosTypeFromNumber(2));
        Node border1 = new Node(i++, 500, -1, 500, 1, availablePos1, availablePos1.get(0));
        Node border2 = new Node(i++, 500, 501, 500, 1, availablePos1, availablePos1.get(0));
        List<PositionType> availablePos2 = new ArrayList<>();
        availablePos2.add(getPosTypeFromNumber(0));
        availablePos2.add(getPosTypeFromNumber(0));
        Node border3 = new Node(i++, 500, 0, 1, 499, availablePos2, availablePos2.get(0));
        Node border4 = new Node(i, -1, 1, 1, 499, availablePos2, availablePos2.get(0));
        nodes.add(border1);
        nodes.add(border2);
        nodes.add(border3);
        nodes.add(border4);
    }

    private PositionType getPosTypeFromNumber(int number) {
        switch (number) {
            case (0):
                return PositionType.LEFT_TOP;
            case (1):
                return PositionType.RIGHT_TOP;
            case (2):
                return PositionType.LEFT_BOTTOM;
            default:
                return PositionType.RIGHT_BOTTOM;
        }
    }

}
