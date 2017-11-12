package com.shoping.yt.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.shoping.yt.R
import com.shoping.yt.utils.DimenUtitls
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        supportActionBar?.let {
            it.hide()
        }

        setContentView(R.layout.activity_splash)

        init()

        initJump()
    }

    private fun init() {
        var sbtm = SystemBarTintManager(this)

        sbtm.setNavigationBarTintEnabled(true)

        sbtm.isStatusBarTintEnabled = true

//        sbtm.setNavigationBarAlpha(1F)

        sbtm.setTintColor(Color.RED)

//        sbtm.setStatusBarAlpha(1F)
    }

    override fun onStop() {
        super.onStop()

        finish()
    }

   private lateinit var subscribe: Disposable

    /**
     * 初始跳转事件
     */
    fun initJump() {

        subscribe = Observable.interval(1, TimeUnit.SECONDS).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe({ along ->
                    if (along == 2L) {
                        subscribe?.let {
                            val intent = Intent(SplashActivity@ this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                            subscribe.dispose()
                        }
                    }


                })


    }
}
