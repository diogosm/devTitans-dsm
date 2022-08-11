package com.example.recyclerviewtest.model;

public class ChildItem {

    private String ChildItemTitle;
    private int ChildItemRate;

    public ChildItem(String childItemTitle, int childItemRate){
        this.ChildItemTitle = childItemTitle;
        this.setChildItemRate(childItemRate);
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
}
