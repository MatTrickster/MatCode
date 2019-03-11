package com.example.nafees.matcode;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class Problem {

    String name, content, input, output, constraints, sampleInput, sampleOutput;

    Problem(){}

    public void setName(String name){
        this.name = name;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setInput(String input){
        this.input = input;
    }

    public void setOutput(String output){
        this.output = output;
    }

    public void setConstraints(String constraints){
        this.constraints = constraints;
    }

    public void setSampleInput(String input){
        this.sampleInput = input;
    }

    public void setSampleOutput(String output){
        this.sampleOutput = output;
    }

    public String getName(){ return this.name; }

    public String getContent(){ return this.content; }

    public String getInput(){ return this.input; }

    public String getOutput(){ return this.output; }

    public String getConstraints(){ return this.constraints; }

    public String getSampleInput(){ return this.sampleInput; }

    public String getSampleOutput(){ return this.sampleOutput; }



}

public class ProblemsAdapter extends ArrayAdapter<Problem> {

    public ProblemsAdapter(Activity context, ArrayList<Problem> data) {

        super(context, 0, data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Problem problem = getItem(position);

        return listItemView;
    }
}