package co.elyx.elyx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/8/15.
 */
public class InboundFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatAdapter.Case> cases;

    public InboundFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        placeholderData();
        mAdapter = new ChatAdapter(cases);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void placeholderData() {
        cases = new ArrayList<>();
        cases.add(new ChatAdapter.Case("Lavery", "01/02/15", "Cough, Dizziness, Asthma", "M, 34, Hypertensive", R.drawable.lavery));
        cases.add(new ChatAdapter.Case("Lillie", "12/02/15", "Dizziness, Asthma", "F, 26", R.drawable.lillie));
        cases.add(new ChatAdapter.Case("Emma", "01/12/15", "Cough, Asthma", "M, 19, Smoking", R.drawable.emma));
        cases.add(new ChatAdapter.Case("Lavery", "04/07/15", "Cough, Dizziness, Asthma", "F, 38, Alchoholic", R.drawable.lavery));
        cases.add(new ChatAdapter.Case("Lillie", "17/06/15", "Cough, Dizziness", "M, 34, Hypertensive", R.drawable.lillie));
        cases.add(new ChatAdapter.Case("Emma", "21/11/15", "Cough", "M, 19", R.drawable.emma));
        cases.add(new ChatAdapter.Case("Lavery", "01/02/15", "Cough, Dizziness, Asthma", "M, 34, Hypertensive", R.drawable.lavery));
        cases.add(new ChatAdapter.Case("Lillie", "12/02/15", "Dizziness, Asthma", "F, 26", R.drawable.lillie));
        cases.add(new ChatAdapter.Case("Emma", "01/12/15", "Cough, Asthma", "M, 19, Smoking", R.drawable.emma));
        cases.add(new ChatAdapter.Case("Lavery", "04/07/15", "Cough, Dizziness, Asthma", "F, 38, Alchoholic", R.drawable.lavery));
        cases.add(new ChatAdapter.Case("Lillie", "17/06/15", "Cough, Dizziness", "M, 34, Hypertensive", R.drawable.lillie));
        cases.add(new ChatAdapter.Case("Emma", "21/11/15", "Cough", "M, 19", R.drawable.emma));

    }


}
