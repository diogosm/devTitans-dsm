package com.example.recyclerviewtest.model;

import android.widget.ImageView;

public class ChildItem {

    private String ChildItemTitle;
    private int ChildItemRate;
    private ImageView ChildItemImage;
    private String ChildItemImageName;
    private boolean imageChanged;

    public ChildItem(String childItemTitle, int childItemRate){
        this.ChildItemTitle = childItemTitle;
        this.setChildItemRate(childItemRate);
        this.ChildItemImage = null;
        this.ChildItemImageName = null;
        this.imageChanged = false;
    }
    public String getChildItemTitle(){
        return ChildItemTitle;
    }
    public void setChildItemTitle(String childItemTitle){
        ChildItemTitle = childItemTitle;
    }

    public int getChildItemRate() {
        return ChildItemRate;
    }

    public void setChildItemRate(int childItemRate) {
        ChildItemRate = childItemRate;
    }

    public ImageView getChildItemImage() {
        return ChildItemImage;
    }

    public void setChildItemImage(ImageView childItemImage) {
        ChildItemImage = childItemImage;
    }

    public String getChildItemImageName() {
        return ChildItemImageName;
    }

    public void setChildItemImageName(String childItemImageName) {
        ChildItemImageName = childItemImageName;
    }

    public boolean isImageChanged() {
        return imageChanged;
    }

    public void setImageChanged(boolean imageChanged) {
        this.imageChanged = imageChanged;
    }
}
