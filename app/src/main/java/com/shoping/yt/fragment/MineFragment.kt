package com.shoping.yt.fragment

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.readystatesoftware.systembartint.SystemBarTintManager

import com.shoping.yt.R
import com.shoping.yt.activity.MainActivity
import com.shoping.yt.utils.DimenUtitls
import kotlinx.android.synthetic.main.fragment_home.*


class MineFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_mine, container, false)
    }

    private var mActivity: MainActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mActivity = context as MainActivity?
    }

    lateinit var sbtm: SystemBarTintManager
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sbtm = mActivity?.getstm()!!
        initNavigation()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            sbtm.setTintColor(Color.TRANSPARENT)
        }
    }

    private fun initNavigation() {

        sbtm.setNavigationBarTintEnabled(true)

        sbtm.isStatusBarTintEnabled = true

        sbtm.setTintColor(Color.TRANSPARENT)

        val systemtHeight = DimenUtitls.getSystemtHeight(mActivity)
//
        val layoutParams = (tb_title.layoutParams as ConstraintLayout.LayoutParams)
//
        layoutParams.topMargin = systemtHeight
//
        tb_title.layoutParams = layoutParams
//

    }

}// Required empty public constructor
