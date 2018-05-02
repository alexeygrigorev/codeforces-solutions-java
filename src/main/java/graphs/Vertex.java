package graphs;

import java.util.List;

import com.google.common.collect.Lists;

public class Vertex {

    private final int number;
    private final double weight;
    private final List<Vertex> vertices = Lists.newLinkedList();

    public Vertex(int number, double weight) {
        this.number = number;
        this.weight = weight;
    }

    public List<Vertex> adjacent() {
        return vertices;
    }

    public int getNumber() {
        return number;
    }

    public double getWeight() {
        return weight;
    }

}
