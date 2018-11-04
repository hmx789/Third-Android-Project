package com.example.hassan.projecta3;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebFragment extends Fragment {
    final String TAG = "WebFragment";
    static String[] urls = {"http://www.visitunionsquaresf.com/","http://www.goldengatebridge.org/","https://www.nps.gov/alca/index.htm","http://www.fishermanswharf.org","https://www.pier39.com/","https://sfrecpark.org/destination/telegraph-hill-pioneer-park/coit-tower/"};
    static String url = "";
    public int indexUrl = -1;
    public WebView mWebView;

    public void setURL(int index) {         // Loading the url this is called from ListSelection
        indexUrl = index;
        mWebView.loadUrl(urls[indexUrl]);
    }
    public int getShownIndex() {        //Check what index the webfragment is set for currently
        return indexUrl;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":onCreateView()");
        View view = inflater.inflate(R.layout.webview, container, false);
        mWebView = view.findViewById(R.id.web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (indexUrl != -1) {
            mWebView.loadUrl(urls[indexUrl]);
        }
        mWebView.setWebViewClient(new WebViewClient());
        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);        // to save the fragment
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

    }
}
