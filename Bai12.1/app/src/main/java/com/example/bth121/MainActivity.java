package com.example.bth121;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView lsv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapater;
    EditText edtwork,edthour,edtmi;
    TextView txtdate;
    Button btnadd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edthour = findViewById(R.id.edthour);
        edtmi = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnadd = findViewById(R.id.btnadd);
        lsv = findViewById(R.id.lsv);
        txtdate = findViewById(R.id.txtdate);

        sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        arraywork = new ArrayList<>();
        arraywork = loadData();
        arrAdapater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        lsv.setAdapter(arrAdapater);

        //Lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo định dạng dd/mm/yyyy
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm nay: " + simpleDateFormat.format(currentDate));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtwork.getText().toString().equals("")||edthour.getText().toString().equals("") ||edtmi.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    });
                    builder.show();
                }
                else {
                    String str = "+ " + edtwork.getText().toString().trim() + " - " + edthour.getText().toString().trim() + ":" + edtmi.getText().toString().trim();
                    arraywork.add(str);
                    arrAdapater.notifyDataSetChanged();
                    saveData();
                    edthour.setText("");
                    edtmi.setText("");
                    edtwork.setText("");
                }
            }
        });

        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete item");
                builder.setMessage("Do you want to delete this item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        arraywork.remove(position);
                        arrAdapater.notifyDataSetChanged();
                        saveData();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    // Lưu
    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        StringBuilder sb = new StringBuilder();
        for (String item : arraywork) {
            sb.append(item).append(";;"); // dấu ;; để phân cách
        }
        editor.putString("MyData", sb.toString());
        editor.apply();
    }

    // Đọc
    private ArrayList<String> loadData() {
        String data = sharedPreferences.getString("MyData", "");
        ArrayList<String> list = new ArrayList<>();
        if (!data.isEmpty()) {
            String[] items = data.split(";;");
            list.addAll(Arrays.asList(items));
        }
        return list;
    }
}