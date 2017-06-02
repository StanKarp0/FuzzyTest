package fuzzy;

import fuzzy.functions.Trapmf;
import fuzzy.functions.Trimf;
import junit.framework.TestCase;
/**
 * Created by wojciech on 02.06.17.
 */
public class TestVariable extends TestCase {

    public TestVariable(String testName) {

        super(testName);

    }

    public void testGetElement() {

        FInVariable variable = new FInVariable("Price");
        variable.addMFnc("Low", new Trapmf(0, 0.1, 20., 50.));
        variable.addMFnc("Medium", new Trimf(40., 200, 500));
        variable.addMFnc("High", new Trapmf(400., 600., 9000., 10000.));

        assertEquals("Low", variable.getElement(10.).getValue());
        assertEquals("Medium", variable.getElement(300.).getValue());
        assertEquals("High", variable.getElement(700.).getValue());

    }

}
