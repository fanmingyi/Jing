package com.shoping.yt.activity

import android.os.Bundle
import android.support.annotation.StringDef
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.shoping.yt.R
import com.shoping.yt.fragment.HomeFragment
import com.shoping.yt.utils.DimenUtitls
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initFragment()

        initNavigetion()
    }

    private fun initNavigetion() {

        val dpi = DimenUtitls.getDaoHangHeight(this)

        if (dpi > 0) {
            val layoutParams = view_fill.layoutParams
            layoutParams.height =dpi
            view_fill.layoutParams =layoutParams

            view_fill.visibility = View.VISIBLE
        }
    }

    lateinit var homeFragment: HomeFragment

    private fun initFragment() {

        val beginTransaction = supportFragmentManager.beginTransaction()

        homeFragment = HomeFragment()

        beginTransaction.add(R.id.fl_containt, homeFragment, HomeFlag)

        beginTransaction.commit()

    }

     val HomeFlag = "home"


    private fun initView() {

    }

companion object {
    val TEST1 = "home"
    const val TEST2 = "home"
}
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String


}
