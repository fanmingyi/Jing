package com.shoping.yt.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.shoping.yt.FullyGridLayoutManager;
import com.shoping.yt.R;
import com.shoping.yt.bean.CartGoodsBean;
import com.shoping.yt.bean.ClassifyRightgoodBean;
import com.shoping.yt.bean.ClassifyRightgoodInsideBean;
import com.shoping.yt.minterface.IRbChangListener;

import java.util.ArrayList;
import java.util.List;

import static com.shoping.yt.FLAG.NATIVE_CARTALL_CHECKED_BRODCAST;
import static com.shoping.yt.FLAG.NATIVE_CARTCHECED_BRODCAST;

/**
 * Created by fmy on 2017/11/13.
 */

public class CartShopAdapter extends BaseItemDraggableAdapter<ArrayList<CartGoodsBean>, BaseViewHolder> {

    private List<ArrayList<CartGoodsBean>> data;
    private CartShowInsideAdapter cartShowInsideAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rv;

    public CartShopAdapter(List<ArrayList<CartGoodsBean>> data, Context context) {
        super(R.layout.item_list_cart, data);
        this.data = data;


    }


    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        }
    };

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {


        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };


    @Override
    protected void convert(final BaseViewHolder helper, final ArrayList<CartGoodsBean> item) {

        final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                for (CartGoodsBean cartGoodsBean : item) {
                    cartGoodsBean.setCheck(isChecked);
                }
                try {

                    if (!rv.isComputingLayout()) {
                        CartShopAdapter.this.notifyDataSetChanged();
                        cartShowInsideAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        final CheckBox cb = (CheckBox) helper.getView(R.id.cb);
        rv = helper.getView(R.id.rv_cart_inside);

        boolean flag = true;

        for (CartGoodsBean cartGoodsBean : item) {
            boolean check = cartGoodsBean.isCheck();
            if (!check) {
                flag = false;
            }
        }

        if (flag) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }


        cartShowInsideAdapter = new CartShowInsideAdapter(item, new IRbChangListener() {

            @Override
            public void change(CheckBox checkBox, CartGoodsBean bean) {
                cb.setOnCheckedChangeListener(null);
                if (checkBox.isChecked()) {
                boolean myflag =false;

                    for (CartGoodsBean cartGoodsBean : item) {
                        if (!cartGoodsBean.isCheck()) {
                            myflag =true;
                           break;
                        }
                    }
                    if (myflag) {

                        cb.setChecked(false);
                    } else {
                        cb.setChecked(true);
                    }
                } else {
                    cb.setChecked(false);
                }
                cb.setOnCheckedChangeListener(onCheckedChangeListener);

            }
        });

        rv.setAdapter(cartShowInsideAdapter);
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        //rv.getChildAdapterPosition()
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(cartShowInsideAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        // 开启拖拽
        cartShowInsideAdapter.enableDragItem(itemTouchHelper);
        cartShowInsideAdapter.setOnItemDragListener(onItemDragListener);

        cb.setOnCheckedChangeListener(onCheckedChangeListener);

    }
}
