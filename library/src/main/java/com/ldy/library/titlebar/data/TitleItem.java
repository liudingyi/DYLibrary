package com.ldy.library.titlebar.data;

/**
 * 导航栏和菜单栏每项的数据结构
 */
public class TitleItem {

    private int itemId;//id
    private String content;//文本
    private int background;//文本背景
    private int src;//图片资源

    public TitleItem(int itemId, int src) {
        this.itemId = itemId;
        this.src = src;
    }

    public TitleItem(int itemId, String content) {
        this.itemId = itemId;
        this.content = content;
    }

    public TitleItem(int itemId, String content, int background) {
        this.itemId = itemId;
        this.content = content;
        this.background = background;
    }

    public TitleItem(int itemId, String content, int background, int src) {
        this.itemId = itemId;
        this.content = content;
        this.background = background;
        this.src = src;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public int getItemId() {
        return itemId;
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
