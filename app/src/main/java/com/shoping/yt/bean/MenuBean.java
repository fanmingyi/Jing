package com.shoping.yt.bean;

/**
 * Created by fmy on 2017/11/19.
 */

public class MenuBean {
    private String name ;

    @Override
    public String toString() {
        return "MenuBean{" +
                "name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public MenuBean() {

    }

    public MenuBean(String name, boolean flag) {
        this.name = name;
        this.flag = flag;
    }

    private boolean flag;
}
