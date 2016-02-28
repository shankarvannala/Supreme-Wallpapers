package sjdev.walls.dash.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import sjdev.walls.dash.R;

/**
 * Created by sjdev on 2/15/2016.
 */
public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rv = (ViewGroup) inflater.inflate(R.layout.fragment_about, container, false);
        setHasOptionsMenu(true);
        //Add your circleimageview pic link here//
        ImageView yiv=(ImageView) rv.findViewById(R.id.your_imageview);
        Picasso.with(getActivity())
                .load("https://lh3.googleusercontent.com/-0mdcClKXlgM/AAAAAAAAAAI/AAAAAAAAAAA/xgdLWeAg7pM/s120-c/photo.jpg")
                .into(yiv);

        ImageView aidaniv=(ImageView) rv.findViewById(R.id.aidan_imageview);
        Picasso.with(getActivity())
                .load("https://lh3.googleusercontent.com/-mMe9cCCcm3Q/AAAAAAAAAAI/AAAAAAAAh2g/900TLdhn7dA/s120-c/photo.jpg")
                .into(aidaniv);

        ImageView jfiv=(ImageView)rv.findViewById(R.id.jf_imageview);
        Picasso.with(getActivity())
                .load("https://lh4.googleusercontent.com/-IyRx4Aav1XU/VluTg9HOAuI/AAAAAAAANok/_C84Zl9yrr4/s548-no/af3938fc-0a44-43c9-98b7-b72d56937fdf")
                .into(jfiv);

        ImageView civ=(ImageView) rv.findViewById(R.id.civ_imageview);
        Picasso.with(getActivity())
                .load("https://lh3.googleusercontent.com/-0mdcClKXlgM/AAAAAAAAAAI/AAAAAAAAAAA/xgdLWeAg7pM/s120-c/photo.jpg")
                .into(civ);

        // card clicks//
        CardView aidan=(CardView)rv.findViewById(R.id.aidandev);
        aidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent af = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.aidan_link)));
                startActivity(af);
            }
        });
        CardView jahir=(CardView)rv.findViewById(R.id.jahirdev);
        jahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jf = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.jahir_link)));
                startActivity(jf);
            }
        });
        CardView mike=(CardView)rv.findViewById(R.id.mikepenzdev);
        mike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.mike_link)));
                startActivity(mp);
            }
        });
        CardView square=(CardView)rv.findViewById(R.id.square);
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sq = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.square_link)));
                startActivity(sq);
            }
        });
        CardView cimagev=(CardView)rv.findViewById(R.id.civ);
        cimagev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent civ = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.circleiv_link)));
                startActivity(civ);
            }
        });

        // gplus links//
        TextView sjgplus=(TextView)rv.findViewById(R.id.sjgoogleplus);
        sjgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sj = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.sjgplus_link)));
                startActivity(sj);
            }
        });
        TextView anilgplus=(TextView)rv.findViewById(R.id.anilgoogleplus);
        anilgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.anilgplus_link)));
                startActivity(ak);
            }
        });

        return rv;
    }
}