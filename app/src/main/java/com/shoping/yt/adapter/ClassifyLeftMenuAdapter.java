package com.shoping.yt.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class ClassifyLeftMenuAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public ClassifyLeftMenuAdapter(List<String> data) {
        super(R.layout.item_classify_left_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
