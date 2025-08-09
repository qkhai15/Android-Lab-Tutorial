package com.example.recyclerviewexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewexample.models.Product;
import com.example.recyclerviewexample.models.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Danh sách sản phẩm với ảnh từ URL
        productList = new ArrayList<>();
        productList.add(new Product(
                "Điện thoại LG",
                "13.3 inch, Silver",
                60000.0,
                4.3,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTE4npSQfuIQaNsYyQVpfMkLcTcBR7XG974lw&s"
        ));
        productList.add(new Product(
                "Điện thoại Samsung",
                "14 inch, Gray",
                60000.0,
                4.3,
                "https://news.khangz.com/wp-content/uploads/2022/07/dien-thoai-samsung-man-hinh-lon-1.jpg"
        ));
        productList.add(new Product(
                "Điện thoại Iphone",
                "13.3 inch, Silver",
                60000.0,
                4.3,
                "https://cdnv2.tgdd.vn/mwg-static/common/News/1569924/9.jpg"
        ));

        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }
}
