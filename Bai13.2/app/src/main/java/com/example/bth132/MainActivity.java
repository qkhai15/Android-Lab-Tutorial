package com.example.bth132;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String namephone[] = {"Điện thoại Iphone", "Điện thoại Samsung", "Điện thoại Xiaomi"};
    int imagephone[] = {R.drawable.iphone, R.drawable.samsung, R.drawable.xiaomi};

    ArrayList<Phone> mylist;
    MyArrayAdapter myArrayAdapter;
    ListView lsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lsv = findViewById(R.id.lsv);
        mylist = new ArrayList<>();
        for (int i = 0; i <namephone.length;i++) {
            mylist.add(new Phone(imagephone[i], namephone[i]));
        }

        myArrayAdapter = new MyArrayAdapter(this, mylist, R.layout.layout_listview);
        lsv.setAdapter(myArrayAdapter);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("name", namephone[position]);
                startActivity(intent);
            }
        });

    }
}