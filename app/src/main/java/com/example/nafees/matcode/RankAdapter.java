package com.example.nafees.matcode;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Info{

    private String name;
    private String xp;
    private String rank;
    private String imageId;

    public void setName(String name){
        this.name = name;
    }

    public void setXp(String xp){
        this.xp = xp;
    }

    public void setRank(String rank){
        this.rank = rank;
    }

    public String getName(){
        return this.name;
    }

    public String getXp(){
        return this.xp;
    }

    public String getRank(){
        return this.rank;
    }

    public String getImageId(){
        return this.imageId;
    }

}

public class RankAdapter extends ArrayAdapter<Info> {

    public RankAdapter(Activity context, ArrayList<Info> data) {

        super(context, 0, data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Info data = getItem(position);

        TextView name = listItemView.findViewById(R.id.name);
        TextView xp = listItemView.findViewById(R.id.xp);
        TextView rank = listItemView.findViewById(R.id.rank);

        name.setText(data.getName());
        xp.setText(data.getXp());
        rank.setText(data.getRank());

        return listItemView;
    }
}