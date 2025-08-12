package com.example.bai20;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    // Khai báo Activity để lưu trữ context của MainActivity
    Activity contextCha;

    // Constructor này được truyền vào là MainActivity
    public MyAsyncTask(Activity ctx) {
        contextCha = ctx;
    }

    // Hàm này sẽ được thực hiện đầu tiên
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }

    // Sau đó tới hàm doInBackground
    // Tuyệt đối không được cập nhật giao diện trong hàm này
    @Override
    protected Void doInBackground(Void... arg0) {
        for (int i = 0; i <= 100; i++) {
            // Nghỉ 100 milisecond thì tiến hành update UI
            SystemClock.sleep(100);
            // Khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    /**
     * Ta cập nhật giao diện trong hàm này
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Thông qua contextCha để lấy được control trong MainActivity
        ProgressBar paCha = (ProgressBar) contextCha.findViewById(R.id.progressBar1);
        // Vì publishProgress chỉ truyền 1 đối số
        // Nên mảng values chỉ có 1 phần tử
        int giatri = values[0];
        // Tăng giá trị của ProgressBar lên
        paCha.setProgress(giatri);
        // Đồng thời hiển thị giá trị là % lên TextView
        TextView txtmsg = (TextView) contextCha.findViewById(R.id.textView1);
        txtmsg.setText(giatri + "%");
    }

    /**
     * Sau khi tiến trình thực hiện xong thì hàm này xảy ra
     */
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Toast.makeText(contextCha, "Update xong roi do!", Toast.LENGTH_LONG).show();
    }
}

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    // Khai báo MyAsyncTask
    MyAsyncTask mytt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.button1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doStart();
            }
        });
    }

    private void doStart() {
        // Truyền this (chính là MainActivity hiện tại) qua background Thread
        mytt = new MyAsyncTask(this);
        // Kích hoạt Tiến trình
        // Khi gọi hàm này thì onPreExecute của mytt sẽ thực thi trước
        mytt.execute();
    }
}