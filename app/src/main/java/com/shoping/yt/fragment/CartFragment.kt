package com.shoping.yt.fragment


import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.readystatesoftware.systembartint.SystemBarTintManager

import com.shoping.yt.R
import com.shoping.yt.activity.MainActivity
import com.shoping.yt.adapter.CartShopAdapter
import com.shoping.yt.adapter.ClassifyLeftMenuAdapter
import com.shoping.yt.utils.DimenUtitls
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_classify.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {
    lateinit var sbtm: SystemBarTintManager

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            sbtm.setTintColor(mMainActivity.resources.getColor(R.color.titilegrey))
        }


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initNavigation()
        initAdapter()
    }

    private fun initAdapter() {

        val data = ArrayList<String>()
        data.add("")
        data.add("")
        data.add("")
        val cartShopAdapter = CartShopAdapter(data)


        val linearLayoutManager = LinearLayoutManager(mMainActivity, LinearLayoutManager.VERTICAL, false)

        rv_goods.layoutManager = linearLayoutManager
        rv_goods.adapter = cartShopAdapter

    }

    private fun initNavigation() {
        sbtm = mMainActivity.getstm()

//        mMainActivity.resources.getColor(R.color.titilegrey)

        sbtm.setTintColor(Color.BLUE)

        val systemtHeight = DimenUtitls.getSystemtHeight(mMainActivity)

        val layoutParams = tb_title_cart.layoutParams as ConstraintLayout.LayoutParams

        layoutParams.topMargin = systemtHeight

        tb_title_cart.layoutParams = layoutParams


    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mMainActivity = context as MainActivity
    }

    lateinit var mMainActivity: MainActivity
}
