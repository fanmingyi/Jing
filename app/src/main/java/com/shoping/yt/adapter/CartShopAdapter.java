package com.shoping.yt.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.content.LocalBroadcastManager;
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
import com.shoping.yt.APP;
import com.shoping.yt.R;
import com.shoping.yt.bean.CartGoodsBean;
import com.shoping.yt.bean.CartGoodsBeanDao;
import com.shoping.yt.minterface.IRbChangListener;

import java.util.ArrayList;
import java.util.List;

import static com.shoping.yt.FLAG.NATIVE_CARTCHECED_BRODCAST;
import static com.shoping.yt.FLAG.NATIVE_CART_IS_SWIED_DEL;

/**
 * Created by fmy on 2017/11/13.
 */

public class CartShopAdapter extends BaseItemDraggableAdapter<List<CartGoodsBean>, BaseViewHolder> {


    List<List<CartGoodsBean>> data;

    public CartShopAdapter(List<List<CartGoodsBean>> data, Context context) {
        super(R.layout.item_list_cart, data);
        this.data = data;
    }

    class MyOnItemSwipeListener implements OnItemSwipeListener {
        CartShowInsideAdapter adapter;

        public MyOnItemSwipeListener(CartShowInsideAdapter adapter) {
            this.adapter = adapter;
        }

        public MyOnItemSwipeListener() {
        }

        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            final CartGoodsBean it = (CartGoodsBean) viewHolder.itemView.getTag();
            CartGoodsBeanDao cartGoodsBeanDao = APP.getCartGoodsBeanDao();
            cartGoodsBeanDao.deleteByKey(it.getID());
            Intent intent = new Intent(NATIVE_CARTCHECED_BRODCAST);
            intent.putExtra(NATIVE_CART_IS_SWIED_DEL, true);
            Log.e("FMY", "滑动删除==" + it.getID() + " 价格" + it.getPrise());
            intent.putExtra(NATIVE_CARTCHECED_BRODCAST, (CartGoodsBean) it.clone());
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

            List<CartGoodsBean> index = null;
            for (List<CartGoodsBean> datum : data) {
                if ((datum.contains(it) && datum.size() == 1) || datum.size() == 0) {
                    index = datum;
                    break;
                }
            }
            if (index != null) {
                data.remove(index);
                notifyDataSetChanged();
            }


        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

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


    ArrayList<CartShowInsideAdapter> adapters = new ArrayList<>();

    public ArrayList<CartShowInsideAdapter> getAdapter() {
        return adapters;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final List<CartGoodsBean> item) {


        helper.getView(R.id.nsv_root).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("FMY", "被触摸");
                return false;
            }
        });

        final CheckBox cb = (CheckBox) helper.getView(R.id.cb);
        final RecyclerView rv = helper.getView(R.id.rv_cart_inside);

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
                        cartShowInsideAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        rv.setAdapter(cartShowInsideAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        //rv.getChildAdapterPosition()
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(cartShowInsideAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        // 开启拖拽
        cartShowInsideAdapter.enableDragItem(itemTouchHelper);
        cartShowInsideAdapter.setOnItemDragListener(null);

        cartShowInsideAdapter.enableSwipeItem();
        cartShowInsideAdapter.setOnItemSwipeListener(new MyOnItemSwipeListener(cartShowInsideAdapter));

        Log.e("FMY", "onver===="+cartShowInsideAdapter.toString() +"  ====>>"+ helper.getAdapterPosition());

        cb.setOnCheckedChangeListener(onCheckedChangeListener);

    }
}
