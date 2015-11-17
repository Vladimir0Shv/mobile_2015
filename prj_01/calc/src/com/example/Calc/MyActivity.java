package com.example.Calc;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {

    EditText calcDialogDisplay;

    TextView allClear;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView division;
    TextView four;
    TextView five;
    TextView six;
    TextView multiply;
    TextView one;
    TextView two;
    TextView three;
    TextView subtract;
    TextView zero;
    TextView equals;
    TextView add;

    ArrayList<Float> result = new ArrayList<Float>();

    float number1;

    float number2;

    int currentOperation = 0;
    int nextOperation;

    final static int ADD = 1;

    final static int SUBTRACT = 2;

    final static int MULTIPLY = 3;

    final static int DIVISION = 4;

    final static int EQUALS = 5;

    final static int CLEAR = 1;
    final static int DONT_CLEAR = 0;
    int clearCalcDisplay = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        calcDialogDisplay = (EditText) findViewById(R.id.editText1);
        allClear = (TextView) findViewById(R.id.button22);
        seven = (TextView) findViewById(R.id.button11);
        eight = (TextView) findViewById(R.id.button12);
        nine = (TextView) findViewById(R.id.button13);
        division =(TextView) findViewById(R.id.button9);
        four = (TextView) findViewById(R.id.button6);
        five = (TextView) findViewById(R.id.button7);
        six =(TextView) findViewById(R.id.button8);
        multiply = (TextView) findViewById(R.id.button10);
        one = (TextView) findViewById(R.id.button1);
        two = (TextView) findViewById(R.id.button2);
        three = (TextView) findViewById(R.id.button3);
        subtract = (TextView) findViewById(R.id.button5);
        zero = (TextView) findViewById(R.id.button20);
        equals = (TextView) findViewById(R.id.button14);
        add = (TextView) findViewById(R.id.button4);
        calcDialogDisplay.setKeyListener(DigitsKeyListener.getInstance(true,true));
        registerListeners();
    }

    public void registerListeners () {

        seven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("8");

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("9");

            }
        });

        division.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(DIVISION);
            }
        });

        allClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcDialogDisplay.setText("");
                number1 = 0;
                number2 = 0;
                result.removeAll(result);
                currentOperation = 0;
                nextOperation = 0;
            }
        });

        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("4");

            }
        });

        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("5");

            }
        });

        six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("6");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("0");
            }
        });


        multiply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(MULTIPLY);
                calcDialogDisplay.setText("");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("1");

            }
        });

        two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("2");

            }
        });

        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("3");

            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(SUBTRACT);
                calcDialogDisplay.setText("");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(ADD);
                calcDialogDisplay.setText("");
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(EQUALS);

            }
        });
    }



    private void calcLogic(int operator) {

        result.add(Float.parseFloat(calcDialogDisplay.getText().toString()));

        if (operator != EQUALS) {
            nextOperation = operator;
        }else if (operator == EQUALS){
            nextOperation = 0;
        }

        switch (currentOperation) {

            case ADD:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 + number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;

            case SUBTRACT:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 - number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;

            case MULTIPLY:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 * number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;
            case DIVISION:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 / number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;

        }

        clearCalcDisplay = CLEAR;
        currentOperation = nextOperation;
        if (operator == EQUALS) {
            number1 = 0;
            number2 = 0;
            result.removeAll(result);
        }
    }

}