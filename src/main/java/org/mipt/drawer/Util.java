package org.mipt.drawer;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static <T> List<T> listFromIterable(Iterable<? extends T> iterable) {
        List<T> myList = new ArrayList<>();
        for (T t : iterable) {
            myList.add(t);
        }
        return myList;
    }

    public static boolean isCross(Node node1, Node node2) {
        int aX = getLeftX(node1);
        int bX = getLeftX(node2);
        int aX1 = getRightX(node1);
        int bX1 = getRightX(node2);
        int aY = getTopY(node1);
        int bY = getTopY(node2);
        int aY1 = getBotY(node1);
        int bY1 = getBotY(node2);
        return (
                (
                        (
                                (aX >= bX && aX <= bX1) || (aX1 >= bX && aX1 <= bX1)
                        ) && (
                                (aY >= bY && aY <= bY1) || (aY1 >= bY && aY1 <= bY1)
                        )
                ) || (
                        (
                                (bX >= aX && bX <= aX1) || (bX1 >= aX && bX1 <= aX1)
                        ) && (
                                (bY >= aY && bY <= aY1) || (bY1 >= aY && bY1 <= aY1)
                        )
                )
        ) || (
                (
                        (
                                (aX >= bX && aX <= bX1) || (aX1 >= bX && aX1 <= bX1)
                        ) && (
                                (bY >= aY && bY <= aY1) || (bY1 >= aY && bY1 <= aY1)
                        )
                ) || (
                        (
                                (bX >= aX && bX <= aX1) || (bX1 >= aX && bX1 <= aX1)
                        ) && (
                                (aY >= bY && aY <= bY1) || (aY1 >= bY && aY1 <= bY1)
                        )
                )
        );
    }

    public static int getLeftX(Node node) {
        int x = node.x;
        if (node.currentPos.equals(PositionType.LEFT_BOTTOM) || node.currentPos.equals(PositionType.LEFT_TOP)) {
            x -= node.width;
        }
        return x;
    }

    public static int getRightX(Node node) {
        int x = node.x;
        if (node.currentPos.equals(PositionType.RIGHT_BOTTOM) || node.currentPos.equals(PositionType.RIGHT_TOP)) {
            x += node.width;
        }
        return x;
    }

    public static int getTopY(Node node) {
        int y = node.y;
        if (node.currentPos.equals(PositionType.LEFT_TOP) || node.currentPos.equals(PositionType.RIGHT_TOP)) {
            y += node.height;
        }
        return y;
    }

    public static int getBotY(Node node) {
        int y = node.y;
        if (node.currentPos.equals(PositionType.LEFT_BOTTOM) || node.currentPos.equals(PositionType.RIGHT_BOTTOM)) {
            y -= node.height;
        }
        return y;
    }
}
