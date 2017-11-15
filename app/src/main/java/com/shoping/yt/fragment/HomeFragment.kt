package com.shoping.yt.fragment


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
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
import com.shoping.yt.adapter.MainFinalAdapter
import com.shoping.yt.adapter.MainFourRvAdapter
import com.shoping.yt.adapter.MainMiddleNavigationAdapter
import com.shoping.yt.bean.MainNavigationBean
import com.shoping.yt.utils.DimenUtitls
import com.squareup.picasso.Picasso
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

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            val scrollY = nsv.scrollY
            if (scrollY==0)
                sbtm.setTintColor(Color.TRANSPARENT)
        }

    }

    var url_maps = HashMap<String, String>()
    val TAG = "FMY";
    lateinit var sbtm: SystemBarTintManager ;
    private fun initNavigation() {
         sbtm = (mContext as MainActivity).getstm()

        sbtm.setNavigationBarTintEnabled(true)

        sbtm.isStatusBarTintEnabled = true

        sbtm.setTintColor(Color.TRANSPARENT)

        val systemtHeight = DimenUtitls.getSystemtHeight(mContext)
//
        val layoutParams = (tb_title.layoutParams as RelativeLayout.LayoutParams)
//
        layoutParams.topMargin = systemtHeight
//
        tb_title.layoutParams = layoutParams
//
        nsv.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->


            val scrollY1 = v!!.scrollY
            if (scrollY1 == 0) {
                sbtm.setTintColor(Color.TRANSPARENT)
            } else {
                sbtm.setTintColor(mContext.resources.getColor(R.color.titilegrey))
            }

            val alph = if (v.scrollY > 0xff) 0xff else v.scrollY

            tb_title.setBackgroundColor(Color.argb(alph, 0xff, 0xff, 0xff))
            tb_bottomline.alpha = (alph / 0xff).toFloat()
            if (alph / 0xff >= 0.7) {

                ib_scan.setImageResource(R.mipmap.ic_scan_gray)
                ib_msg.setImageResource(R.mipmap.ic_msg_gray)
                et_search.setHintTextColor(Color.WHITE)
                et_search.setTextColor(Color.WHITE)
                et_search.setBackgroundResource(R.drawable.et_search_shape2)
                val drawable = resources.getDrawable(R.mipmap.item_while_search)
                drawable.setBounds(0, 0, DimenUtitls.dip2px(mContext, 16f), DimenUtitls.dip2px(mContext, 16f))
                et_search.setCompoundDrawables(drawable, null, null, null)
            } else {
                val drawable = resources.getDrawable(R.mipmap.item_search)
                drawable.setBounds(0, 0, DimenUtitls.dip2px(mContext, 16f), DimenUtitls.dip2px(mContext, 16f))
                et_search.setCompoundDrawables(drawable, null, null, null)
                et_search.setBackgroundResource(R.drawable.et_search_shape)
                et_search.setHintTextColor(Color.GRAY)
                et_search.setTextColor(Color.GRAY)
                ib_scan.setImageResource(R.mipmap.ic_scan)
                ib_msg.setImageResource(R.mipmap.ic_msg)
            }
        }
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
        initImage()
        initFourRv()
        initFinalRv()
    }


    private fun initFinalRv() {
        val data = ArrayList<String>()
        data.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3772146472,2512961182&fm=200&gp=0.jpg")
        data.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3772146472,2512961182&fm=200&gp=0.jpg")
        val llm: LinearLayoutManager = object : LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollHorizontally(): Boolean {
                return false

            }

            override fun canScrollVertically(): Boolean {

                return false
            }
        }
        val mainFinalAdapter = MainFinalAdapter(data)
        rv_custom.layoutManager = llm
        rv_custom.adapter = mainFinalAdapter
    }

    var fourRvData = java.util.ArrayList<String>()

    private fun initFourRv() {
        fourRvData.add("https://img14.360buyimg.com/n1/s546x546_jfs/t7243/249/2639307512/334952/36bd6321/59b26495N982225a7.jpg")
        fourRvData.add("https://img13.360buyimg.com/mobilecms/s500x500_jfs/t11929/154/1361565739/198113/ef6db8dc/5a0089c4Nb9df5575.jpg")
        fourRvData.add("https://img10.360buyimg.com/mobilecms/s500x500_jfs/t4225/51/2441838275/605882/dd1e029d/58d16144N8c39351f.jpg")
        fourRvData.add("https://img11.360buyimg.com/mobilecms/s500x500_jfs/t6085/338/3917460180/403794/4ab3ca13/595b092aN7e4cd251.jpg")
//
//
        val gridLayoutManager = object : GridLayoutManager(mContext, 4) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rv_four.layoutManager = gridLayoutManager
        val mainFourRvAdapter = MainFourRvAdapter(fourRvData)
        rv_four.adapter = mainFourRvAdapter
    }

    private fun initImage() {

        Picasso.with(mContext).load("https://img12.360buyimg.com/n1/s450x450_jfs/t7582/66/3048380492/71753/acde79b5/59b85824N836bb714.jpg").into(iv_show1)
        Picasso.with(mContext).load("https://img12.360buyimg.com/mobilecms/s500x500_jfs/t11017/29/401218495/81262/11528632/59eede69Nf0f10126.jpg").into(iv_show2)
    }

    private fun initMiddleNavigation() {
        var data = ArrayList<MainNavigationBean>()
        navigationIcon.indices.mapTo(data) { MainNavigationBean(navigationString[it], navigationIcon[it]) }
        val mainMiddleNavigationAdapter = MainMiddleNavigationAdapter(data)
        val gridLayoutManager = object : GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false) {
            override fun canScrollHorizontally(): Boolean {


                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rv_middle_natvigation.layoutManager = gridLayoutManager;
        rv_middle_natvigation.adapter = mainMiddleNavigationAdapter


        var itemDragAndSwipeCallback = ItemDragAndSwipeCallback(mainMiddleNavigationAdapter);
        var itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback);
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

    var navigationString = arrayOf("电技", "闲置", "美食", "租赁", "海外", "二手", "限时优惠", "夺宝", "日常")
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
