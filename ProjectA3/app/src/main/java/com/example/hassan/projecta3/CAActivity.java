package com.example.hassan.projecta3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

public class CAActivity extends AppCompatActivity implements ListPoints.ListSelectionListener {
    static String[] pois = {"Union Square","Golden Gate Bridge","Alcatraz Island","Fisherman's Wharf","Pier 39","Coit Tower"};
    private WebFragment wf;
    private ListPoints lp;
    private android.app.FragmentManager fm;
    private FrameLayout mPoiLayout,mWebLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "CAActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        mPoiLayout = (FrameLayout) findViewById(R.id.listholder);
        mWebLayout = (FrameLayout) findViewById(R.id.webholder);

        fm = getFragmentManager();
        lp = (ListPoints) fm.findFragmentById(R.id.listholder);
        wf = (WebFragment) fm.findFragmentById(R.id.webholder);

        if ( null == fm.findFragmentById(R.id.listholder)) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            lp = new ListPoints();
            fragmentTransaction.replace(R.id.listholder,lp);
            fragmentTransaction.commit();
        }
        setLayout();
        
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                LayoutAfterBackStackChanged();
            }
        });

    }

    
    private void LayoutAfterBackStackChanged(){
        if (null == fm.findFragmentById(R.id.webholder)) {
                lp.unCheckSelection();
        }
        setLayout();
    }


    private void setLayout() {

        // Determine whether the QuoteFragment has been added
        if (null == fm.findFragmentById(R.id.webholder)) {

            // Make the TitleFragment occupy the entire layout
            mPoiLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mWebLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        }
        // If in landscape mode make list 1/3 and title 2/3
        else  if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            mPoiLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            mWebLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }
        //If in portrait mode and webview present do not show the list
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mPoiLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    0));

            // Make the QuoteLayout take 2/3's of the layout's width
            mWebLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT));
        }
    }

    @Override
    public void onListSelection(int index) {
        if (null == fm.findFragmentById(R.id.webholder)) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            wf = new WebFragment();
            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.webholder,wf);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            fm.executePendingTransactions();
        }

        if (wf.getShownIndex() != index) {
            // Open the webview of the selected point of interest
            wf.setURL(index);

        }
    }

    
}
