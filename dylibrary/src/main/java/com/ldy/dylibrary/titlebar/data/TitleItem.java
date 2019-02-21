package com.ldy.dylibrary.titlebar.data;

/**
 * 导航栏和菜单栏每项的数据结构
 */
public class TitleItem {

    private String content;//文本
    private int background;//文本背景
    private int src;//图片资源

    public TitleItem(int src) {
        this.src = src;
    }

    public TitleItem(String content) {
        this.content = content;
    }

    public TitleItem(String content, int background) {
        this.content = content;
        this.background = background;
    }

    public TitleItem(String content, int background, int src) {
        this.content = content;
        this.background = background;
        this.src = src;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getContent() {
        return content;
    }

    public int getBackground() {
        return background;
    }

    public int getSrc() {
        return src;
    }
}
