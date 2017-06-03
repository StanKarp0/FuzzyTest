package fuzzy;

import fuzzy.functions.Trapmf;
import fuzzy.functions.Trimf;
import junit.framework.TestCase;

/**
 * Created by wojciech on 02.06.17.
 */
public class TestInput extends TestCase {

    public TestInput(String testName) {

        super(testName);

    }

    public void testGetElement() {

        Input input = new Input("Price");
        input.addMFnc("Low", new Trapmf(0, 0.1, 20., 50.));
        input.addMFnc("Medium", new Trimf(40., 200, 500));
        input.addMFnc("High", new Trapmf(400., 600., 9000., 10000.));

        assertEquals(1, input.getProbability(10., "Low"));
        assertEquals(1, input.getProbability(300., "Medium"));
        assertEquals(1, input.getProbability(700., "High"));
        assertEquals(0, input.getProbability(-700., "None"));

    }

}
