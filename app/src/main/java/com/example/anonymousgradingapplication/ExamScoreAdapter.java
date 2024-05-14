package com.example.anonymousgradingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamScoreAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> studentNames, studentGrades;
    private LayoutInflater inflater;

    public ExamScoreAdapter(Context context, ArrayList<String> studentNames, ArrayList<String> studentGrades)
    {
        this.context = context;
        this.studentNames = studentNames;
        this.studentGrades = studentGrades;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return studentNames.size();
    }

    @Override
    public Object getItem(int position) {
        return studentGrades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_exam_score_map, null);
        TextView text = (TextView) convertView.findViewById(R.id.studentNameTextView);
        TextView text2 = (TextView) convertView.findViewById(R.id.studentGradeTextView);
        text.setText(studentNames.get(position));
        text2.setText(studentGrades.get(position));
        return convertView;
    }
}
