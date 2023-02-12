package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private TextView mCountPhrase;
    private TextView mCountPhrase2;
    public static final int INITIAL_COUNT = 0;
    private FragmentManager mFragmentManager;

    private CountFragment topFragment;
    private CountFragment botFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCountPhrase = findViewById(R.id.count_phrase);
        mCountPhrase2 = findViewById(R.id.count_phrase2);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();

        topFragment = CountFragment.newInstance(INITIAL_COUNT);
        botFragment = CountFragment.newInstance(INITIAL_COUNT);
        //updateView(INITIAL_COUNT);

        mFragmentManager.beginTransaction()
                .add(R.id.topFragmentContainerView, topFragment)
                .commit();

        mFragmentManager.beginTransaction()
                .add(R.id.botFragmentContainerView, botFragment)
                .commit();

        mFragmentManager.setFragmentResultListener(CountFragment.REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Here we get bunlde from Fragement, so we can get the count out
                int countVal = result.getInt(CountFragment.COUNT);
                Log.d(LOG_TAG,  "count in bundle is: "+countVal);

                //update the text hints on bottom
                updateMsg();
            }
        });

    }

    public void updateText1(String t1) {
        mCountPhrase = findViewById(R.id.count_phrase);
        mCountPhrase.setText(t1);
    }
    public void updateText2(String t2) {
        mCountPhrase2 = findViewById(R.id.count_phrase2);
        mCountPhrase2.setText(t2);
    }

    public void updateTextView(String text, int viewId) {
        TextView mCountPhrase = findViewById(viewId);
        mCountPhrase.setText(text);
    }

    private void updateMsg() {

        int topCount = this.topFragment.getCount();
        String msg1 = getResources().getString(R.string.count_phrase, 1, topCount);
        String hint1 = getResources().getString(R.string.count_hint1, "TOP", topCount);

        updateTextView(hint1, R.id.count_phrase);
        //this.updateText1(msg1);

        int botCount = this.botFragment.getCount();
        String msg2 = getResources().getString(R.string.count_phrase2, 2, botCount);
        String hint2 = getResources().getString(R.string.count_hint2, "BOTTOM", botCount);

        updateTextView(hint2, R.id.count_phrase2);
        //this.updateText2(msg2);

    }
}