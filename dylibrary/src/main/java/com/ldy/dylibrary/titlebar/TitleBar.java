package com.ldy.dylibrary.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldy.dylibrary.R;
import com.ldy.dylibrary.titlebar.data.TitleItem;
import com.ldy.dylibrary.util.PixelUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题栏
 */
public class TitleBar extends FrameLayout {

    private Context context;

    private TextView mTvTitle;//标题
    private LinearLayout mLayoutNavigation;//导航栏
    private LinearLayout mLayoutMenu;//菜单栏

    private int titleSize;//标题大小
    private int titleColor;//标题颜色
    private int navigationTextSize;//导航栏文字大小
    private int navigationTextColor;//导航栏文字颜色
    private int menuTextSize;//菜单栏文字大小
    private int menuTextColor;//菜单栏文字颜色

    private List<TitleItem> navigationList;
    private List<TitleItem> menuList;

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化
        init(context, attrs);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            titleSize = typedArray.getInteger(R.styleable.TitleBar_title_size, 18);
            titleColor = typedArray.getColor(R.styleable.TitleBar_title_color, Color.BLACK);
            navigationTextSize = typedArray.getInteger(R.styleable.TitleBar_navigation_text_size, 14);
            navigationTextColor = typedArray.getColor(R.styleable.TitleBar_navigation_text_color, Color.BLACK);
            menuTextSize = typedArray.getInteger(R.styleable.TitleBar_menu_text_size, 14);
            menuTextColor = typedArray.getColor(R.styleable.TitleBar_menu_text_color, Color.BLACK);
            typedArray.recycle();
        }
        inflate(context, R.layout.layout_title_bar, this);
        mTvTitle = findViewById(R.id.tv_title);
        mLayoutNavigation = findViewById(R.id.layout_navigation);
        mLayoutMenu = findViewById(R.id.layout_menu);
        //初始化标题
        initTitle();
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        mTvTitle.setTextSize(titleSize);
        mTvTitle.setTextColor(titleColor);
    }

    /**
     * 添加导航栏项
     *
     * @param list List<TitleItem>
     */
    public void addNavigations(List<TitleItem> list) {
        if (list != null) {
            if (navigationList == null) {
                navigationList = new ArrayList<>();
            }
            navigationList.clear();
            navigationList.addAll(list);
            loadNavigation();
        }
    }

    /**
     * 添加导航栏项
     *
     * @param item TitleItem
     */
    public void addNavigation(TitleItem item) {
        if (item != null) {
            View view = getTitleItem(item, true);
            if (view != null) {
                mLayoutNavigation.addView(view);
            }
        }
    }

    /**
     * 加载导航栏
     */
    private void loadNavigation() {
        if (navigationList != null) {
            mLayoutNavigation.removeAllViews();
            for (TitleItem item : navigationList) {
                addNavigation(item);
            }
        }
    }

    /**
     * 添加菜单栏项
     *
     * @param list List<TitleItem>
     */
    public void addMenus(List<TitleItem> list) {
        if (list != null) {
            if (menuList == null) {
                menuList = new ArrayList<>();
            }
            menuList.clear();
            menuList.addAll(list);
            loadMenu();
        }
    }

    /**
     * 添加菜单栏项
     *
     * @param item TitleItem
     */
    public void addMenu(TitleItem item) {
        if (item != null) {
            View view = getTitleItem(item, false);
            if (view != null) {
                mLayoutMenu.addView(view);
            }
        }
    }

    /**
     * 加载菜单栏
     */
    private void loadMenu() {
        if (menuList != null) {
            mLayoutNavigation.removeAllViews();
            for (TitleItem item : menuList) {
                addMenu(item);
            }
        }
    }

    /**
     * 加载 TitleItem
     *
     * @param item TitleItem
     */
    private View getTitleItem(TitleItem item, boolean isNavigation) {
        View view = null;
        if (item != null) {
            if (item.getSrc() > 0) {
                view = createImage(item.getSrc());
            } else {
                view = createText(item.getContent(), item.getBackground(), isNavigation);
            }
        }
        return view;
    }

    /**
     * 创建Image
     */
    private ImageView createImage(int src) {
        int imageSize = PixelUtils.dp2px(context, 36f);
        int padding = PixelUtils.dp2px(context, 8f);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(imageSize, imageSize));
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(src);
        return imageView;
    }

    /**
     * 创建Text
     */
    private TextView createText(String text, int background, boolean isNavigation) {
        TextView textView = new TextView(context);
        if (background > 0) {
            int paddingH = PixelUtils.dp2px(context, 8f);
            int paddingV = PixelUtils.dp2px(context, 3f);
            int margin = PixelUtils.dp2px(context, 8f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(margin, margin, margin, margin);
            textView.setLayoutParams(params);
            textView.setPadding(paddingH, paddingV, paddingH, paddingV);
            textView.setBackgroundResource(background);
        } else {
            int paddingH = PixelUtils.dp2px(context, 12f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setPadding(paddingH, 0, paddingH, 0);
        }
        textView.setText(text);
        if (isNavigation) {
            textView.setTextColor(navigationTextColor);
            textView.setTextSize(navigationTextSize);
        } else {
            textView.setTextColor(menuTextColor);
            textView.setTextSize(menuTextSize);
        }
        return textView;
    }

}
