package org.mipt.drawer;

import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class LabelDrawer {
    private static int offsetByX = 0;
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

    private static Object insertAndGetDummyVertexToGraph(mxGraph graph, int offsetByY, int offsetByX, int width, int height) {
        return graph.insertVertex(graph.getDefaultParent(),
                null, null, 2 * width * (offsetByX), 3 * height * (offsetByY + 1),
                width / 10, height / 10, mxConstants.STYLE_IMAGE);
    }

    public void drawLabelsToFile(String fileName) {
        mxGraph graph = new mxGraph();
        fillClauses();
        fillClausesGraphs();

        TwoSATSolver twoSATSolver = new TwoSATSolver(clausesGraph, revClausesGraph);
        List<Integer> answers = twoSATSolver.solve();

        fillGraph(graph, answers);

        BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);

        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    }


}
