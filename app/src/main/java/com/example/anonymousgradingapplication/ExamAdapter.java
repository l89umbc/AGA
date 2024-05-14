package com.example.anonymousgradingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.Exam;

import java.util.ArrayList;

public class ExamAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> examNames;
    private ArrayList<String> examIDs;
    private LayoutInflater inflater;

    public ExamAdapter(Context context, ArrayList<String> examNames, ArrayList<String> examIDs)
    {
        this.context = context;
        this.examNames = examNames;
        this.examIDs = examIDs;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return examIDs.size();
    }

    @Override
    public Object getItem(int position) {
        return examIDs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_exam_map, null);
        TextView text = (TextView) convertView.findViewById(R.id.map_examNameText);
        text.setText(examNames.get(position));
        return convertView;
    }
}
