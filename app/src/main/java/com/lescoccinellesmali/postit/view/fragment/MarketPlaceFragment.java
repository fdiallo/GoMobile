package com.lescoccinellesmali.postit.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lescoccinellesmali.postit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketPlaceFragment extends Fragment {


    public MarketPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market_place, container, false);
    }

}
