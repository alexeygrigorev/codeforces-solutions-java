package optimization.cp;


/**
 * Two goals in life:
 * - feasibility checking
 * - pruning
 * 
 * @author Grigorev Alexey
 *
 * @param <E>
 */
public interface Constraint<E> {

    void informAboutForget(Connector<E> connector);

    void informAboutNewValue(Connector<E> connector);

}
