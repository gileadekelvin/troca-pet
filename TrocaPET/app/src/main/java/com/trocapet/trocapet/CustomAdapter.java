package com.trocapet.trocapet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lucas on 19/08/2017.
 */

public class CustomAdapter extends ArrayAdapter{

    private final Activity context;
    private final String[] itemName;
    private final Integer[] imgsID;

    public CustomAdapter(final Activity context, final String[] itemName, final Integer[] imgsID) {
        super(context, R.layout.my_list, itemName);

        this.context = context;
        this.itemName = itemName;
        this.imgsID = imgsID;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        //TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemName[position]);
        imageView.setImageResource(imgsID[position]);
        //extratxt.setText("Description "+itemName[position]);
        return rowView;

    };
}
