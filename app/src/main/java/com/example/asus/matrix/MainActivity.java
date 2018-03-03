package com.example.asus.matrix;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.widget.GridLayout.CENTER;
import static android.widget.GridLayout.VERTICAL;
import static android.widget.GridLayout.spec;

public class MainActivity extends AppCompatActivity {
    private ContentFrameLayout contentFrameLayout;
    private Button [][] btnArr;
    private Button BaseBtn;
    private Drawable baseButtonBackGround;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentFrameLayout=findViewById(android.R.id.content);
        BaseBtn=new Button(this);
        baseButtonBackGround=BaseBtn.getBackground();
        //create on click listener
        View.OnClickListener onclickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view instanceof Button){
                    Button btn=(Button)view;
                    String str=btn.getTag().toString();
                    colorTableView(str);
                }
            }
        };
        //main linear layout
        LinearLayout mainLinearLayout=new LinearLayout(this);
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(mainLinearLayout,mainLayoutParams);
        TextView txt=new TextView(this);
        LinearLayout.LayoutParams txtParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt.setLayoutParams(txtParams);
        txt.setTextSize(getResources().getDimension(R.dimen.text_size));
        txt.setText("MATRIX");
        txt.setGravity(Gravity.CENTER_HORIZONTAL);
        mainLinearLayout.addView(txt);
        //first table layout
        TableLayout tableLayout=new TableLayout(this);
        TableLayout.LayoutParams layoutParams=new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tableLayout.setLayoutParams(layoutParams);
        int counter =0;
        for (int i = 0; i <2 ; i++) {
            TableRow tr=new TableRow(this);
            for (int j = 0; j <2 ; j++) {
                Button btn=new Button(this);
                String str="string"+counter;
                btn.setText(getResources().getString(getResources().getIdentifier(str,"string",getPackageName())));
                btn.setTag(""+counter);
                btn.setOnClickListener(onclickListener);
                tr.addView(btn);
                counter++;
            }
            tr.setGravity(Gravity.CENTER);
            tr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            tableLayout.addView(tr);
            tableLayout.setColumnStretchable(i,true);
        }
        tableLayout.setGravity(Gravity.CENTER);
        mainLinearLayout.addView(tableLayout);
        //Create and add Second Table Layout with buttons
        mainLinearLayout.addView(createTableLayout(5));
    }
    public TableLayout createTableLayout(int number){
        TableLayout tableLayout=new TableLayout(this);
        TableLayout.LayoutParams layoutParams=new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        tableLayout.setLayoutParams(layoutParams);
        btnArr=new Button[number][number];
        for (int i = 0; i <btnArr.length ; i++) {
            TableRow tr=new TableRow(this);
            for (int j = 0; j <btnArr.length ; j++) {
                btnArr[i][j]=new Button(this);
                btnArr[i][j].setText("");
                btnArr[i][j].setPadding(5,5,5,5);
                tr.addView(btnArr[i][j]);
            }
            tr.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams trParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(5,5,5,5);
            tr.setLayoutParams(trParams);
            tableLayout.addView(tr);
            tableLayout.setColumnShrinkable(i,true);
            tableLayout.setColumnStretchable(i,true);
        }
        tableLayout.setGravity(Gravity.CENTER);
        return tableLayout;
    }
    //goes to the correct function
    public void colorTableView(String string){
        switch(string){
            case ""+0:{
                colorUpperTriangle();
                break;
            }
            case ""+1:{
                colorLowerTriangle();
                break;
            }
            case ""+2:{
                colorDiagonals();
                break;
            }
            case ""+3:{
                colorBorder();
                break;
            }
        }

    }
    public void colorUpperTriangle(){
        restart(contentFrameLayout);
        for (int i = 0; i < btnArr.length ; i++) {
            for (int j = 0; j <btnArr[i].length ; j++) {
                if(j>i || i==j){
                    btnArr[i][j].setBackgroundResource(R.drawable.button_red);
                }
            }
        }
    }
    public void colorLowerTriangle(){
        restart(contentFrameLayout);
        for (int i = 0; i < btnArr.length ; i++) {
            for (int j = 0; j <btnArr[i].length ; j++) {
                if(j<i || i==j){
                    btnArr[i][j].setBackgroundResource(R.drawable.button_red);
                }
            }
        }
    }
    public void colorDiagonals(){
        restart(contentFrameLayout);
        for (int i = 0; i < btnArr.length ; i++) {
            for (int j = 0; j <btnArr[i].length ; j++) {
                if(j+i==btnArr.length-1 || i==j){
                    btnArr[i][j].setBackgroundResource(R.drawable.button_red);
                }
            }
        }
    }
    public void colorBorder(){
        restart(contentFrameLayout);
        for (int i = 0; i < btnArr.length ; i++) {
            for (int j = 0; j <btnArr[i].length ; j++) {
                if(j==0 || i==0 || i== btnArr.length-1 || j==btnArr.length-1){
                    btnArr[i][j].setBackgroundResource(R.drawable.button_red);
                }
            }
        }
    }
    //restarts the view
    public void restart(ViewGroup parent){
        for (int i = 0; i <parent.getChildCount() ; i++) {
            View v=parent.getChildAt(i);
            if(v instanceof Button){
                Button btn=(Button)v;
                if(!btn.getBackground().equals(baseButtonBackGround));
                btn.setBackground(baseButtonBackGround);
            }
            if(v instanceof ViewGroup){
                restart((ViewGroup)v);
            }
        }
    }
}
