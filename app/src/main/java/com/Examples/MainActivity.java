package com.Examples;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udojava.evalex.Expression;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    public Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,addBtn,subBtn,mulBtn,divBtn,erBtn,dotBtn,eqBtn,clrBtn,brsBtn;
    public TextView tvBt,tvUp;
    public ImageView img;
    public int braces=0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeElements();
        try
        {
            btn0.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("0");
                else tvBt.setText(ss+"0");
            });
            btn1.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("1");
                else tvBt.setText(ss+"1");
            });
            btn2.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("2");
                else tvBt.setText(ss+"2");
            });
            btn3.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("3");
                else tvBt.setText(ss+"3");
            });
            btn4.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("4");
                else tvBt.setText(ss+"4");
            });
            btn5.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("5");
                else tvBt.setText(ss+"5");
            });
            btn6.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("6");
                else tvBt.setText(ss+"6");
            });
            btn7.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("7");
                else tvBt.setText(ss+"7");
            });
            btn8.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("8");
                else tvBt.setText(ss+"8");
            });
            btn9.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.equals("0")) tvBt.setText("9");
                else tvBt.setText(ss+"9");
            });
            dotBtn.setOnClickListener(view ->{
                    String ss=tvBt.getText().toString();
                    if(ss.length()==0)
                    {
                        ss="0.";
                        tvBt.setText(ss);
                    }
                    else if(ss.endsWith("(")==false || ss.endsWith(")")==false)
                    {
                        if(!ss.contains("."))tvBt.setText(ss.toString()+".");
                    }
            });
            erBtn.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.length()!=0)
                {
                    if(ss.endsWith(")")) ++braces;
                    if(ss.endsWith("(")) --braces;
                    ss=ss.substring(0,ss.length()-1);
                    tvBt.setText(ss);
                    if(ss.length()==0)
                    {
                        tvBt.setText("0");
                    }
                }

            });
            brsBtn.setOnClickListener(view -> {
                String ss=tvBt.getText().toString();
                if(ss.equals("0"))
                {
                    ss="0x(";
                    tvBt.setText(ss);
                    braces++;
                }
                else if(ss.endsWith("("))
                {
                    ss+="(";
                    tvBt.setText(ss);
                    braces++;
                }
                else if(ss.charAt(ss.length()-1)>='0' && ss.charAt(ss.length()-1)<='9')
                {
                    if(braces==0)
                    {
                        ss+="x(";
                        braces++;
                        tvBt.setText(ss);
                    }
                    else
                    {
                        ss+=")";
                        braces--;
                        tvBt.setText(ss);
                    }
                }
                else if(ss.endsWith("+") || ss.endsWith("-") || ss.endsWith("x") || ss.endsWith("/"))
                {
                    ss+="(";
                    braces++;
                    tvBt.setText(ss);
                }
                else if(ss.endsWith(")"))
                {
                    if(braces==0)
                    {
                        ss+="x(";
                        braces++;
                        tvBt.setText(ss);
                    }
                    else
                    {
                        ss+=")";
                        tvBt.setText(ss);
                        braces--;
                    }
                }

            });
            eqBtn.setOnClickListener(view -> {

                if(tvBt.getText()!=null)
                {
                    try {
                        String ss=tvBt.getText().toString();
                        ss=ss.replaceAll("x","*");
                        java.math.BigDecimal result=null;
                        Expression expression  = new Expression(ss);
                        if(ss.contains("/"))    expression.setPrecision(3);
                        else expression.setPrecision(10000000);
                        result=expression.eval();
                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        ss=df.format(result);
                        tvUp.setText(ss);
                        tvBt.setText("0");

                    }catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        img=findViewById(R.id.imageView2);
                        img.setImageResource(R.drawable.animraven);
                        AnimationDrawable animRaven =(AnimationDrawable)img.getDrawable();
                        animRaven.setOneShot(true);
                        animRaven.start();
                    }

                }
            });
            addBtn.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.endsWith("(")==false) {
                    if (ss.endsWith(".")) {
                        ss += "0";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("-") || ss.endsWith("x") || ss.endsWith("/")) {
                        ss = ss.substring(0, ss.length() - 1);
                        ss += "+";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("+") == false) {
                        tvBt.append("+");
                    }
                }
            });
            subBtn.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                    if (ss.endsWith(".")) {
                        ss += "0";
                        tvBt.setText(ss);
                    }
                    if (ss.equals("0")) {
                        ss += "-1";
                        tvBt.setText("-1");
                    } else if (ss.endsWith("+") || ss.endsWith("x") || ss.endsWith("/")) {
                        ss = ss.substring(0, ss.length() - 1);
                        ss += "-";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("-") == false) {
                        tvBt.append("-");
                    }
            });
            mulBtn.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.endsWith("(")==false) {
                    if (ss.endsWith(".")) {
                        ss += "0";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("-") || ss.endsWith("+") || ss.endsWith("/")) {
                        ss = ss.substring(0, ss.length() - 1);
                        ss += "x";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("x") == false) {
                        tvBt.append("x");
                    }
                }
            });
            divBtn.setOnClickListener(view ->{
                String ss=tvBt.getText().toString();
                if(ss.endsWith("(")==false) {
                    if (ss.endsWith(".")) {
                        ss += "0";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("-") || ss.endsWith("x") || ss.endsWith("+")) {
                        ss = ss.substring(0, ss.length() - 1);
                        ss += "/";
                        tvBt.setText(ss);
                    } else if (ss.endsWith("/") == false) {
                        tvBt.append("/");
                    }
                }
            });
            clrBtn.setOnClickListener(view -> {
                tvBt.setText("0");
                tvUp.setText(null);
                braces=0;
            });

        }catch (Exception e)
        {

        }
    }
    void initializeElements()
    {
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        addBtn=findViewById(R.id.addBtn);
        subBtn=findViewById(R.id.subBtn);
        divBtn=findViewById(R.id.divBtn);
        mulBtn=findViewById(R.id.mulBtn);
        erBtn=findViewById(R.id.erBtn);
        dotBtn=findViewById(R.id.dotBtn);
        eqBtn=findViewById(R.id.eqBtn);
        tvBt=findViewById(R.id.textView);
        tvBt.setText("0");
        tvUp=findViewById(R.id.textView2);
        clrBtn=findViewById(R.id.clrBtn);
        brsBtn=findViewById(R.id.brsBtn);
        tvBt.setMovementMethod(new ScrollingMovementMethod());
        tvBt.setHorizontallyScrolling(true);
        tvUp.setMovementMethod(new ScrollingMovementMethod());
        tvUp.setHorizontallyScrolling(true);
    }
}