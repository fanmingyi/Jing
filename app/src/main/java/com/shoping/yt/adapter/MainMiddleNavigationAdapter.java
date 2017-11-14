package com.shoping.yt.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.R;
import com.shoping.yt.bean.MainNavigationBean;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class MainMiddleNavigationAdapter extends BaseItemDraggableAdapter<MainNavigationBean, BaseViewHolder> {

    public MainMiddleNavigationAdapter(List<MainNavigationBean> data) {
        super(R.layout.item_list_main_midlle_navigation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainNavigationBean item) {
        helper.setImageBitmap(R.id.iv, BitmapFactory.decodeResource(mContext.getResources(), item.getImg_id()));
        helper.setText(R.id.tv, item.getName());
    }
}
