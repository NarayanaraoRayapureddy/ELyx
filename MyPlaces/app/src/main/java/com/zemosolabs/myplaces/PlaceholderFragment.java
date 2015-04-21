package com.zemosolabs.myplaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PlaceholderFragment extends Fragment {


    ListView listView;


    public PlaceholderFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        Log.i("onCreateViewPlaceHolderFrag", "Creating a view for placeholder fragment");



        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView_Places);
        listView.setAdapter(MainActivity.mPlacesAdapter);
        getActivity().registerForContextMenu(listView);
        Log.i("onCreateViewPlaceHolderFrag","ListView registered");

        return rootView;

    }




}
