package com.fmsfrontend.ltctours.modelClass;

public class ItemModel {
    int image;
    String title;
    String item_title;
    String item_desc;
    private boolean expanded;

    public ItemModel(int image, String title, String item_title, String item_desc) {
        this.image = image;
        this.title = title;
        this.item_title = item_title;
        this.item_desc = item_desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
