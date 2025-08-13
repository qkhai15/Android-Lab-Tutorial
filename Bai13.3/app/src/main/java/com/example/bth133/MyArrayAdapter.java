package com.example.bth133;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Image> {
    Activity context = null;
    ArrayList<Image> myArray = null;
    int LayoutId;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Image> myArray) {
        super(context, layoutId, myArray);
        this.context = context;
        this.LayoutId = layoutId;
        this.myArray = myArray;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);
        final Image myimage = myArray.get(position);
        //dòng lệnh lấy ImageView ra để hiển thị hình ảnh
        final ImageView imgitem = (ImageView)convertView.findViewById(R.id.imageView1);
        imgitem.setImageResource(myimage.getImg());
        //dòng lệnh lấy TextView ra để hiển thị tên Ảnh
        final TextView myname = (TextView)convertView.findViewById(R.id.textView1);
        myname.setText(myimage.getName());
        return convertView;
    }
}
