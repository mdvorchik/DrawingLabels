package org.mipt.drawer;

import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class LabelDrawer {
    private static int offsetByX = 0;
    private final List<Node> nodes;

    public LabelDrawer(List<Node> nodes) {
        this.nodes = nodes;
    }

    private static Object insertAndGetDummyVertexToGraph(mxGraph graph, int offsetByY, int offsetByX, int width, int height) {
        return graph.insertVertex(graph.getDefaultParent(),
                null, null, 2 * width * (offsetByX), 3 * height * (offsetByY + 1),
                width / 10, height / 10, mxConstants.STYLE_IMAGE);
    }

    public void drawLabelsToFile(String fileName) {
        mxGraph graph = new mxGraph();
        fillGraph(graph);

        BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);

        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillGraph(mxGraph graph) {

    }


}
