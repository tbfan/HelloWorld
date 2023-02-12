package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class CountFragment extends Fragment {
    private static final String LOG_TAG = CountFragment.class.getSimpleName();
    public static final String COUNT = "count";

    private int mCount = 0;
    private TextView mCountView;

    public static final String INITIAL_COUNT = "initial_count";
    public static final String REQUEST_KEY = "request_key";

    public CountFragment() {
        // Required empty public constructor
    }


    public static CountFragment newInstance(int initialCount) {
        CountFragment fragment = new CountFragment();
        Bundle bundle = new Bundle();

        bundle.putInt(COUNT, initialCount);

        fragment.setArguments(bundle);
        return fragment;
    }

    public static CountFragment newInstance(){
        return newInstance(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCount =  getArguments().getInt(INITIAL_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        MainActivity mainActivity = (MainActivity)getActivity();
//        String text1 = mainActivity.getText1();

        View view = inflater.inflate(R.layout.fragment_count, container, false);

        //save this mCountView component, we will update it in increment
        mCountView = view.findViewById(R.id.count_view);

        view.findViewById(R.id.increment).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "button clicked");
                increment();
            }
        });
        updateView();
        return view;
    }

    public int getCount() {
        return this.mCount;
    }

    public void increment(){
        this.mCount++;
        Log.d(LOG_TAG, "mCount: "+this.mCount);

//        MainActivity mainActivity = (MainActivity)getActivity();
//        mainActivity.updateText1("count1: "+this.mCount);

        Bundle bundle = new Bundle();
        bundle.putInt(CountFragment.COUNT, this.mCount);

        Log.d(LOG_TAG, "bundle count: "+bundle.getInt(CountFragment.COUNT));

        getParentFragmentManager().setFragmentResult(REQUEST_KEY, bundle);

        //update the mCounterView in this Fragement
        updateView();
    }

    public void updateView(){
        //this set the countView
        mCountView.setText(Integer.toString(mCount));
    }
}