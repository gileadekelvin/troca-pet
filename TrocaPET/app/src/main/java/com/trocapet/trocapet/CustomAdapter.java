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

    private final String[] itemName;
    private final Integer[] imgsID;

    private final static String[] brindes = {
            "Impressora HP Preto e Branco",
            "Celular Moto G1",
            "Headphone Samsung",
            "Monitor 21.5' AOC",
            "Coca-Cola 2L",
            "Pen-drive Kingston 16GB",
            "Mouse Sem Fio Multilaser",
            "HD Externo 500 GB Seagate"
    };

    private final static Integer[] imgsDosBrindes = {
            R.drawable.impressoa_pb,
            R.drawable.motog1,
            R.drawable.headphone,
            R.drawable.monitor,
            R.drawable.coca_cola_2litros,
            R.drawable.pendrive_16gb,
            R.drawable.mouse_sem_fio,
            R.drawable.hd500gb
    };

    //talvez criar um objeto brinde.
    private final Integer[] ecopointsCadaBrinde = {
            1500,
            2000,
            150,
            900,
            20,
            65,
            250,
            600
    };
    private String descricaoBrinde;

    public CustomAdapter(final Activity context, final String[] itemName, final Integer[] imgsID) {
        super(context, R.layout.my_list, itemName);

        this.context = context;
        this.itemName = itemName;
        this.imgsID = imgsID;
    }

    public static CustomAdapter createAdapter(final Activity context){
        return new CustomAdapter(context, brindes, imgsDosBrindes);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.my_list, null, true);

        setUpViewsIn(rowView);

        txtTitle.setText(itemName[position]);
        imageView.setImageResource(imgsID[position]);

        descricaoBrinde = "Requer " + ecopointsCadaBrinde[position] + " ecopoints.";
        txtDescription.setText(descricaoBrinde);

        return rowView;

    }

    private void setUpViewsIn(View rowView) {

        txtTitle = (TextView) rowView.findViewById(R.id.item_name);

        imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtDescription = (TextView) rowView.findViewById(R.id.description);
    }
}
