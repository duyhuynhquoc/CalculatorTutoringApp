package com.duyhq.calculatortutoringapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var inputTV: TextView? = null;

    var result: Double = 0.0;
    var inputNumber: Double = 0.0;

    var isFirstNumber: Boolean = true;
    var isOperatorPressed: Boolean = false;

    var lastOperator: String = "";
    var lastNumber: Double = 0.0;
    var lastAddOrMinus: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTV = findViewById(R.id.inputTV);
    }

    fun onAllClear(view: View){
        inputTV?.text = "";

        result = 0.0;
        inputNumber = 0.0;
        isFirstNumber = true;
        isOperatorPressed = false;

        lastOperator = ""
        lastNumber = 0.0
        lastAddOrMinus = ""
    }

    fun onDigit(view: View) {
        if (isOperatorPressed) {
            inputTV?.text = "";
        }
        isOperatorPressed = false;

        inputTV?.append((view as Button).text);
    }

    fun calculateAddMinusEqual() {
        if (isFirstNumber) {
            result += inputNumber;
            isFirstNumber = false;
        } else {
            if (lastOperator == "+") {
                result += inputNumber;
            } else if (lastOperator == "-") {
                result -= inputNumber;
            } else if (lastOperator == "*") {
                lastNumber *= inputNumber;

                if (lastAddOrMinus == "+") {
                    result += lastNumber;
                } else {
                    result -= lastNumber;
                }
            }
        }
    }


    fun onAdd(view: View) {
        isOperatorPressed = true;

        inputNumber = inputTV?.text.toString().toDouble();

        calculateAddMinusEqual();

        lastOperator = "+";
        lastAddOrMinus = "+";

        inputTV?.text = result.toString();
    }

    fun onMinus(view: View) {
        isOperatorPressed = true;

        inputNumber = inputTV?.text.toString().toDouble();

        calculateAddMinusEqual();

        lastOperator = "-";
        lastAddOrMinus = "-";

        inputTV?.text = result.toString();
    }

    fun onMultiply(view: View) {
        isOperatorPressed = true;

        inputNumber = inputTV?.text.toString().toDouble();

        if (lastOperator == "+" || lastOperator == "-") {
            lastNumber = inputNumber;
        } else {
            lastNumber *= inputNumber;
        }

        lastOperator = "*";
        inputTV?.text = lastNumber.toString();
    }

    fun onEqual(view: View) {
        isOperatorPressed = true;

        inputNumber = inputTV?.text.toString().toDouble();
        Log.i("number", inputNumber.toString());

        calculateAddMinusEqual();

        inputTV?.text = result.toString();
    }
}