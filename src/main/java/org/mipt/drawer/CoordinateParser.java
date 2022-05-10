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
        for (int i = 0; i < size; i++) {
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
