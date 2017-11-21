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
import android.util.Log;
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
import static com.shoping.yt.FLAG.NATIVE_CART_IS_SWIED_DEL;

/**
 * Created by fmy on 2017/11/13.
 */

public class CartShopAdapter extends BaseItemDraggableAdapter<ArrayList<CartGoodsBean>, BaseViewHolder> {


    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rv;

    public CartShopAdapter(List<ArrayList<CartGoodsBean>> data, Context context) {
        super(R.layout.item_list_cart, data);

    }

    public void myNotifyAll() {
        for (CartShowInsideAdapter adapter : adapters) {
            adapter.notifyDataSetChanged();
        }
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

//
            CartGoodsBean it = (CartGoodsBean) viewHolder.itemView.getTag();


            Intent intent = new Intent(NATIVE_CARTCHECED_BRODCAST);
            intent.putExtra(NATIVE_CART_IS_SWIED_DEL, true);
            Log.e("FMY", "滑动删除==" + it.getID() + " 价格" + it.getPrise());
            intent.putExtra(NATIVE_CARTCHECED_BRODCAST, (CartGoodsBean)it.clone());
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };

    ArrayList<CartShowInsideAdapter> adapters = new ArrayList<>();


    @Override
    protected void convert(final BaseViewHolder helper, final ArrayList<CartGoodsBean> item) {


        helper.getView(R.id.nsv_root).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("FMY", "被触摸");
                return false;
            }
        });

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


        final CartShowInsideAdapter cartShowInsideAdapter = new CartShowInsideAdapter(item, new IRbChangListener() {

            @Override
            public void change(CheckBox checkBox, CartGoodsBean bean) {
                cb.setTag(true);

                    boolean myflag = false;

                    for (CartGoodsBean cartGoodsBean : item) {
                        if (!cartGoodsBean.isCheck()) {
                            myflag = true;
                            break;
                        }
                    }
                    if (myflag) {

                        cb.setChecked(false);
                    } else {
                        cb.setChecked(true);
                    }


                cb.setTag(false);
            }
        });
        final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Object tag1 = buttonView.getTag();
                if (tag1 != null) {
                    boolean tag = (boolean) tag1;
                    if (tag) {
                        return;
                    }
                }


                for (CartGoodsBean cartGoodsBean : item) {
                    cartGoodsBean.setCheck(isChecked);
                }
                try {

                    if (!rv.isComputingLayout()) {
//                        CartShopAdapter.this.notifyDataSetChanged();
                        CartShopAdapter.this.myNotifyAll();
//                        cartShowInsideAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
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

        cartShowInsideAdapter.enableSwipeItem();
        cartShowInsideAdapter.setOnItemSwipeListener(onItemSwipeListener);

        adapters.add(cartShowInsideAdapter);
        cb.setOnCheckedChangeListener(onCheckedChangeListener);

    }
}
