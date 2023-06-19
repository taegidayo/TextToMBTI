package com.example.test_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Log  extends Activity {
ImageButton btn_return;

    DatabaseHelper dbHelper;
    SQLiteDatabase sqlDB;
    Cursor cursor;
    ListView listView;


    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_view);
        btn_return=findViewById(R.id.return_button);
        dbHelper= new DatabaseHelper(this);

        listView= findViewById(R.id.listView);


        sqlDB = dbHelper.getReadableDatabase();
        ArrayList<String> text=new ArrayList<>();
        ArrayList<String> mbti=new ArrayList<>();
        ArrayList<Float> mind=new ArrayList<>();
        ArrayList<Float> energy=new ArrayList<>();
        ArrayList<Float> nature=new ArrayList<>();
        ArrayList<Float> tactics=new ArrayList<>();
        ArrayList<String> result=new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, result);
        listView.setAdapter(adapter);
        cursor = sqlDB.rawQuery("SELECT * FROM USER ORDER BY id DESC;", null);

        while (cursor.moveToNext()) {
            result.add("입력한 텍스트:"+cursor.getString(1)==""?"입력한 글이 없습니다.":cursor.getString(1)+"\n결과:"+cursor.getString(2)+"\n각각의 비율\nE:"+cursor.getString(3)+"\nN:"+cursor.getString(4)+"\nF:"+cursor.getString(5)+"\nJ:"+cursor.getString(6));
            text.add(cursor.getString(1)) ;
            mbti.add(cursor.getString(2));
            mind.add(cursor.getFloat(3));
            energy.add(cursor.getFloat(4));
            nature.add(cursor.getFloat(5));
            tactics.add(cursor.getFloat(6));

        }
        adapter.notifyDataSetChanged();



        dbHelper = new DatabaseHelper(this);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
