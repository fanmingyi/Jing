package com.shoping.yt.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.shoping.yt.FullyGridLayoutManager;
import com.shoping.yt.R;
import com.shoping.yt.bean.ClassifyRightgoodBean;
import com.shoping.yt.bean.ClassifyRightgoodInsideBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class CartShopAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public CartShopAdapter(List<String> data) {
        super(R.layout.item_list_cart, data);
    }

    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos){}
        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {}
        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {}
    };

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {}
        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}
        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {}

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };



    @Override
    protected void convert(BaseViewHolder helper, String item) {

        RecyclerView rv = helper.getView(R.id.rv_cart_inside);

        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        CartShowInsideAdapter cartShowInsideAdapter = new CartShowInsideAdapter(data);
        rv.setAdapter(cartShowInsideAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(cartShowInsideAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        // 开启拖拽
        cartShowInsideAdapter.enableDragItem(itemTouchHelper);
        cartShowInsideAdapter.setOnItemDragListener(onItemDragListener);

// 开启滑动删除
        cartShowInsideAdapter.enableSwipeItem();
        cartShowInsideAdapter.setOnItemSwipeListener(onItemSwipeListener);


    }
}
