package com.example.android.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    String string = "";
    public void onClick(View view){

        TextView textView = (TextView) findViewById(R.id.edit);
        TextView textView1 = (TextView)findViewById(R.id.edit1);

         if (view.getId() == R.id.button31) {
             textView.setText(textView.getText() + "1 ");
             string = string + "1";
         }
         else if (view.getId() == R.id.button32 ) {
             textView.setText(textView.getText() + "2 ");
             string = string + "2";
         }
         else if (view.getId() == R.id.button33 ) {
             textView.setText(textView.getText() + "3 ");
             string = string + "3";
         }
         else if (view.getId() == R.id.button21 ) {
             textView.setText(textView.getText() + "4 ");
             string = string + "4";
         }
         else if (view.getId() == R.id.button22 ) {
             textView.setText(textView.getText() + "5 ");
             string = string + "5";
         }
         else if (view.getId() == R.id.button23) {
             textView.setText(textView.getText() + "6 ");
             string = string + "6";
         }
         else if (view.getId() == R.id.button11) {
             string = string + "7";
             textView.setText(textView.getText() + "7 ");
         }
         else if (view.getId() == R.id.button12) {
             string = string + "8";
             textView.setText(textView.getText() + "8 ");
         }
         else if (view.getId() == R.id.button13 ) {
             string = string + "9";
             textView.setText(textView.getText() + "9 ");
         }
         else if (view.getId() == R.id.button42 ) {
             string = string + "0";
             textView.setText(textView.getText() + "0 ");
         }
         else if (view.getId() == R.id.button51 ) {
             String str = textView.getText().toString();
             textView.setText(str.substring(0, str.length() - 1));
             string = string.substring(0, string.length() - 1);
         }
         else if (view.getId() == R.id.button52 ) {
             string = string + "+";
             textView.setText(textView.getText() + "+");
         }
         else if (view.getId() == R.id.button53) {
             string = string + "-";
             textView.setText(textView.getText() + "-");
         }
         else if (view.getId() == R.id.button54) {
             string = string + "*";
             textView.setText(textView.getText() + "*");
         }
         else if (view.getId() == R.id.button55 ) {
             string = string + "/";
             textView.setText(textView.getText() + "/ ");
         }
         else if (view.getId() == R.id.button41 ) {
             string = string + "(";
             textView.setText(textView.getText() + "(");
         }
         else if (view.getId() == R.id.button4 ) {
             string = string + ")";
             textView.setText(textView.getText() + ")");
         }
         else if (view.getId() == R.id.button34 ) {
             string = "";
             textView.setText("");
         }
        else if (view.getId() == R.id.button43){
            textView1.setText(evaluate(string) + "");
        }
    }

    public static int evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }
    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

}
