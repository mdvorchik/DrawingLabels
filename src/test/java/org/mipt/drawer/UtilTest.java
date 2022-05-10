package org.mipt.drawer;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    List<PositionType> availablePos = new ArrayList<>();

    {
        availablePos.add(PositionType.LEFT_TOP);
        availablePos.add(PositionType.RIGHT_TOP);
    }

    @Test
    public void isCross1() {
        Node node1 = new Node(0, 0, 0, 2, 2, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 2, availablePos, PositionType.LEFT_TOP);

        Assert.assertTrue(Util.isCross(node1, node2));
        Assert.assertTrue(Util.isCross(node2, node1));
    }

    @Test
    public void isCross2() {
        Node node1 = new Node(0, 60, 0, 2, 2, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 2, availablePos, PositionType.LEFT_TOP);

        Assert.assertFalse(Util.isCross(node1, node2));
        Assert.assertFalse(Util.isCross(node2, node1));
    }

    @Test
    public void isCross3() {
        Node node1 = new Node(0, 0, 60, 2, 2, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 2, availablePos, PositionType.LEFT_TOP);

        Assert.assertFalse(Util.isCross(node1, node2));
        Assert.assertFalse(Util.isCross(node2, node1));
    }

    @Test
    public void isCross4() {
        Node node1 = new Node(0, 1, 1, 2, 2, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 2, availablePos, PositionType.LEFT_TOP);

        Assert.assertTrue(Util.isCross(node1, node2));
    }

    @Test
    public void isCross5() {
        Node node1 = new Node(0, 3, 3, 2, 2, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 2, availablePos, PositionType.LEFT_TOP);

        Assert.assertFalse(Util.isCross(node1, node2));
    }

    @Test
    public void getLeftX() {
        Node node1 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_TOP);
        Node node3 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_BOTTOM);
        Node node4 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_BOTTOM);

        Assert.assertEquals(-2, Util.getLeftX(node1));
        Assert.assertEquals(0, Util.getLeftX(node2));
        Assert.assertEquals(-2, Util.getLeftX(node3));
        Assert.assertEquals(0, Util.getLeftX(node4));
    }

    @Test
    public void getRightX() {
        Node node1 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_TOP);
        Node node3 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_BOTTOM);
        Node node4 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_BOTTOM);

        Assert.assertEquals(0, Util.getRightX(node1));
        Assert.assertEquals(2, Util.getRightX(node2));
        Assert.assertEquals(0, Util.getRightX(node3));
        Assert.assertEquals(2, Util.getRightX(node4));
    }

    @Test
    public void getTopY() {
        Node node1 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_TOP);
        Node node3 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_BOTTOM);
        Node node4 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_BOTTOM);

        Assert.assertEquals(3, Util.getTopY(node1));
        Assert.assertEquals(3, Util.getTopY(node2));
        Assert.assertEquals(0, Util.getTopY(node3));
        Assert.assertEquals(0, Util.getTopY(node4));
    }

    @Test
    public void getBotY() {
        Node node1 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_TOP);
        Node node2 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_TOP);
        Node node3 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.LEFT_BOTTOM);
        Node node4 = new Node(0, 0, 0, 2, 3, availablePos, PositionType.RIGHT_BOTTOM);

        Assert.assertEquals(0, Util.getBotY(node1));
        Assert.assertEquals(0, Util.getBotY(node2));
        Assert.assertEquals(-3, Util.getBotY(node3));
        Assert.assertEquals(-3, Util.getBotY(node4));
    }
}