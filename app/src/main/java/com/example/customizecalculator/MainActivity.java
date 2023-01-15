package com.example.customizecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @SuppressLint("SetTextI18n")
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
        rough = findViewById(R.id.imageView3);

        // assign textview with find view by id -
        input_text = findViewById(R.id.input_text);
        results = findViewById(R.id.results);

        // set methods for buttons (0-9)
        button_dot.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + ".");
        });

        button_0.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "0");
        });

        button_1.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "1");
        });

        button_2.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "2");
        });

        button_3.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "3");
        });

        button_4.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "4");
        });

        button_5.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "5");
        });

        button_6.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "6");
        });

        button_7.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "7");
        });

        button_8.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "8");
        });

        button_9.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "9");
        });

        button_c.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText("");
            results.setText("");
        });

        rough.setOnClickListener(view -> {
            data = input_text.getText().toString();
            // Prevent from crash the application
            if(data.equals("")){
                Toast toast = Toast.makeText(this, "Already 0", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                input_text.setText(data.substring(0, data.length()-1));
            }
        });


        // for operators
        button_divide.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "÷");
        });
        button_multiply.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "×");
        });
        button_minus.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "-");
        });
        button_plus.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "+");
        });
        button_bracket_start.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + "(");
        });
        button_bracket_end.setOnClickListener(view -> {
            data = input_text.getText().toString();
            input_text.setText(data + ")");
        });
        // Equal Button
        button_equal.setOnClickListener(view -> {
            data = input_text.getText().toString();
            data = data.replaceAll("÷", "/");
            data = data.replaceAll("×", "*");

            // Using rhino to solve calculation
            Context rhino = Context.enter();
            rhino.setOptimizationLevel(-1);

            String final_result;

            Scriptable scriptable = rhino.initStandardObjects();
            final_result = rhino.evaluateString(scriptable, data, "JavaScript", 1, null).toString();

            // Solve some error and bugs during the calculation
            if(final_result.startsWith(".0", final_result.length()-2)){
                final_result = final_result.substring(0, final_result.length()-2);
            }else if(final_result.startsWith("0999999999999", final_result.length()-13)){
                final_result = final_result.substring(0, final_result.length()-13) + "1";
            }
            results.setText(final_result);
        });
    }
}