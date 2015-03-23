package at.sw2015.calculator.test;

import junit.framework.TestCase;

import at.sw2015.calculator.Calculations;

/**
 * Created by stephan on 18.03.15.
 */
public class CalculationsTest extends TestCase{

    public void testDoAddition(){
        int result = Calculations.doAddition(2, 3);
        assertEquals(5, result);
    }

    public void testDoSubtraction(){
        int result = Calculations.doSubtraction(7, 3);
        assertEquals(4, result);
    }

    public void testDoMultiplication(){
        int result = Calculations.doMultiplication(2, 3);
        assertEquals(6, result);
    }

    public void testDoDivision(){
        int result = Calculations.doDivision(8, 4);
        assertEquals(2, result);
    }

    public void testDoDivision1(){
        int result = Calculations.doDivision(8, 0);
        assertEquals(0, result);
    }
}
