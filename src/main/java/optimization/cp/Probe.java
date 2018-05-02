package optimization.cp;

public class Probe<E> implements Constraint<E> {

    @Override
    public void informAboutForget(Connector<E> connector) {
        System.out.println("Connector " + connector + " has forgotten its value");
    }

    @Override
    public void informAboutNewValue(Connector<E> connector) {
        System.out.println("Connector " + connector + " has new value " + connector.getValue());
    }

}
