package org.mipt.drawer;

import java.util.List;

public class Node {
    public int id;
    public int x;
    public int y;
    public int width;
    public int height;
    public List<PositionType> availablePos;
    public PositionType currentPos;
    public Node rev;

    public Node(int id, int x, int y, int width, int height, List<PositionType> availablePos, PositionType currentPos) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.availablePos = availablePos;
        this.currentPos = currentPos;
        rev = new Node(id, x, y, width, height, availablePos, availablePos.get(1));
    }
}
