package at.sw2015.calculator;

/**
 * Created by stephan on 18.03.15.
 */
public class Calculations {

    public static int doAddition (int first, int second) {
        return first + second;
    }

    public static int doSubtraction (int first, int second) {
        return first - second;
    }

    public static int doMultiplication (int first, int second) {
        return first * second;
    }

    public static int doDivision (int first, int second) {

        if(second == 0) {
            return 0;
        }

        return first / second;
    }
}
