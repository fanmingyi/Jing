package com.shoping.yt.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Created by fmy on 2017/11/12.
 */

public class DimenUtitls {

      public static int getSystemtHeight(Context context){
          int statusBarHeight1 = -1;

          int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

          if (identifier>0) {
              statusBarHeight1 = context.getResources().getDimensionPixelSize(identifier);
          }

          return statusBarHeight1;
      }

    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public static int getDaoHangHeight(Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }
}
