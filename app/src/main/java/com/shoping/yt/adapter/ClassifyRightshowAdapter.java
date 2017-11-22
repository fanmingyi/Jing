package com.shoping.yt.adapter;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.APP;
import com.shoping.yt.FLAG;
import com.shoping.yt.R;
import com.shoping.yt.bean.CartGoodsBean;
import com.shoping.yt.bean.CartGoodsBeanDao;
import com.shoping.yt.bean.ClassifyRightgoodBean;
import com.shoping.yt.bean.ClassifyRightgoodInsideBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        classifyRightshowInsideAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Snackbar.make(view, "模拟添加到购物车成功", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                CartGoodsBeanDao cartGoodsBeanDao = APP.getCartGoodsBeanDao();
                CartGoodsBean cartGoodsBean = new CartGoodsBean(false, UUID.randomUUID().toString(), 22.9f);
                cartGoodsBeanDao.insert(cartGoodsBean);

                Intent intent = new Intent(FLAG.NATIVE_CART_HAVE_GOOD);
                intent.putExtra(FLAG.NATIVE_CART_HAVE_GOOD,cartGoodsBean);
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);

            }
        });

        GridLayoutManager linearLayoutManager = new GridLayoutManager(mContext, 3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(classifyRightshowInsideAdapter);


    }
}
