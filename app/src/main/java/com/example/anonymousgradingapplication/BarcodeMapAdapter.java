package com.example.anonymousgradingapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BarcodeMapAdapter extends BaseAdapter
{

    private Context context;
    private List<String> studentNames;
    private List<Bitmap> barcodeBitmaps;

    private LayoutInflater inflater;

    public BarcodeMapAdapter(Context context, List<String> fruitnames, List<Bitmap> images){
        this.context = context;
        this.studentNames = fruitnames;
        this.barcodeBitmaps = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return studentNames.size();
    }

    @Override
    public Object getItem(int position) {
        return studentNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_student_barcode_map, null);
        TextView text = (TextView) convertView.findViewById(R.id.studentNameText);
        ImageView image = (ImageView) convertView.findViewById(R.id.barcodeImage);
        text.setText(studentNames.get(position));
        image.setImageBitmap(barcodeBitmaps.get(position));
        return convertView;
    }
}