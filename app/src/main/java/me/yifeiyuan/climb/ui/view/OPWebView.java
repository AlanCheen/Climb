package me.yifeiyuan.climb.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 程序亦非猿 on 16/2/18.
 */
public class OPWebView extends WebView {

    public OPWebView(Context context) {
        super(context);
        init();
    }

    public OPWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OPWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public OPWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new ViewClient());
        getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        setWebChromeClient(new ChromeClient());
        getSettings().setAllowFileAccess(true);
        getSettings().setBuiltInZoomControls(false);
    }

    private class ViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (null != mCallback) {
                mCallback.onPageFinished(url);
            }

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }


    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (null != mCallback) {
                mCallback.onProgressChanged(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (null != mCallback) {
                mCallback.onReceivedTitle(title);
            }
        }

    }

    private Callback mCallback;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onProgressChanged(int newProgress);

        void onReceivedTitle(String title);

        void onPageFinished(String url);
    }
}