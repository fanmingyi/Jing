package com.shoping.yt.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.FullyGridLayoutManager;
import com.shoping.yt.R;
import com.shoping.yt.bean.ClassifyRightgoodBean;
import com.shoping.yt.bean.ClassifyRightgoodInsideBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class ClassifyRightshowAdapter extends BaseItemDraggableAdapter<ClassifyRightgoodBean, BaseViewHolder> {

    public ClassifyRightshowAdapter(List<ClassifyRightgoodBean> data) {
        super(R.layout.item_list_category_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyRightgoodBean item) {
        helper.setText(R.id.tv_classify_titile, item.getCategoryName());
        helper.setVisible(R.id.tv_ranking, item.isShowRanking());
        RecyclerView rv = helper.getView(R.id.rv_classfify_inside);
        List<String> imgUri = item.getImgUri();
        List<ClassifyRightgoodInsideBean> goodSet = new ArrayList(imgUri.size());

        for (int i = 0; i < imgUri.size(); i++) {
            goodSet.add(new ClassifyRightgoodInsideBean(imgUri.get(i), item.getInfomation().get(i)));
        }
        ClassifyRightshowInsideAdapter classifyRightshowInsideAdapter = new ClassifyRightshowInsideAdapter(goodSet);
        GridLayoutManager linearLayoutManager = new FullyGridLayoutManager(mContext, 3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(classifyRightshowInsideAdapter);


    }
}
