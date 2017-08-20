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

    private ImageView imageView;
    private TextView txtTitle;
    private TextView txtDescription;

    private final Activity context;

    private final static String[] nomeDosBrindes = BrindeController.getNomeTodosBrindes();

    private String descricaoBrinde;

    private CustomAdapter(final Activity context, final String[] itemName) {
        super(context, R.layout.my_list, itemName);

        this.context = context;
    }

    public static CustomAdapter createAdapter(final Activity context){
        return new CustomAdapter(context, nomeDosBrindes);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.my_list, null, true);

        setUpViewsIn(rowView);

        setUpBrindeInfo(position);

        return rowView;

    }

    private void setUpBrindeInfo(int position) {
        Brinde brindeAtual = BrindeController.getBrinde(position);

        txtTitle.setText(brindeAtual.getNome());

        imageView.setImageResource(brindeAtual.getIdFoto());

        descricaoBrinde = "Requer " + brindeAtual.getEcopointsRequeridos() + " ecopoints.";
        txtDescription.setText(descricaoBrinde);
    }

    private void setUpViewsIn(final View rowView) {

        txtTitle = (TextView) rowView.findViewById(R.id.item_name);

        imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtDescription = (TextView) rowView.findViewById(R.id.description);
    }
}
