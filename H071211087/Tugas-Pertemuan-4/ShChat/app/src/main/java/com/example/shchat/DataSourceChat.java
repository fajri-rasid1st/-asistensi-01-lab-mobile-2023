package com.example.shchat;

import java.util.ArrayList;

public class DataSourceChat {

    static ArrayList<ModelChat> getListChat(){
        ArrayList<ModelChat> u = new ArrayList<>();
        u.add(new ModelChat("04.10 Pm","Assalamualaikum"));
        u.add(new ModelChat("04.20 Pm","Dimana k Sekarang?"));
        u.add(new ModelChat("04.30 Pm","Alhamdulillah"));
        u.add(new ModelChat("04.40 Pm","Waalaikumsalam"));
        u.add(new ModelChat("04.50 Pm","Ada Kelas mu?"));
        u.add(new ModelChat("04.55 Pm","Bagaimana Mid nya?"));
        u.add(new ModelChat("05.00 Pm","Ada k Didepartemen?"));
        u.add(new ModelChat("05.10 Pm","Otw kesana ma"));
        u.add(new ModelChat("05.20 Pm","Ada mi dosen?"));
        u.add(new ModelChat("05.30 Pm","Apa Kabar?"));
        return u;

    }

}
