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

    private final int BACK_ID = 100;
    private final int BACK_TEXT_ID = 101;
    private final int SHARE_ID = 102;
    private final int SHARE1_ID = 103;
    private final int SHARE2_ID = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleBar = findViewById(R.id.mTitleBar);
//        mTitleBar.addNavigation(new TitleItem(BACK_ID, R.drawable.ic_back));
//        mTitleBar.addNavigation(new TitleItem(BACK_TEXT_ID, "返回"));
//        mTitleBar.addMenu(new TitleItem(SHARE_ID, "分享"));
//        mTitleBar.addMenu(new TitleItem(SHARE1_ID, "分享", R.drawable.shape_stroke_white));
//        mTitleBar.addMenu(new TitleItem(SHARE2_ID, "分享", R.drawable.shape_stroke_white, R.drawable.ic_share));

        List<TitleItem> items = new ArrayList<>();
        items.add(new TitleItem(BACK_ID, R.drawable.ic_back));
        items.add(new TitleItem(BACK_TEXT_ID, "返回"));
        mTitleBar.addNavigation(items);
        List<TitleItem> items1 = new ArrayList<>();
        items1.add(new TitleItem(SHARE_ID, "分享"));
        items1.add(new TitleItem(SHARE1_ID, "分享", R.drawable.shape_stroke_white));
        items1.add(new TitleItem(SHARE2_ID, "分享", R.drawable.shape_stroke_white, R.drawable.ic_share));
        mTitleBar.addMenus(items1);

        mTitleBar.setNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case BACK_ID:
                        Toast.makeText(MainActivity.this, "back", Toast.LENGTH_SHORT).show();
                        break;
                    case BACK_TEXT_ID:
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
                    case SHARE_ID:
                        Toast.makeText(MainActivity.this, "share", Toast.LENGTH_SHORT).show();
                        break;
                    case SHARE1_ID:
                        Toast.makeText(MainActivity.this, "share one", Toast.LENGTH_SHORT).show();
                        break;
                    case SHARE2_ID:
                        Toast.makeText(MainActivity.this, "share two", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
