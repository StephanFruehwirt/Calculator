package at.sw2015.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class Calculator extends ActionBarActivity implements View.OnClickListener{

    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonEqual;
    private Button buttonClear;

    private TextView textView;
    private State currentState = State.INIT;

    private int firstNumber = 0;

    public enum State{
        ADD, SUB, MUL, DIV, INIT, NUM
    };

    ArrayList<Button> numberButtons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);

        textView = (TextView) findViewById(R.id.textView);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);



        generateNumberButtons();
    }

    public enum NumberStrings{
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine
    };

    public void generateNumberButtons(){
        for(int i = 0; i <= 9; i++){
            String buttonName = "button" + NumberStrings.values()[i].toString();

            int id = getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());

            Button button = (Button) findViewById(id);
            button.setOnClickListener(this);
            numberButtons.add(button);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.buttonPlus:
                clearNumberViewAndSaveNumber();
                currentState = State.ADD;
                break;
            case R.id.buttonMinus:
                clearNumberViewAndSaveNumber();
                currentState = State.SUB;
                break;
            case R.id.buttonMultiply:
                clearNumberViewAndSaveNumber();
                currentState = State.MUL;
                break;
            case R.id.buttonDivide:
                clearNumberViewAndSaveNumber();
                currentState = State.DIV;
                break;
            case R.id.buttonClear:
                clearTextView();
                break;
            case R.id.buttonEqual:
                calculateResult();
                break;
            default:
                String recentNumber = textView.getText().toString();

                if(currentState == State.INIT) {
                    recentNumber = "";
                    currentState = State.NUM;
                }

                recentNumber += clickedButton.getText().toString();
                textView.setText(recentNumber);
        }

    }

    private void clearTextView(){
        textView.setText("0");
        firstNumber = 0;
        currentState = State.INIT;
    }

    private void clearNumberViewAndSaveNumber(){
        String tempString = textView.getText().toString();
        if(!tempString.equals("")){
            firstNumber = Integer.valueOf(tempString);
        }
        textView.setText("");
    }

    private void calculateResult(){
        int secondNumber = 0;

        String tempString = textView.getText().toString();
        if(!tempString.equals("")){
            secondNumber = Integer.valueOf(tempString);
        }

        int result;

        switch (currentState){
            case ADD:
                result = Calculations.doAddition(firstNumber, secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber, secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber, secondNumber);
                break;
            case SUB:
                result = Calculations.doSubtraction(firstNumber, secondNumber);
                break;
            default:
                result = secondNumber;
        }
        textView.setText(Integer.toString(result));
    }
}
