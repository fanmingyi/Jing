package com.shoping.yt;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.shoping.yt.bean.CartGoodsBean;
import com.shoping.yt.bean.CartGoodsBeanDao;
import com.shoping.yt.bean.DaoMaster;
import com.shoping.yt.bean.DaoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by fmy on 2017/11/21.
 */

public class APP extends Application {

    private static DaoMaster.DevOpenHelper devOpenHelper;
    private static DaoMaster daoMaster;
    private static CartGoodsBeanDao cartGoodsBeanDao;

    @Override
    public void onCreate() {
        super.onCreate();

        devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        cartGoodsBeanDao = daoSession.getCartGoodsBeanDao();

        CartGoodsBean cartGoodsBean = new CartGoodsBean(false, UUID.randomUUID().toString(), 23.0f);
        CartGoodsBean cartGoodsBean2 = new CartGoodsBean(false, UUID.randomUUID().toString(), 23.0f);
        CartGoodsBean cartGoodsBean3 = new CartGoodsBean(false, UUID.randomUUID().toString(), 23.0f);

        getCartGoodsBeanDao().insert(cartGoodsBean);
        getCartGoodsBeanDao().insert(cartGoodsBean2);
        getCartGoodsBeanDao().insert(cartGoodsBean3);
    }


    public static CartGoodsBeanDao getCartGoodsBeanDao() {

        return cartGoodsBeanDao;
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }
}
