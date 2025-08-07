package com.example.bai121;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView txtSelection;
    private ListView lvPhoneList;
    private String[] phoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSelection = findViewById(R.id.selection);
        lvPhoneList = findViewById(R.id.lv1);

        if (txtSelection == null || lvPhoneList == null) {
            Toast.makeText(this, "Lỗi: Không tìm thấy TextView hoặc ListView!", Toast.LENGTH_LONG).show();
            return;
        }

        phoneList = new String[]{"Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730", "Sony Xperia XZ", "HTC One E9"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneList);
        lvPhoneList.setAdapter(adapter);

        lvPhoneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (position >= 0 && position < phoneList.length) {
                        txtSelection.setText("Vị trí " + position + " : " + phoneList[position]);
                    } else {
                        txtSelection.setText("Vị trí không hợp lệ!");
                    }
                } catch (Exception e) {
                    txtSelection.setText("Lỗi: " + e.getMessage());
                    Toast.makeText(MainActivity.this, "Đã xảy ra lỗi, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}