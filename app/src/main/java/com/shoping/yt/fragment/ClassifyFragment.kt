package com.shoping.yt.fragment

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.shoping.yt.R
import com.shoping.yt.activity.MainActivity
import com.shoping.yt.adapter.ClassifyLeftMenuAdapter
import com.shoping.yt.utils.DimenUtitls
import kotlinx.android.synthetic.main.fragment_classify.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class ClassifyFragment : Fragment() {

    lateinit var classifyName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        classifyName = arrayOf("推荐分类", "京东超市", "国际名牌", "奢侈品", "全球购", "男装", "女装", "男鞋", "女鞋", "手机", "汽车用品", "家具家装", "礼品鲜花", "宠物生活")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            sbtm.setTintColor(mContext.resources.getColor(R.color.titilegrey))
        }


    }

    private fun initLeftNavigation() {
        val linearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        val strings = Arrays.asList(*classifyName!!)
        val classifyLeftMenuAdapter = ClassifyLeftMenuAdapter(strings)
        rv_menu.layoutManager = linearLayoutManager
        rv_menu.adapter = classifyLeftMenuAdapter
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_classify, container, false)


    }

    lateinit var mContext: Context

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initNavigation()
        initLeftNavigation()
    }

    lateinit var sbtm: SystemBarTintManager
    private fun initNavigation() {
        sbtm = (mContext as MainActivity).getstm()



        sbtm.setTintColor(mContext.resources.getColor(R.color.titilegrey))

        val systemtHeight = DimenUtitls.getSystemtHeight(mContext)
//
        val layoutParams = tb_title_classify.layoutParams as ConstraintLayout.LayoutParams
//
        layoutParams.topMargin = systemtHeight
//
        tb_title_classify.layoutParams = layoutParams
//

    }

}
