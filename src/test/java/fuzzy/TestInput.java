package fuzzy;

import junit.framework.TestCase;

import static fuzzy.FFunction.trapmf;
import static fuzzy.FFunction.trimf;

/**
 * Created by wojciech on 02.06.17.
 */
public class TestInput extends TestCase {

    public TestInput(String testName) {

        super(testName);

    }

    public void testGetElement() {

        Variable input = new Variable("Price", 0., 10000.);
        input.addMFnc("Low", trapmf(0, 0.1, 20., 50.));
        input.addMFnc("Medium", trimf(40., 200, 500));
        input.addMFnc("High", trapmf(400., 600., 9000., 10000.));

        assertEquals(1, input.fuzzification(10., "Low"));
        assertEquals(1, input.fuzzification(300., "Medium"));
        assertEquals(1, input.fuzzification(700., "High"));
        assertEquals(0, input.fuzzification(-700., "None"));

    }

}
