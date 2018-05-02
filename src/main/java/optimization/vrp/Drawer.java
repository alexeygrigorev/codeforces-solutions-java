package optimization.vrp;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    private static final String FIRST_STYLE = "defaultVertex;strokeColor=black;fillColor=red;rounded=true";
    private static final String VERTEX_STYLE = "defaultVertex;strokeColor=black;fillColor=white;rounded=true";

    private final InputData input;
    private final Result result;

    private final String filename;
    private double maxX, maxY, minX, minY;

    private Map<String, mxCell> nodes = Maps.newHashMap();

    public Drawer(InputData input, Result result, String filename) {
        this.input = input;
        this.result = result;
        this.filename = filename;

        findBounds();
    }

    public void visualize() {
        mxGraph graph = prepareGraph();
        exportSvg(graph, filename);
    }

    private mxGraph prepareGraph() {
        mxGraph graph = new mxGraph();

        graph.getModel().beginUpdate();

        drawWarehouse(graph);
        drawLocations(graph);
        drawEdges(graph);

        graph.getModel().endUpdate();

        return graph;
    }

    private void drawWarehouse(mxGraph graph) {
        Object defaultParent = graph.getDefaultParent();
        Location warehouse = input.getWarehouse();

        String warehouseId = id(warehouse);
        mxCell firstVertex =
                (mxCell) graph.insertVertex(defaultParent, warehouseId, warehouseId, xPos(warehouse), yPos(warehouse), 20, 20, FIRST_STYLE);

        nodes.put(warehouseId, firstVertex);
    }

    private void drawLocations(mxGraph graph) {
        Object defaultParent = graph.getDefaultParent();
        List<Location> locations = input.getLocations();

        for (Location p : locations) {
            String id = id(p);
            mxCell v = (mxCell) graph.insertVertex(defaultParent, id, id, xPos(p), yPos(p), 20, 20, VERTEX_STYLE);
            nodes.put(id, v);
        }
    }

    private void drawEdges(mxGraph graph) {
        Object defaultParent = graph.getDefaultParent();

        List<Vehicle> vehicles = result.getVehicles();
        for (Vehicle vehicle : vehicles) {
            Iterator<Location> iterator = vehicle.getLocations().iterator();
            Location prev = iterator.next();

            String style = randomColorStyle();

            while (iterator.hasNext()) {
                Location next = iterator.next();
                graph.insertEdge(defaultParent, null, null, node(prev), node(next), style);
                prev = next;
            }
        }
    }

    private static String randomColorStyle() {
        Color color = randomColor();
        String rgb = String.format("#%2h%2h%2h", color.getRed(), color.getGreen(), color.getBlue());
        return "strokeWidth=2;strokeColor=" + rgb;
    }

    private mxCell node(Location prev) {
        return nodes.get(id(prev));
    }

    private double yPos(Location p) {
        return p.getY() * HEIGHT / maxY - minY;
    }

    private double xPos(Location p) {
        return p.getX() * WIDTH / maxX - minX;
    }

    private void exportSvg(mxGraph graph, String filename) {
        try {
            Document document = mxCellRenderer.createSvgDocument(graph, null, 1, null, null);
            mxUtils.writeFile(mxXmlUtils.getXml(document), filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String id(Location p) {
        return String.valueOf(p.getIndex());
    }

    private void findBounds() {
        Location warehouse = input.getWarehouse();

        double maxX = warehouse.getX();
        double maxY = warehouse.getY();
        double minX = warehouse.getX();
        double minY = warehouse.getY();

        for (Location p : input.getLocations()) {
            if (maxX < p.getX()) {
                maxX = p.getX();
            }
            if (maxY < p.getY()) {
                maxY = p.getY();
            }
            if (minX > p.getX()) {
                minX = p.getX();
            }
            if (minY > p.getY()) {
                minY = p.getY();
            }
        }

        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }

    private final static Random random = new Random();

    private static Color randomColor() {
        // http://stackoverflow.com/questions/43044/algorithm-to-randomly-generate-an-aesthetically-pleasing-color-palette
        int red = 127 + random.nextInt(127);
        int green = 127 + random.nextInt(127);
        int blue = 127 + random.nextInt(127);
        return new Color(red, green, blue);
    }

}
