package com.shoping.yt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.listener.OnItemDragListener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by fmy on 2017/11/11.
 */

public class FLAG {
    //当前按钮是否被点击
    public static final int MENU_ISCLICK = 0;
    //被点击条目的viewhold下标
    public static final int MENU_CLICK_INDEX = 1;
}
