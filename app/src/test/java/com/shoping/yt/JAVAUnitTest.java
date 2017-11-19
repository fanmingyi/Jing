package com.shoping.yt;

import android.util.Log;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by fmy on 2017/11/11.
 */

public class JAVAUnitTest {
    private static final String TAG = "JAVAUnitTest";

    @Test
    public void test() {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");

        String format = df.format(3.888);
        System.out.println("======================================FMY"+format);

    }
}
