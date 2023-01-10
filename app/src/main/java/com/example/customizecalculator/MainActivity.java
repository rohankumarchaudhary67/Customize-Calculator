package com.example.customizecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    // Creating objects for buttons -
    Button button_dot, button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    Button button_c, button_bracket_start, button_bracket_end, button_divide, button_multiply, button_minus;
    Button button_plus, button_equal;

    // Creating objects for textview -
    TextView input_text;
    TextView results;

    // creating objects for images -
    ImageView rough;

    // Creating a string to store buttons data -
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // assign button with find view by id -
        button_dot = findViewById(R.id.button_dot);
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_c = findViewById(R.id.button_c);
        button_divide = findViewById(R.id.button_divide);
        button_multiply = findViewById(R.id.button_multiply);
        button_minus = findViewById(R.id.button_minus);
        button_plus = findViewById(R.id.button_plus);
        button_equal = findViewById(R.id.button_equal);
        button_bracket_end = findViewById(R.id.button_bracket_end);
        button_bracket_start = findViewById(R.id.bracket_start);

        // assign rough button with image -
        rough = (ImageView) findViewById(R.id.imageView3);

        // assign textview with find view by id -
        input_text = findViewById(R.id.input_text);
        results = findViewById(R.id.results);

        // set methods for buttons (0-9)
        button_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + ".");
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "0");
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "1");
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "2");
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "3");
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "4");
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "5");
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "6");
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "7");
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "8");
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "9");
            }
        });

        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText("");
                results.setText("");
            }
        });

        rough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data.substring(0, data.length()-1));
            }
        });

        // for operators
        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "÷");
            }
        });
        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "×");
            }
        });
        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "-");
            }
        });
        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "+");
            }
        });
        button_bracket_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + "(");
            }
        });
        button_bracket_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                input_text.setText(data + ")");
            }
        });

        // Equal Button
        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input_text.getText().toString();
                data = data.replaceAll("÷", "/");
                data = data.replaceAll("×", "*");

                // Using rhino to solve calculation
                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);

                String final_result = "";

                Scriptable scriptable = rhino.initStandardObjects();
                final_result = rhino.evaluateString(scriptable, data, "JavaScript", 1, null).toString();

                if(final_result.substring(final_result.length()-2, final_result.length()-0).equals(".0")){
                    final_result = final_result.substring(0, final_result.length()-2);
                }else if(final_result.substring(final_result.length()-13, final_result.length()-0).equals("0999999999999")){
                    final_result = final_result.substring(0, final_result.length()-13) + "1";
                }

                results.setText(final_result);
            }
        });
    }
}