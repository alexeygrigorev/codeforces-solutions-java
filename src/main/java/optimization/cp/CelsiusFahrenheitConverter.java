package optimization.cp;

public class CelsiusFahrenheitConverter {

    public static void converter(Connector<Double> c, Connector<Double> f) {
        Connector<Double> u = new Connector<Double>();
        Connector<Double> v = new Connector<Double>();
        Connector<Double> w = new Connector<Double>();
        Connector<Double> x = new Connector<Double>();
        Connector<Double> y = new Connector<Double>();

        Multiplier.register(c, w, u);
        Multiplier.register(v, x, u);

        Adder.register(v, y, f);

        Constant.register(9.0, w);
        Constant.register(5.0, x);
        Constant.register(32.0, y);
    }

}
