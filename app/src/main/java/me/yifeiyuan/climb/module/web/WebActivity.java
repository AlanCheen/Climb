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
import me.yifeiyuan.climb.ui.widget.ToastUtil;

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
        mUrl = getIntent().getStringExtra(KEY_URL);
        //如果是 github 的仓库地址，那么直接进完整的README 不然每次都要自己点
        if (mUrl.startsWith("https://github.com") && !mUrl.endsWith("README.md")) {
            mUrl = mUrl + "/blob/master/README.md";
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mWebview.setCallback(new OPWebView.Callback() {
            @Override
            public void onProgressChanged(int newProgress) {

                if (null == mProgressBar) {
                    return;
                }
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
                    startActivity(Intent.createChooser(i,"请选择浏览器"));
                }
                break;

            case R.id.share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, mWebview.getUrl());
                startActivity(Intent.createChooser(share,"分享当前链接给"));
                break;

            case R.id.collect:
                ToastUtil.show(mContext,"Coming ");
                break;

            case R.id.copy_link:
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText("URL",mUrl));
                ToastUtil.show(mContext,"Url复制成功");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
