package com.example.bth133;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String[] arrayName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6"};
    public static int[] imageName = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6};
    GridView gridViewDemo;
    //Sử dụng MyArrayAdapter thay thì ArrayAdapter
    MyArrayAdapter adapterDanhSach;
    ArrayList<Image> arrimage;

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

        gridViewDemo = (GridView) findViewById(R.id.grid1);
        arrimage = new ArrayList<Image>();
        adapterDanhSach = new MyArrayAdapter(MainActivity.this,
                R.layout.listitem, // lấy custom layout
                arrimage); //thiết lập data source*/
        gridViewDemo.setAdapter(adapterDanhSach);

        for (int i = 0; i < imageName.length; i++) {
            Image myimage = new Image();
            myimage.setName(arrayName[i]);
            myimage.setImg(imageName[i]);
            arrimage.add(myimage);
            //gọi hàm cập nhật giao diện
            adapterDanhSach.notifyDataSetChanged();
        }

        gridViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(MainActivity.this, SubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("TITLE", position);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

    }
}