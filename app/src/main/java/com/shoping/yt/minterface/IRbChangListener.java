package com.shoping.yt.minterface;

import android.widget.CheckBox;

import com.shoping.yt.bean.CartGoodsBean;

/**
 * Created by fmy on 2017/11/19.
 */

public interface IRbChangListener {
    void change(CheckBox checkBox, CartGoodsBean bean);
}
