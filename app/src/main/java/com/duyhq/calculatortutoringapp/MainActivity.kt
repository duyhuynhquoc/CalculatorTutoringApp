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

    var isOperatorPressed: Boolean = false;
    var isEqualPressed: Boolean = false;

    var lastOperator: String = "+";
    var lastNumber: Double = 0.0;
    var lastAddOrMinus: String = "+";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTV = findViewById(R.id.inputTV);

        supportActionBar?.hide();
    }

    fun onAllClear(view: View) {
        inputTV?.text = "";
        inputNumber = 0.0;

        reset();

        isOperatorPressed = false;
        isEqualPressed = false;
    }

    fun onDigit(view: View) {
        if (isOperatorPressed) {
            inputTV?.text = "";
        }

        isOperatorPressed = false;

        inputTV?.append((view as Button).text);
        isEqualPressed = false;
    }

    fun calculateAddMinusEqual() {
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

    fun calculateMultiplyDivide() {
        if (lastOperator == "*") {
            lastNumber *= inputNumber;
        } else if (lastOperator == "/") {
            lastNumber /= inputNumber
        }
    }


    fun onAdd(view: View) {
        isOperatorPressed = true;

        getInputNumber();

        calculateAddMinusEqual();

        lastOperator = "+";
        lastAddOrMinus = "+";

        inputTV?.text = result.toString();
        isEqualPressed = false;
    }

    fun onMinus(view: View) {
        isOperatorPressed = true;

        getInputNumber();

        calculateAddMinusEqual();

        lastOperator = "-";
        lastAddOrMinus = "-";

        inputTV?.text = result.toString();
        isEqualPressed = false;
    }

    fun onMultiply(view: View) {
        isOperatorPressed = true;

        getInputNumber();

        if (lastOperator == "+" || lastOperator == "-") lastNumber =
            inputNumber;

        calculateMultiplyDivide()

        lastOperator = "*";
        inputTV?.text = lastNumber.toString();

        isEqualPressed = false;
    }

    fun onEqual(view: View) {
        isOperatorPressed = true;

        if (!isEqualPressed) {
            inputNumber = inputTV?.text.toString().toDouble();
        }

        calculateAddMinusEqual();

        inputTV?.text = result.toString();
        isEqualPressed = true;

        reset();
    }

    fun getInputNumber() {
        if (!isEqualPressed) {
            inputNumber = inputTV?.text.toString().toDouble();
        }
    }

    fun reset() {
        inputNumber = result;
        result = 0.0;
        lastNumber = 0.0;
        lastOperator = "+";
        lastAddOrMinus = "+"
    }
}