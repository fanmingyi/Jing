package com.shoping.yt.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.R;
import com.shoping.yt.bean.ClassifyRightgoodInsideBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class CartShowInsideAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public CartShowInsideAdapter(List<String> data) {
        super(R.layout.item_list_cart_inside, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tv_classify_titile, item.getInfomation());
////        helper.setText(R.id.tv_classify_titile, item.getInfomation());iv_show_good
//        ImageView iv = helper.getView(R.id.iv_show_good);
//        Picasso.with(mContext).load(item.getImgUri()).into(iv);
    }



}
