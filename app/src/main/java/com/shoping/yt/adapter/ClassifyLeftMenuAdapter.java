package com.shoping.yt.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.FLAG;
import com.shoping.yt.R;
import com.shoping.yt.bean.MenuBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class ClassifyLeftMenuAdapter extends BaseItemDraggableAdapter<MenuBean, BaseViewHolder> {

    public ClassifyLeftMenuAdapter(List<MenuBean> data) {
        super(R.layout.item_classify_left_menu, data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean item) {

        View view = helper.getView(R.id.rootView);


        if (item.isFlag()) {
            view.setBackgroundColor(mContext.getResources().getColor(R.color.bg));
            helper.setTextColor(R.id.tv, mContext.getResources().getColor(R.color.category_menu_press));
        } else {
            view.setBackgroundColor(Color.WHITE);
            helper.setTextColor(R.id.tv, mContext.getResources().getColor(R.color.color_category_menu_tx));

        }

        helper.setText(R.id.tv, item.getName());
    }
}
