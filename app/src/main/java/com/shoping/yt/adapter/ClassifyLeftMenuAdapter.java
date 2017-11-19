package com.shoping.yt.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.FLAG;
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

        View view = helper.getView(R.id.rootView);
        Object tag = view.getTag(R.id.menu_click_index);
        //当前条目是被点击的
        if (tag != null) {
            int index = (int) tag;
            int position = helper.getAdapterPosition();
            if (position == index) {
                view.setBackgroundColor(mContext.getResources().getColor(R.color.bg));
                helper.setTextColor(R.id.tv, mContext.getResources().getColor(R.color.category_menu_press));
            } else {
                view.setBackgroundColor(Color.WHITE);
                helper.setTextColor(R.id.tv, mContext.getResources().getColor(R.color.color_category_menu_tx));

            }
        }
        helper.setText(R.id.tv, item);
    }
}
