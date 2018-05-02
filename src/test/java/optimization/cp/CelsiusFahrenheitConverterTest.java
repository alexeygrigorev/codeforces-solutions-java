package optimization.cp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CelsiusFahrenheitConverterTest {

    @Test
    public void converter() {
        Probe<Double> probe = new Probe<Double>();

        Connector<Double> c = new Connector<Double>();
        c.addConstraint(probe);

        Connector<Double> f = new Connector<Double>();
        f.addConstraint(probe);

        CelsiusFahrenheitConverter.converter(c, f);

        c.setValue("user", 10.0);
        assertEquals(f.getValue(), 50.0);
        
        c.forgetValue("user");

        f.setValue("user", 50.0);
        assertEquals(c.getValue(), 10.0);
        
    }
}
