package com.example.anonymousgradingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAdapter extends BaseAdapter {
    private ArrayList<String> courseNames, courseIDs;
    private Context context;
    private LayoutInflater inflater;

    public CourseAdapter(Context context, ArrayList<String> courseNames, ArrayList<String> courseIDs)
    {
        this.courseNames = courseNames;
        this.courseIDs = courseIDs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return courseIDs.size();
    }

    @Override
    public Object getItem(int position) {
        return courseIDs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_course_map, null);
        TextView text = (TextView) convertView.findViewById(R.id.map_courseNameTextView);
        text.setText(courseNames.get(position));
        return convertView;
    }
}
