package com.example.itubeapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.itubeapp.R;

public class VideoWebViewFragment extends Fragment {
    private String mUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_web_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUrl = getArguments().getString("video");
        WebView webView = view.findViewById(R.id.webview);
        setupWebView(webView);
        loadUrl(webView, mUrl);
    }

    private void setupWebView(WebView webView) {
        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set a WebViewClient to handle navigation inside the WebView
        webView.setWebViewClient(new WebViewClient());
    }

    private void loadUrl(WebView webView, String url) {
        if (url != null) {
            webView.loadUrl(url);
        }
    }
}
