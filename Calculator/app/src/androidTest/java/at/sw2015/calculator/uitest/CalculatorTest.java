package at.sw2015.calculator.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import at.sw2015.calculator.Calculator;
import at.sw2015.calculator.R;

/**
 * Created by stephan on 18.03.15.
 */
public class CalculatorTest extends ActivityInstrumentationTestCase2<Calculator> {

    private Solo mySolo;

    public CalculatorTest() {
        super(Calculator.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws  Exception{

    }

    public void testButtons(){

        for (int i = 0 ; i <= 9 ; i++){
            mySolo.clickOnButton(Integer.toString(i));
        }
        mySolo.clickOnButton("+");
        mySolo.clickOnButton("-");
        mySolo.clickOnButton("/");
        mySolo.clickOnButton("*");

        mySolo.clickOnButton("=");
        mySolo.clickOnButton("C");
    }

    public void testInputField(){
        mySolo.clickOnButton("4");
        mySolo.clickOnButton("2");

        mySolo.getText("42");
    }

    public void testClearButton() {
        mySolo.clickOnButton("2");
        mySolo.clickOnButton("C");

        waitAndAssertEquals("0");
    }

    private void waitAndAssertEquals(String assertion){
        TextView text = (TextView) mySolo.getCurrentActivity().findViewById(R.id.textView);

        mySolo.sleep(600);

        assertEquals(assertion, text.getText());
    }

    public  void testAll(){
        mySolo.clickOnButton("2");
        mySolo.clickOnButton("+");
        mySolo.clickOnButton("8");
        mySolo.clickOnButton("=");

        waitAndAssertEquals("10");
    }

}
