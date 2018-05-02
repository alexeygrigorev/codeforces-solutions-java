package optimization.tsm.mst;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class SpanningTree {

    private final int number;
    private final List<SpanningTree> childred = Lists.newLinkedList();

    public SpanningTree(int number) {
        this.number = number;
    }

    public List<Integer> eulerTraversal() {
        return eulerTraversal(new LinkedList<Integer>());
    }

    private List<Integer> eulerTraversal(LinkedList<Integer> result) {
        result.add(number);
        for (SpanningTree child : childred) {
            child.eulerTraversal(result);
        }
        return result;
    }

    public void addChild(SpanningTree child) {
        childred.add(child);
    }

}