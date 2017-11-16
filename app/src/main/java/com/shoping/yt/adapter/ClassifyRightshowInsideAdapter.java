package com.shoping.yt.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.R;
import com.shoping.yt.bean.ClassifyRightgoodBean;
import com.shoping.yt.bean.ClassifyRightgoodInsideBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class ClassifyRightshowInsideAdapter extends BaseItemDraggableAdapter<ClassifyRightgoodInsideBean, BaseViewHolder> {

    public ClassifyRightshowInsideAdapter(List<ClassifyRightgoodInsideBean> data) {
        super(R.layout.item_list_category_right_inside, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyRightgoodInsideBean item) {
        helper.setText(R.id.tv_classify_titile, item.getInfomation());
//        helper.setText(R.id.tv_classify_titile, item.getInfomation());iv_show_good
        ImageView iv = helper.getView(R.id.iv_show_good);
        Picasso.with(mContext).load(item.getImgUri()).into(iv);
    }



}
