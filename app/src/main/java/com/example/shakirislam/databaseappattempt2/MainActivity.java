package com.example.shakirislam.databaseappattempt2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd, buttonShow, buttonClear;
    private EditText editText;
    private TextView textView;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        buttonAdd = (Button) findViewById(R.id.button);
        buttonShow = (Button) findViewById(R.id.button2);
        buttonClear = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView);


        textView.setText("");
        dbhelper = new DatabaseHelper(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueEntry = editText.getText().toString();

                if(editText.length() != 0){
                    addData(valueEntry);
                    editText.setText("");
                }else{
                    toastMessage("You must put something in the text field!");
                }
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean work = dbhelper.resetTable();
                if(work){
                    toastMessage("Data Successfully cleared");
                }else{
                    toastMessage("Something went wrong");
                }
            }
        });


    }

private void addData(String name){
        boolean insertData = dbhelper.addData(name);

        if(insertData){
            toastMessage("Data successfully inserted");
        }
        else{
            toastMessage("Something went wrong");
        }
}


    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

public void showData (){
    Cursor data = dbhelper.getData();
    ArrayList<String> listData = new ArrayList<>();
    while(data.moveToNext()) {
        //column 1 is technically the second column because of index numbering
        listData.add(data.getString(1));
    }

    String allValues = "";
    for(int i =0;i < listData.size();i++){

        String temp;
        temp = (i + 1) + " " + listData.get(i) + ", ";
        allValues = allValues + temp;

    }

    textView.setText(allValues);
}
}
