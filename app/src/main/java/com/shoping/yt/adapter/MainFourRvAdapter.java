package com.shoping.yt.adapter;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.R;
import com.shoping.yt.bean.MainNavigationBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class MainFourRvAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public MainFourRvAdapter(List<String> data) {
        super(R.layout.item_list_main_four, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        View iv = helper.getView(R.id.imageView);
        Picasso.with(mContext).load(item).into((ImageView) iv);
    }
}
