package com.ldy.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ldy.dylibrary.titlebar.TitleBar;
import com.ldy.dylibrary.titlebar.data.TitleItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleBar = findViewById(R.id.mTitleBar);
        List<TitleItem> items = new ArrayList<>();
        items.add(new TitleItem(R.id.iv_back, R.drawable.ic_back));
        items.add(new TitleItem(R.id.tv_back, "返回"));
        mTitleBar.addNavigations(items);
        List<TitleItem> items1 = new ArrayList<>();
        items1.add(new TitleItem(R.id.tv_share1, "分享"));
        items1.add(new TitleItem(R.id.tv_share2, "分享", R.drawable.shape_stroke_white));
        items1.add(new TitleItem(R.id.tv_share3, "分享", R.drawable.shape_stroke_white, R.drawable.ic_share));
        mTitleBar.addMenus(items1);

        mTitleBar.setNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_back:
                        Toast.makeText(MainActivity.this, "back", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_back:
                        Toast.makeText(MainActivity.this, "back text", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        mTitleBar.setMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_share1:
                        Toast.makeText(MainActivity.this, "share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_share2:
                        Toast.makeText(MainActivity.this, "share one", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_share3:
                        Toast.makeText(MainActivity.this, "share two", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
