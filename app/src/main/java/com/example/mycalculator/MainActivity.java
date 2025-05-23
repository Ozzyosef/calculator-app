
package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    double num1 = 0, num2 = 0;
    String operator = "";
    boolean isSecondNumber = false;
    boolean operatorJustPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        int[] numberButtonIds = {
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (operatorJustPressed) {
                    textView.setText("");
                    operatorJustPressed = false;
                }
                textView.append(b.getText());
            }
        };

        for (int id : numberButtonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(numberClickListener);
        }

        findViewById(R.id.buttonAdd).setOnClickListener(opClickListener);
        findViewById(R.id.buttonSub).setOnClickListener(opClickListener);
        findViewById(R.id.buttonMul).setOnClickListener(opClickListener);
        findViewById(R.id.buttonDiv).setOnClickListener(opClickListener);
        findViewById(R.id.buttonEq).setOnClickListener(eqClickListener);
        findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = 0;
                num2 = 0;
                operator = "";
                isSecondNumber = false;
                operatorJustPressed = false;
                textView.setText("");
            }
        });
    }

    View.OnClickListener opClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (operatorJustPressed) {
                // if user pressed 2 operations --> error
                textView.setText("Error");
                num1 = 0;
                num2 = 0;
                operator = "";
                isSecondNumber = false;
                operatorJustPressed = false;
                return;
            }

            Button b = (Button) v;
            operator = b.getText().toString();

            if (!textView.getText().toString().isEmpty()) {
                try {
                    num1 = Double.parseDouble(textView.getText().toString());
                } catch (NumberFormatException e) {
                    textView.setText("Error");
                    return;
                }
                isSecondNumber = true;
                textView.setText(operator); // shows the operation
                operatorJustPressed = true;
            }
        }
    };

    View.OnClickListener eqClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!textView.getText().toString().isEmpty()) {
                num2 = Double.parseDouble(textView.getText().toString());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textView.setText("Error");
                            return;
                        }
                        break;
                    default:
                        textView.setText("Invalid");
                        return;
                }

                textView.setText(String.valueOf(result));
                isSecondNumber = false;
                operatorJustPressed = false;
            }
        }
    };
}
