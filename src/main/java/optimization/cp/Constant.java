package optimization.cp;

public class Constant implements Constraint<Double> {

    private final Double value;

    public Constant(Double value) {
        this.value = value;
    }

    public static Constant register(Double value, Connector<Double> connector) {
        Constant constant = new Constant(value);
        connector.setValue(constant, value);
        return constant;
    }

    @Override
    public void informAboutForget(Connector<Double> connector) {
        throw new IllegalStateException("I am a constant");
    }

    @Override
    public void informAboutNewValue(Connector<Double> connector) {
        throw new IllegalStateException("I am a constant");
    }

}
