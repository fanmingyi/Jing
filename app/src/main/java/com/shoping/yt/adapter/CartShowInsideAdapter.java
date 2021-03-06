package com.shoping.yt.adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoping.yt.APP;
import com.shoping.yt.R;
import com.shoping.yt.bean.CartGoodsBean;
import com.shoping.yt.bean.CartGoodsBeanDao;
import com.shoping.yt.minterface.IRbChangListener;

import java.util.List;

import static com.shoping.yt.FLAG.NATIVE_CARTCHECED_BRODCAST;

/**
 * Created by fmy on 2017/11/13.
 */

public class CartShowInsideAdapter extends BaseItemDraggableAdapter<CartGoodsBean, BaseViewHolder> {

    IRbChangListener listner;

    public CartShowInsideAdapter(List<CartGoodsBean> data, IRbChangListener listner) {
        super(R.layout.item_list_cart_inside, data);
        this.listner = listner;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final CartGoodsBean item) {

        helper.itemView.setTag(item);

        TextView tv_prise_original = helper.getView(R.id.tv_prise_original);

        tv_prise_original.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        helper.setText(R.id.tv_prise, "¥" + item.getPrise());

        final CheckBox cb = helper.getView(R.id.cb);

        cb.setOnCheckedChangeListener(null);
        if (item.isCheck()) {

            cb.setChecked(true);

            changeCh(true, item);

        } else {

            changeCh(false, item);

            cb.setChecked(false);
        }
        if (listner != null) {
            listner.change(cb, item);
        }
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CartGoodsBeanDao cartGoodsBeanDao = APP.getCartGoodsBeanDao();
                cartGoodsBeanDao.update(item);
                item.setCheck(isChecked);

                changeCh(isChecked, (CartGoodsBean) item.clone());

                if (listner != null) {
                    listner.change(cb, item);
                }
            }
        });

    }

    private void changeCh(boolean isChecked, CartGoodsBean item) {

        CartGoodsBean clone = (CartGoodsBean) item.clone();
        Intent intent = new Intent(NATIVE_CARTCHECED_BRODCAST);
        intent.putExtra(NATIVE_CARTCHECED_BRODCAST, clone);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }


}
