package com.example.hassan.projecta3;



import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


    public class ListPoints extends ListFragment {

        private static final String TAG = "ListPoints";
        private ListSelectionListener mListener = null;
        public boolean mDualPane;
        private int mCurrIdx = ListView.INVALID_POSITION;

        public void unCheckSelection() {
            if (getListView().getCheckedItemCount() > 0) {
                getListView().setItemChecked(getListView().getCheckedItemPosition(), false);
            }
            mCurrIdx = ListView.INVALID_POSITION;
        }

        // Callback interface that allows this Fragment to notify the CAActivity when
        // user clicks on a List Item
        public interface ListSelectionListener {
            public void onListSelection(int index);
        }

        @Override
        public void onAttach(Context activity) {
            super.onAttach(activity);
            Log.i(TAG, getClass().getSimpleName() + ":onAttach()");
            try {

                // Set the ListSelectionListener for communicating with the CAActivity
                mListener = (ListSelectionListener) activity;

            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement ");
            }
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i(TAG, getClass().getSimpleName() + ":onCreate()");
            setRetainInstance(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);

        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("curChoice", mCurrIdx);
        }

        @Override
        public void onActivityCreated(Bundle savedState) {
            super.onActivityCreated(savedState);
            Log.i(TAG, getClass().getSimpleName() + ": onActivityCreated()");

            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.list_item, CAActivity.pois));
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE); //one select at a time


            if (savedState != null) {       // checking if their was a previous index saved
                Log.i(TAG,"saved state is not null");
                mCurrIdx = savedState.getInt("curChoice");
                Log.i(TAG,"in saved state this is curridx: " + mCurrIdx);
            }


//             If an item has been selected, set its checked state
            if (-1 != mCurrIdx) {
                Log.i(TAG,"Saved Instance and now setting item checked");
                getListView().setItemChecked(mCurrIdx, true);
                // UB:  10-6-2017 Added this call to handle configuration changes
                // that broke in API 25
                mListener.onListSelection(mCurrIdx);
            }
        }

        // Called when the user selects an item from the List
        @Override
        public void onListItemClick(ListView l, View v, int pos, long id) {
            if (mCurrIdx != pos) {
                mCurrIdx = pos;

                // Inform the QuoteViewerActivity that the item in position pos has been selected
                mListener.onListSelection(pos);
            }
            // Indicates the selected item has been checked
            l.setItemChecked(mCurrIdx, true);
        }


    }
