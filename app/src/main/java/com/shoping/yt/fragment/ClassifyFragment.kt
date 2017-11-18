package com.shoping.yt.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.shoping.yt.FLAG
import com.shoping.yt.R
import com.shoping.yt.activity.MainActivity
import com.shoping.yt.adapter.ClassifyLeftMenuAdapter
import com.shoping.yt.adapter.ClassifyRightshowAdapter
import com.shoping.yt.bean.ClassifyRightgoodBean
import com.shoping.yt.utils.DimenUtitls
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_classify.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class ClassifyFragment : Fragment() {

    var adImgurl = "https://m.360buyimg.com/mobilecms/s528x180_jfs/t5803/108/2354279071/106333/90538df2/592fbeefN2adb55ce.JPG";

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

        classifyLeftMenuAdapter.setOnItemClickListener { adapter, view, position ->

            val beforView = linearLayoutManager.getChildAt(current)
            beforView.setBackgroundColor(Color.WHITE)
            beforView.findViewById<TextView>(R.id.tv).setTextColor(mContext.resources.getColor(R.color.color_category_menu_tx))
            current = linearLayoutManager.getPosition(view)


            beforView.setTag(R.id.menu_click_index, -1)


            view.setBackgroundColor(mContext.resources.getColor(R.color.bg))

            view.findViewById<TextView>(R.id.tv).setTextColor(mContext.resources.getColor(R.color.category_menu_press))


            view.setTag(R.id.menu_click_index, position)
        }
        rv_menu.postDelayed({
            val beforeView = linearLayoutManager.getChildAt(0)

            beforeView?.let {

                it.performClick()
            }


        },300)

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
        initRight()
    }

    private fun initRight() {

        Picasso.with(mContext).load(adImgurl).into(iv_ad)
        val ClassifyRightgoodBeanSet = ArrayList<ClassifyRightgoodBean>()
        var imgSet = ArrayList<String>()
        imgSet.add("https://img14.360buyimg.com/n1/s450x450_jfs/t8554/233/1190311996/405021/e1436347/59b62b10N6d520c28.jpg")
        imgSet.add("https://img20.360buyimg.com/mobilecms/s700x700_jfs/t12178/265/284849018/30258/7b9d95ea/5a081677Na1564490.jpg!q70.jpg")
        imgSet.add("https://img14.360buyimg.com/n0/jfs/t9913/200/2424950357/270172/192bae2b/59f7e438Nf58e088d.jpg")
        var infoSet = ArrayList<String>()
        infoSet.add("Beats头戴式耳机")
        infoSet.add("洛诗琳落肩袖衣")
        infoSet.add("戴尔燃700xp")
        val zhuanchang = ClassifyRightgoodBean("专场推荐", imgSet, infoSet, false)
        ClassifyRightgoodBeanSet.add(zhuanchang)


        imgSet = ArrayList<String>()
        infoSet = ArrayList<String>()

//        for (i in 0..3) {
        imgSet.add("https://img14.360buyimg.com/n1/s450x450_jfs/t10690/249/1626659345/69516/b3643998/59e4279aNff3d63ac.jpg")
        imgSet.add("https://img13.360buyimg.com/n1/jfs/t11815/17/1693248377/207273/624cf1a4/5a068aa6N6e5d9a92.jpg")
        imgSet.add("https://img11.360buyimg.com/n1/jfs/t6127/83/1625860522/206460/724e074/593522feN93f6d885.jpg")


        imgSet.add("https://img13.360buyimg.com/n1/jfs/t11434/8/1209515383/204394/987892d/59ff23e2Nc69d5879.jpg")
        imgSet.add("https://img10.360buyimg.com/n1/jfs/t8728/131/789293675/610545/d09c49f8/59af6162Na1457cc0.jpg")
        imgSet.add("https://img12.360buyimg.com/n1/jfs/t4312/186/779685079/140169/5122dbc/58b92705Nec6f016c.jpg")


        imgSet.add("https://img13.360buyimg.com/n1/jfs/t1924/274/2855034241/329322/28e481e4/56f0a06aNa5846311.jpg")
        imgSet.add("https://img14.360buyimg.com/n0/jfs/t13903/318/372830385/304296/c63dc43f/5a0a7f5aN434120a6.jpg")
        imgSet.add("https://img12.360buyimg.com/n1/jfs/t8983/303/807168317/585152/d2b66523/59af52cfN0c691081.jpg")


        infoSet.add("手机")
        infoSet.add("洗衣机")
        infoSet.add("三鹿奶粉")

        infoSet.add("生鲜")
        infoSet.add("落肩袖衣")
        infoSet.add("法国酒")

        infoSet.add("开心果")
        infoSet.add("蛋糕")
        infoSet.add("碧螺春")
//        }

        val hot = ClassifyRightgoodBean("热门分类", imgSet, infoSet, true)

        ClassifyRightgoodBeanSet.add(hot)
        val classifyRightshowAdapter = ClassifyRightshowAdapter(ClassifyRightgoodBeanSet)
        val linearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        rv_rightinfo.layoutManager = linearLayoutManager
        rv_rightinfo.adapter = classifyRightshowAdapter


    }

    var current = 0;
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
