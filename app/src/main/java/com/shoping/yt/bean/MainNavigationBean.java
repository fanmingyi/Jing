package com.shoping.yt.bean;

/**
 * Created by fmy on 2017/11/13.
 */

public class MainNavigationBean {
    String name;
    int img_id;

    public MainNavigationBean(String name, int img_id) {
        this.name = name;
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
