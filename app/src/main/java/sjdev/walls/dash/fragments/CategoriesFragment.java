package sjdev.walls.dash.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sjdev.walls.dash.R;
import sjdev.walls.dash.activities.CardTwo;
import sjdev.walls.dash.activities.CardOne;

/**
 * Created by sjdev on 2/15/2016.
 */
public class CategoriesFragment extends Fragment{
    public CategoriesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rv = (ViewGroup) inflater.inflate(R.layout.fragment_categorys, container, false);
        setHasOptionsMenu(false);
        CardView co=(CardView)rv.findViewById(R.id.cardone);
        co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(getActivity(),CardOne.class);
                startActivity(act);
            }
        });
        CardView ctwo=(CardView)rv.findViewById(R.id.cardtwo);
        ctwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(getActivity(),CardTwo.class);
                startActivity(act);
            }
        });

        return rv;
    }


}