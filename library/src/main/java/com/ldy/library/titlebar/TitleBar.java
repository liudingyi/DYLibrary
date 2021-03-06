package com.ldy.library.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldy.library.R;
import com.ldy.library.titlebar.data.TitleItem;
import com.ldy.library.util.PixelUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * 标题栏
 */
public class TitleBar extends FrameLayout {

    private Context context;

    private RelativeLayout mLayoutTitleBar;//标题容器
    private TextView mTvTitle;//标题
    private LinearLayout mLayoutNavigation;//导航栏
    private LinearLayout mLayoutMenu;//菜单栏

    private int titleSize;//标题大小
    private int titleColor;//标题颜色
    private int navigationTextSize;//导航栏文字大小
    private int navigationTextColor;//导航栏文字颜色
    private int menuTextSize;//菜单栏文字大小
    private int menuTextColor;//菜单栏文字颜色
    private int titleBarBackground;//标题容器的背景色

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int newHeightMeasureSpec = heightMeasureSpec;
        if (mode != MeasureSpec.EXACTLY) {
            int height = PixelUtils.dp2px(context, 48f);
            newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
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
            navigationTextColor = typedArray.getColor(R.styleable.TitleBar_navigation_text_color, Color.GRAY);
            menuTextSize = typedArray.getInteger(R.styleable.TitleBar_menu_text_size, 14);
            menuTextColor = typedArray.getColor(R.styleable.TitleBar_menu_text_color, Color.GRAY);
            titleBarBackground = typedArray.getResourceId(R.styleable.TitleBar_title_bar_background, Color.TRANSPARENT);
            typedArray.recycle();
        }
        inflate(context, R.layout.layout_title_bar, this);
        mLayoutTitleBar = findViewById(R.id.layout_title_bar);
        mTvTitle = findViewById(R.id.tv_title);
        mLayoutNavigation = findViewById(R.id.layout_navigation);
        mLayoutMenu = findViewById(R.id.layout_menu);
        //初始化TitleBar背景
        if (titleBarBackground > 0) {
            mLayoutTitleBar.setBackgroundResource(titleBarBackground);
        }
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
     * 设置标题
     *
     * @param resId int
     */
    public void setTitle(@StringRes int resId) {
        mTvTitle.setText(resId);
    }

    /**
     * 设置标题
     *
     * @param title String
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 设置标题颜色
     *
     * @param titleColor int
     */
    public void setTitleColor(int titleColor) {
        mTvTitle.setTextColor(titleColor);
    }

    /**
     * 添加导航栏项
     *
     * @param navigationList List<TitleItem>
     */
    public void addNavigations(List<TitleItem> navigationList) {
        if (navigationList != null) {
            mLayoutNavigation.removeAllViews();
            for (TitleItem item : navigationList) {
                addNavigation(item);
            }
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
     * 添加导航栏项
     *
     * @param index int
     * @param item  TitleItem
     */
    public void addNavigation(int index, TitleItem item) {
        if (item != null) {
            View view = getTitleItem(item, true);
            if (view != null) {
                mLayoutNavigation.addView(view, index);
            }
        }
    }

    /**
     * 添加菜单栏项
     *
     * @param menuList List<TitleItem>
     */
    public void addMenus(List<TitleItem> menuList) {
        if (menuList != null) {
            mLayoutMenu.removeAllViews();
            for (TitleItem item : menuList) {
                addMenu(item);
            }
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
     * 添加菜单栏项
     *
     * @param index int
     * @param item  TitleItem
     */
    public void addMenu(int index, TitleItem item) {
        if (item != null) {
            View view = getTitleItem(item, false);
            if (view != null) {
                mLayoutMenu.addView(view, index);
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
            view.setId(item.getItemId());
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
            int paddingH = PixelUtils.dp2px(context, isNavigation ? 6f : 12f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
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

    /**
     * 设置导航栏的点击事件
     *
     * @param navigationClickListener OnClickListener
     */
    public void setNavigationClickListener(OnClickListener navigationClickListener) {
        for (int i = 0; i < mLayoutNavigation.getChildCount(); i++) {
            mLayoutNavigation.getChildAt(i).setOnClickListener(navigationClickListener);
        }
    }

    /**
     * 设置菜单栏的点击事件
     *
     * @param menuClickListener OnClickListener
     */
    public void setMenuClickListener(OnClickListener menuClickListener) {
        for (int i = 0; i < mLayoutMenu.getChildCount(); i++) {
            mLayoutMenu.getChildAt(i).setOnClickListener(menuClickListener);
        }
    }

    /**
     * 清空导航栏
     */
    public void clearNavigation() {
        mLayoutNavigation.removeAllViews();
    }

    /**
     * 清空菜单栏
     */
    public void clearMenu() {
        mLayoutMenu.removeAllViews();
    }

    /**
     * 设置TitleItems是否显示
     *
     * @param itemId    int
     * @param isVisible boolean
     */
    public void setVisible(int itemId, boolean isVisible) {
        View view = getNavigationItem(itemId);
        if (view != null) {
            view.setVisibility(isVisible ? VISIBLE : GONE);
            return;
        }
        view = getMenuItem(itemId);
        if (view != null) {
            view.setVisibility(isVisible ? VISIBLE : GONE);
        }
    }

    /**
     * 更新指定项的导航栏item
     *
     * @param index int
     * @param item  TitleItem
     */
    public void updateNavigationItem(int index, TitleItem item) {
        if (mLayoutNavigation.getChildCount() > index) {
            mLayoutNavigation.removeViewAt(index);
            addNavigation(index, item);
        }
    }

    /**
     * 更新指定项的菜单栏item
     *
     * @param index int
     * @param item  TitleItem
     */
    public void updateMenuItem(int index, TitleItem item) {
        if (mLayoutMenu.getChildCount() > index) {
            mLayoutMenu.removeViewAt(index);
            addMenu(index, item);
        }
    }

    /**
     * 获取指定id的导航栏view
     *
     * @param itemId int
     * @return View
     */
    public View getNavigationItem(int itemId) {
        for (int i = 0; i < mLayoutNavigation.getChildCount(); i++) {
            View view = mLayoutNavigation.getChildAt(i);
            if (view.getId() == itemId) {
                return view;
            }
        }
        return null;
    }

    /**
     * 获取指定id的菜单栏view
     *
     * @param itemId int
     * @return View
     */
    public View getMenuItem(int itemId) {
        for (int i = 0; i < mLayoutMenu.getChildCount(); i++) {
            View view = mLayoutMenu.getChildAt(i);
            if (view.getId() == itemId) {
                return view;
            }
        }
        return null;
    }

}
