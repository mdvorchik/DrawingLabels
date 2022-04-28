package org.mipt.drawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoordinateParser {
    List<Node> nodes;

    public CoordinateParser(String fileName) {
        Scanner scanner = new Scanner(fileName);
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            List<PositionType> availablePos = new ArrayList<>();
            availablePos.add(getPosTypeFromNumber(scanner.nextInt()));
            availablePos.add(getPosTypeFromNumber(scanner.nextInt()));
            Node node = new Node(0, x, y, width, height, availablePos, availablePos.get(0));
            nodes.add(node);
        }
    }

    private PositionType getPosTypeFromNumber(int number) {
        switch (number) {
            case (0):
                return PositionType.RIGHT_BOTTOM;
            case (1):
                return PositionType.LEFT_BOTTOM;
            case (2):
                return PositionType.RIGHT_TOP;
            default:
                return PositionType.LEFT_TOP;
        }
    }

}
