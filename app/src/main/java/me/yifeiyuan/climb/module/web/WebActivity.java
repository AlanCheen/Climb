package me.yifeiyuan.climb.module.web;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.Bind;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseActivity;
import me.yifeiyuan.climb.ui.view.OPWebView;
import me.yifeiyuan.climb.ui.widget.T;

public class WebActivity extends BaseActivity {

    public static final String KEY_URL = "key_url";

    @Bind(R.id.webview)
    OPWebView mWebview;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;
    private String mUrl;

    public static void comeOn(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mWebview.setCallback(new OPWebView.Callback() {
            @Override
            public void onProgressChanged(int newProgress) {

                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setProgress(newProgress);
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(String title) {
                setTitle(title);
            }

            @Override
            public void onPageFinished(String url) {
                mProgressBar.setVisibility(View.GONE);
            }
        });

        mUrl = getIntent().getStringExtra(KEY_URL);
        mWebview.loadUrl(mUrl);

    }


    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        ((ViewGroup)mWebview.getParent()).removeView(mWebview);
        mWebview.removeAllViews();
        mWebview.destroy();
        mWebview = null;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.open:
                Intent i = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(mUrl);
                i.setData(uri);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(i,"请皇上翻盘"));
                }
                break;

            case R.id.share:
                T.show(mContext,"Coming soon");
                break;

            case R.id.collect:
                T.show(mContext,"Coming soon");
                break;

            case R.id.copy_link:
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText("URL",mUrl));
                T.show(mContext,"Url复制成功");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
