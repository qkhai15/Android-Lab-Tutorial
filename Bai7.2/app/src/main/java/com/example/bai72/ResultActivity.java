package com.example.bai72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    EditText edtkq;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        edtkq = findViewById(R.id.edtkq);
        btnback = findViewById(R.id.btnback);

        Intent yourIntent = getIntent();
        Bundle yourBundle = yourIntent.getBundleExtra("mybackage");
        if (yourBundle != null) {
            int a = yourBundle.getInt("soa");
            int b = yourBundle.getInt("sob");

            String kq = "";
            if (a == 0 && b == 0) {
                kq = "Vô số nghiệm";
            } else if (a == 0 && b != 0) {
                kq = "Vô nghiệm";
            } else {
                DecimalFormat dcf = new DecimalFormat("0.##");
                double result = -b * 1.0 / a; // Chuyển sang double để tránh lỗi chia nguyên
                kq = dcf.format(result);
            }

            edtkq.setText(kq);
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại MainActivity
            }
        });
    }
}