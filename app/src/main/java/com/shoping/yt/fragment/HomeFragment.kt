package com.shoping.yt.fragment


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.shoping.yt.R
import com.shoping.yt.activity.MainActivity
import com.shoping.yt.adapter.MainMiddleNavigationAdapter
import com.shoping.yt.bean.MainNavigationBean
import com.shoping.yt.utils.DimenUtitls
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var mContext: MainActivity;
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = (context as MainActivity?)!!

    }

    var url_maps = HashMap<String, String>()

    private fun initNavigation() {
        var sbtm = SystemBarTintManager(mContext)

        sbtm.setNavigationBarTintEnabled(true)

        sbtm.isStatusBarTintEnabled = true

        sbtm.setTintColor(Color.TRANSPARENT)

        val systemtHeight = DimenUtitls.getSystemtHeight(mContext)
//
        val layoutParams = (tb_title.layoutParams as ConstraintLayout.LayoutParams)

        layoutParams.topMargin = systemtHeight

        tb_title.layoutParams = layoutParams


    }

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initSlid()
        initNavigation()
        initMiddleNavigation()
    }

    private fun initMiddleNavigation() {
        var data = ArrayList<MainNavigationBean>()
        for (i in navigationIcon.indices) {
            val mainNavigationBean = MainNavigationBean(navigationString[i], navigationIcon[i])
            data.add(mainNavigationBean)
        }
        val mainMiddleNavigationAdapter = MainMiddleNavigationAdapter(data)
        val gridLayoutManager = GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);
        rv_middle_natvigation.layoutManager =gridLayoutManager;
        rv_middle_natvigation.adapter = mainMiddleNavigationAdapter


        var itemDragAndSwipeCallback =  ItemDragAndSwipeCallback(mainMiddleNavigationAdapter);
        var itemTouchHelper =  ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv_middle_natvigation)


        mainMiddleNavigationAdapter.enableDragItem(itemTouchHelper)

        mainMiddleNavigationAdapter.setOnItemDragListener(onItemDragListener)
    }

    internal var onItemSwipeListener: OnItemSwipeListener = object : OnItemSwipeListener {
        override fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {}
        override fun clearView(viewHolder: RecyclerView.ViewHolder, pos: Int) {}
        override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder, pos: Int) {}

        override fun onItemSwipeMoving(canvas: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, isCurrentlyActive: Boolean) {

        }
    }
    internal var onItemDragListener: OnItemDragListener = object : OnItemDragListener {
        override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {}
        override fun onItemDragMoving(source: RecyclerView.ViewHolder, from: Int, target: RecyclerView.ViewHolder, to: Int) {}
        override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {}
    }

    var navigationString = arrayOf("电技", "闲置","美食","租赁","海外","二手","限时优惠","夺宝","日常")
    var navigationIcon = intArrayOf(
            R.mipmap.ic_main_middle_game,
            R.mipmap.ic_main_middle_secondhand,
            R.mipmap.ic_main_middle_takeout,
            R.mipmap.ic_main_middle_renting,
            R.mipmap.ic_main_middle_goodcargo,
            R.mipmap.ic_main_middle_discounts,
            R.mipmap.ic_main_middle_rush,
            R.mipmap.ic_main_middle_daily

    )


    private fun initSlid() {
        url_maps.put("三只松鼠限时特惠", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510480484329&di=ee201068a45d085a26447f2c9e2e061a&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01ab3557b10cfe0000018c1be6a69c.png")
        url_maps.put("潮男潮女限时惠", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510480649925&di=cd6c6703f1abd84e2bb116114efc4ed1&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012787554c0a88000001bf721cd092.jpg")
        url_maps.put("良品铺子只为你", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510480996499&di=b98fcac4cc7c3772a5ee4ceb4f6a3f1b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01dca55782461e0000012e7edd02ee.jpg")
        url_maps.put("更多优惠只在双11", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510481060514&di=905ee14a48b6de39a37504e4138893d8&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0188e75782462a0000012e7ecf1d4a.jpg")
//        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal",R.drawable.nemo);
//        file_maps.put("Big Bang Theory",R.drawable.up);
//        file_maps.put("House of Cards",R.drawable.wall);
//        file_maps.put("Game of Thrones", R.drawable.toystory);

        for (name in url_maps.keys) {

            val textSliderView = TextSliderView(mContext)
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps[name])
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(null)

            //add your extra information 点击图片时可用到
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                    .putString("extra", name)

            slider.addSlider(textSliderView)
        }


        // 设置默认过渡效果(可在xml中设置)
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion)
        // 设置默认指示器位置(默认指示器白色,位置在sliderlayout底部)
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        // 设置自定义指示器(位置自定义)
//        slider.setCustomIndicator(custom_indicator2)
        // 设置TextView自定义动画
        slider.setCustomAnimation(DescriptionAnimation())
//        slider.setCustomAnimation(new ChildAnimationExample()); // 多种效果，进入该类修改，参考效果github/daimajia/AndroidViewAnimations
        // 设置持续时间
        slider.setDuration(2000)
//        slider.addOnPageChangeListener(this)
    }

}// Required empty public constructor