package com.example.bai72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb;
    Button btnkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btnkq = findViewById(R.id.btnkq);

        btnkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aStr = edta.getText().toString().trim();
                String bStr = edtb.getText().toString().trim();

                if (aStr.isEmpty() || bStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập a và b!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int a = Integer.parseInt(aStr);
                    int b = Integer.parseInt(bStr);
                    Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("soa", a);
                    bundle1.putInt("sob", b);
                    myIntent.putExtra("mybackage", bundle1);
                    startActivity(myIntent);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số nguyên!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}