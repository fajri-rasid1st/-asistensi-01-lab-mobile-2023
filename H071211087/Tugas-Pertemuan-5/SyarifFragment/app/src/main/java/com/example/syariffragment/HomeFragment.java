package com.example.syariffragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {

    ImageView tlImage;
    TextView Tuser, Tname, Tcapt,warn;
    CircleImageView ppImg;
    CardView cview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tlImage = view.findViewById(R.id.imageContent);
        ppImg = view.findViewById(R.id.profile_img);
        Tuser = view.findViewById(R.id.userr);
        Tname = view.findViewById(R.id.namee);
        Tcapt = view.findViewById(R.id.captiontxt);
        warn = view.findViewById(R.id.warning);
        cview = view.findViewById(R.id.cardView2);


        Bundle bundle = getArguments();
        if(bundle == null){
            tlImage.setVisibility(View.GONE);
            ppImg.setVisibility(View.GONE);
            Tuser.setVisibility(View.GONE);
            Tname.setVisibility(View.GONE);
            Tcapt.setVisibility(View.GONE);
            cview.setVisibility(View.GONE);
        }else{
            Uri uri = bundle.getParcelable("img");
            Uri uri1 = bundle.getParcelable("image");
            String putCapt = bundle.getString(AddFragment.Key1);
            Tcapt.setText(putCapt);
            tlImage.setImageURI(uri);
            ppImg.setImageURI(uri1);
            warn.setVisibility(View.GONE);
        }
        return view;
    }
}
