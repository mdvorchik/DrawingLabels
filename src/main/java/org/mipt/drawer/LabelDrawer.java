package org.mipt.drawer;

import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mipt.drawer.PositionType.LEFT_BOTTOM;
import static org.mipt.drawer.PositionType.LEFT_TOP;

public class LabelDrawer {
    private static final List<List<Integer>> clausesGraph = new ArrayList<>();
    private static final List<List<Integer>> revClausesGraph = new ArrayList<>();
    private final List<Node> nodes;
    private final Set<Clause> clauses = new HashSet<>();

    public LabelDrawer(List<Node> nodes) {
        this.nodes = nodes;
        for (Node node : nodes) {
            clausesGraph.add(new ArrayList<>());
            clausesGraph.add(new ArrayList<>());
            revClausesGraph.add(new ArrayList<>());
            revClausesGraph.add(new ArrayList<>());
        }
    }

    private static Object insertAndGetDummyVertexToGraph(mxGraph graph, String label, double offsetByX, double offsetByY, double width, double height) {
        return graph.insertVertex(graph.getDefaultParent(),
                null, label, offsetByX, offsetByY,
                width, height, mxConstants.STYLE_ROUNDED);
    }

    public int drawLabelsToFile(String fileName) {
        mxGraph graph = new mxGraph();
        fillClauses();
        fillClausesGraphs();

        TwoSATSolver twoSATSolver = new TwoSATSolver(clausesGraph, revClausesGraph);
        List<Integer> answers = twoSATSolver.solve();

        if (answers == null) {
            System.out.println("NO SOLUTION");
            return -1;
        }

        fillGraph(graph, answers);

        BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);

        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void fillClauses() {
        for (int i = 0; i < nodes.size(); i++) {
            clauses.add(new Clause(nodes.get(i).id, nodes.get(i).id, false, true));
            for (int j = 0; j < nodes.size(); j++) {
                if (nodes.get(i).id != nodes.get(j).id) {
                    if (Util.isCross(nodes.get(i), nodes.get(j))) {
                        clauses.add(new Clause(nodes.get(i).id, nodes.get(j).id, true, true));
                    }
                    if (Util.isCross(nodes.get(i).rev, nodes.get(j))) {
                        clauses.add(new Clause(nodes.get(i).id, nodes.get(j).id, false, true));
                    }
                    if (Util.isCross(nodes.get(i), nodes.get(j).rev)) {
                        clauses.add(new Clause(nodes.get(i).id, nodes.get(j).id, true, false));
                    }
                    if (Util.isCross(nodes.get(i).rev, nodes.get(j).rev)) {
                        clauses.add(new Clause(nodes.get(i).id, nodes.get(j).id, false, false));
                    }
                }
            }
        }
    }

    private void fillClausesGraphs() {
        int offsetForReverse = nodes.size();
        for (Clause clause : clauses) {
            if (!clause.isFirstNodeReversed && !clause.isSecondNodeReversed) {
                clausesGraph.get(clause.firstNodeId + offsetForReverse).add(clause.secondNodeId);
                clausesGraph.get(clause.secondNodeId + offsetForReverse).add(clause.firstNodeId);

                revClausesGraph.get(clause.secondNodeId).add(clause.firstNodeId + offsetForReverse);
                revClausesGraph.get(clause.firstNodeId).add(clause.secondNodeId + offsetForReverse);
            }
            if (!clause.isFirstNodeReversed && clause.isSecondNodeReversed) {
                clausesGraph.get(clause.firstNodeId + offsetForReverse).add(clause.secondNodeId + offsetForReverse);
                clausesGraph.get(clause.secondNodeId).add(clause.firstNodeId);

                revClausesGraph.get(clause.secondNodeId + offsetForReverse).add(clause.firstNodeId + offsetForReverse);
                revClausesGraph.get(clause.firstNodeId).add(clause.secondNodeId);
            }
            if (clause.isFirstNodeReversed && !clause.isSecondNodeReversed) {
                clausesGraph.get(clause.firstNodeId).add(clause.secondNodeId);
                clausesGraph.get(clause.secondNodeId + offsetForReverse).add(clause.firstNodeId + offsetForReverse);

                revClausesGraph.get(clause.secondNodeId).add(clause.firstNodeId);
                revClausesGraph.get(clause.firstNodeId + offsetForReverse).add(clause.secondNodeId + offsetForReverse);
            }
            if (clause.isFirstNodeReversed && clause.isSecondNodeReversed) {
                clausesGraph.get(clause.firstNodeId).add(clause.secondNodeId + offsetForReverse);
                clausesGraph.get(clause.secondNodeId).add(clause.firstNodeId + offsetForReverse);

                revClausesGraph.get(clause.secondNodeId + offsetForReverse).add(clause.firstNodeId);
                revClausesGraph.get(clause.firstNodeId + offsetForReverse).add(clause.secondNodeId);
            }
        }
    }

    private void fillGraph(mxGraph graph, List<Integer> answers) {
        for (int i = 0; i < answers.size(); i++) {
            boolean isRev = answers.get(i) >= nodes.size();
            int ind = answers.get(i) % nodes.size();
            Node node = isRev ? nodes.get(ind).rev : nodes.get(ind);
            double x = (node.currentPos == LEFT_BOTTOM || node.currentPos == LEFT_TOP) ? Util.getRightX(node) - node.width : Util.getLeftX(node);
            double y = Util.getBotY(node);
            if (answers.size() - i <= 4) {
                insertAndGetDummyVertexToGraph(graph, "border_" + (answers.size() - i), x, y, node.width, node.height);
            } else {
                insertAndGetDummyVertexToGraph(graph, node.currentPos.toString() + "_n" + node.id, x, y, node.width, node.height);
                insertAndGetDummyVertexToGraph(graph, "n" + node.id, node.x, node.y, 15, 15);
            }
        }
    }


}
