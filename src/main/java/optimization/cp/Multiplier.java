package optimization.cp;

/**
 * @author Grigorev Alexey
 */
public class Multiplier implements Constraint<Double> {

    private final Connector<Double> input1;
    private final Connector<Double> input2;
    private final Connector<Double> output;

    public static Multiplier register(Connector<Double> input1, Connector<Double> input2, Connector<Double> output) {
        Multiplier multiplier = new Multiplier(input1, input2, output);
        input1.addConstraint(multiplier);
        input2.addConstraint(multiplier);
        output.addConstraint(multiplier);
        return multiplier;
    }

    public Multiplier(Connector<Double> input1, Connector<Double> input2, Connector<Double> output) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
    }

    @Override
    public void informAboutNewValue(Connector<Double> connector) {
        if ((input1.hasValue() && input1.getValue() == 0.0) || (input2.hasValue() && input2.getValue() == 0.0)) {
            output.setValue(this, 0.0);
        } else if (input1.hasValue() && input2.hasValue()) {
            Double result = input1.getValue() * input2.getValue();
            output.setValue(this, result);
        } else if (output.hasValue() && input1.hasValue()) {
            Double result = output.getValue() / input1.getValue();
            input2.setValue(this, result);
        } else if (output.hasValue() && input2.hasValue()) {
            Double result = output.getValue() / input2.getValue();
            input1.setValue(this, result);
        }
    }

    @Override
    public void informAboutForget(Connector<Double> connector) {
        input1.forgetValue(this);
        input2.forgetValue(this);
        output.forgetValue(this);        
    }


}
