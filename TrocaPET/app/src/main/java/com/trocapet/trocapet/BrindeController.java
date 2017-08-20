package com.trocapet.trocapet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lucas on 19/08/2017.
 */

public class BrindeController {

    private static String[] nomeDosBrindes = {
            "Impressora HP Preto e Branco",
            "Celular Moto G1",
            "Headphone Samsung",
            "Monitor 21.5' AOC",
            "Coca-Cola 2L",
            "Pen-drive Kingston 16GB",
            "Mouse Sem Fio Multilaser",
            "HD Externo 500 GB Seagate"
    };

    private static Integer[] imgsDosBrindes = {
            R.drawable.impressoa_pb,
            R.drawable.motog1,
            R.drawable.headphone,
            R.drawable.monitor,
            R.drawable.coca_cola_2litros,
            R.drawable.pendrive_16gb,
            R.drawable.mouse_sem_fio,
            R.drawable.hd500gb
    };
    //talvez criar um mapa <nome, ecopoints>
    private static Integer[] ecopointsCadaBrinde = {
            1500,
            2000,
            150,
            900,
            20,
            65,
            250,
            600
    };

    public static Collection<Brinde> getBrindes() {
        List<Brinde> brindes = new ArrayList<Brinde>();

        int totalBrindes = nomeDosBrindes.length;
        for (int i = 0; i < totalBrindes; i++) {
            brindes.add(new Brinde(nomeDosBrindes[i], ecopointsCadaBrinde[i], imgsDosBrindes[i]));
        }
        return brindes;
    }

    public static Brinde getBrinde(final int position) {
        List<Brinde> brindes = (List<Brinde>) getBrindes();
        return (brindes.get(position)) ;
    }

    public static String[] getNomeTodosBrindes() {
        return nomeDosBrindes;
    }

    public static Integer[] getEcopointsCadaBrinde() {
        return ecopointsCadaBrinde;
    }

    public static Integer[] getImgsDosBrindes() {
        return imgsDosBrindes;
    }
}
