package com.zemosolabs.myplaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

/*
*
* Created by admin on 25/3/15.
*/


public class MapFrag extends SupportMapFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





//        ArrayAdapter mPlacesAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                R.layout.list_item_places,
//                R.id.list_item_places_textview,
//                MainActivity.myPlaces);

        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        return rootView;

    }



//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        try {
//            SupportMapFragment fragment2 = (SupportMapFragment) getActivity()
//                    .getSupportFragmentManager().findFragmentById(R.id.map);
//            if (fragment2 != null)
//                getFragmentManager().beginTransaction().remove(fragment2)
//                        .commit();
//
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        }
//    }
}
