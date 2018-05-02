package optimization.tsm;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.google.common.collect.Maps;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

public class Drawer {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final String VERTEX_STYLE = "defaultVertex;strokeColor=black;fillColor=white;rounded=true";
    private static final String FIRST_STYLE = "defaultVertex;strokeColor=black;fillColor=red;rounded=true";

    private final List<Point> input;
    private final Result result;
    private final String filename;
    private double maxX = -1;
    private double maxY = -1;
    private Map<String, mxCell> nodes = Maps.newHashMap();

    public Drawer(List<Point> input, Result result, String filename) {
        this.input = input;
        this.result = result;
        this.filename = filename;
        findMaxes();
    }

    private void findMaxes() {
        double maxX = -1;
        double maxY = -1;

        for (Point p : input) {
            if (maxX < p.getX()) {
                maxX = p.getX();
            }
            if (maxY < p.getY()) {
                maxY = p.getY();
            }
        }

        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void visualize() {
        mxGraph graph = prepareGrraph();
        exportSvg(graph, filename);
    }

    private mxGraph prepareGrraph() {
        mxGraph graph = new mxGraph();

        graph.getModel().beginUpdate();
        Object defaultParent = graph.getDefaultParent();

        for (Point p : input) {
            String id = id(p);
            mxCell v = (mxCell) graph.insertVertex(defaultParent, id, id, xPos(p), yPos(p), 20, 20, VERTEX_STYLE);
            nodes.put(id, v);
        }

        List<Point> path = result.getPath();
        Iterator<Point> iterator = path.iterator();
        Point prev = iterator.next();
        Point first = prev;

        while (iterator.hasNext()) {
            Point next = iterator.next();
            graph.insertEdge(defaultParent, null, null, node(prev), node(next));
            prev = next;
        }

        graph.insertEdge(defaultParent, null, null, node(prev), node(first));

        node(first).setStyle(FIRST_STYLE);
        graph.getModel().endUpdate();
        return graph;
    }

    private mxCell node(Point prev) {
        return nodes.get(id(prev));
    }

    private double yPos(Point p) {
        return p.getY() * HEIGHT / maxY;
    }

    private double xPos(Point p) {
        return p.getX() * WIDTH / maxX;
    }

    private void exportSvg(mxGraph graph, String filename) {
        try {
            Document document = mxCellRenderer.createSvgDocument(graph, null, 1, null, null);
            mxUtils.writeFile(mxXmlUtils.getXml(document), filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String id(Point p) {
        return String.valueOf(p.getNumber());
    }
}
