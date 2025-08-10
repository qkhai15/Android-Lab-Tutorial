package com.example.bai16;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb, edtkq;
    Button btntong, btnclear;
    TextView txtlichsu;
    String lichsu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtkq = findViewById(R.id.edtkq);
        btntong = findViewById(R.id.btntong);
        btnclear = findViewById(R.id.btnclear);
        txtlichsu = findViewById(R.id.txtlichsu);

        // Lấy lại dữ liệu từ SharedPreferences
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        lichsu = myprefs.getString("ls", "");
        txtlichsu.setText(lichsu);

        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edta.getText().toString());
                    int b = Integer.parseInt(edtb.getText().toString());
                    int kq = a + b;
                    edtkq.setText(String.valueOf(kq));

                    // Thêm vào lịch sử với xuống dòng
                    if (!lichsu.isEmpty()) {
                        lichsu += "\n";
                    }
                    lichsu += a + " + " + b + " = " + kq;
                    txtlichsu.setText(lichsu);
                } catch (NumberFormatException e) {
                    // Xử lý nếu input không phải số (tùy chọn, có thể thêm Toast)
                }
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu = "";
                txtlichsu.setText(lichsu);
                // Lưu ngay để đảm bảo xóa sạch
                saveLichsu();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLichsu();
    }

    private void saveLichsu() {
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls", lichsu);
        myedit.apply(); // Sử dụng apply() thay commit() để async và an toàn hơn
    }
}