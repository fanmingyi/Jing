package com.shoping.yt.fragment


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.shoping.yt.APP
import com.shoping.yt.FLAG
import com.shoping.yt.FLAG.*
import com.shoping.yt.R
import com.shoping.yt.activity.MainActivity
import com.shoping.yt.adapter.CartShopAdapter
import com.shoping.yt.bean.CartGoodsBean
import com.shoping.yt.utils.DimenUtitls
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_classify.*
import kotlinx.android.synthetic.main.item_list_cart.*
import java.text.DecimalFormat
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

    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initNavigation()
        initAdapter()
        initBrodCast()
    }

    lateinit var myBroadCastReceiver: MyBroadCastReceiver

    private fun initBrodCast() {

        myBroadCastReceiver = MyBroadCastReceiver()

        val instance = LocalBroadcastManager.getInstance(mMainActivity)

        val intentFilter = IntentFilter(NATIVE_CARTCHECED_BRODCAST)
        val intentFilter2 = IntentFilter(NATIVE_CART_HAVE_GOOD)

        instance.registerReceiver(myBroadCastReceiver, intentFilter)
        instance.registerReceiver(myBroadCastReceiver, intentFilter2)


        cb_all.setOnCheckedChangeListener(cb_allListner)

    }

    val cb_allListner = CompoundButton.OnCheckedChangeListener { cb, isChecked ->

        dataAll
                .flatMap { it }
                .forEach {
                    it.setCheck(isChecked)
                    val intent = Intent(NATIVE_CARTCHECED_BRODCAST)
                    intent.putExtra(NATIVE_CARTCHECED_BRODCAST, it)
                    LocalBroadcastManager.getInstance(mMainActivity).sendBroadcast(intent)
//
                }

        rv_cart_inside
        cartShopAdapter.notifyDataSetChanged()
//        cartShopAdapter.myNotifyAll();

    }


    override fun onDestroy() {
        super.onDestroy()
        val instance = LocalBroadcastManager.getInstance(mMainActivity)
        instance.unregisterReceiver(myBroadCastReceiver)

    }

    var dataAll = ArrayList<List<CartGoodsBean>>()
    lateinit var cartShopAdapter: CartShopAdapter
    private fun initAdapter() {
        val dao = APP.getCartGoodsBeanDao()

        val list = dao.queryBuilder().build().list()
        if (list.size != 0)
            dataAll.add(list)


        cartShopAdapter = CartShopAdapter(dataAll, mMainActivity)

        val intentFilter = IntentFilter()

        intentFilter.addAction(NATIVE_CARTCHECED_BRODCAST)

        val linearLayoutManager = LinearLayoutManager(mMainActivity, LinearLayoutManager.VERTICAL, false)

        rv_goods.layoutManager = linearLayoutManager
        rv_goods.adapter = cartShopAdapter


    }


    val goods: ArrayList<String> = ArrayList()

    inner class MyBroadCastReceiver : BroadcastReceiver() {


        override fun onReceive(context: Context, intent: Intent) {

            val action = intent.action
            if (action == NATIVE_CART_HAVE_GOOD) {

                val parcelableExtra = intent.getParcelableExtra<CartGoodsBean>(NATIVE_CART_HAVE_GOOD)

                var list: ArrayList<CartGoodsBean>? = null

                list = if (dataAll.size == 0) {
                    ArrayList()
                } else {
                    dataAll[dataAll.size - 1] as ArrayList<CartGoodsBean>
                }
                if (((list != null && list.size >= 3) || dataAll.size == 0) && parcelableExtra != null) {
                    val newList = ArrayList<CartGoodsBean>()
                    newList.add(parcelableExtra)
                    dataAll.add(newList)
                    cartShopAdapter.notifyItemInserted(dataAll.size)
                } else if (parcelableExtra != null) {
                    list?.add(parcelableExtra)



                }
                cartShopAdapter.notifyDataSetChanged()

            } else if (action.equals(NATIVE_CARTCHECED_BRODCAST)) {
                val parcelableExtra = intent.getParcelableExtra<CartGoodsBean>(NATIVE_CARTCHECED_BRODCAST)
                val contains = goods.contains(parcelableExtra.id)

                val text = tv_prise.text
                var toFloat = text.toString().toFloat()

                //当前是滑动删除
                if (intent.getBooleanExtra(NATIVE_CART_IS_SWIED_DEL, false)) {
//
                    if (contains) {
                        toFloat -= parcelableExtra.prise
                        goods.remove(parcelableExtra.id)
                    }
//
                    var arrIndex = -1

                    dataAll.forEachIndexed({ index, arrayList ->
                        if (arrayList.size == 0) {
                            arrIndex = index
                            return@forEachIndexed
                        }
                    })
                    if (arrIndex != -1) {
                        dataAll.removeAt(arrIndex)
                        cartShopAdapter.notifyItemRemoved(arrIndex)
                    }


                } else {
                    if (contains) {

                        if (parcelableExtra.getIsCheck()) {

                        } else {
                            toFloat -= parcelableExtra.prise
                            goods.remove(parcelableExtra.id)
                        }

                    } else {


                        if (parcelableExtra.getIsCheck()) {
                            goods.add(parcelableExtra.id)
                            toFloat += parcelableExtra.prise
                        }
                    }


                    if (!parcelableExtra.getIsCheck()) {
                        cb_all.setOnCheckedChangeListener(null)
                        cb_all.isChecked = false
                        cb_all.setOnCheckedChangeListener(cb_allListner)
                    }

                    var count = 0

                    dataAll.forEach {
                        count += it.size
                    }

                    if (count == goods.size) {
                        cb_all.setOnCheckedChangeListener(null)
                        cb_all.isChecked = true
                        cb_all.setOnCheckedChangeListener(cb_allListner)
                    }

                }
                val decimalFormat = DecimalFormat("#0.00")

                val format = decimalFormat.format(toFloat)

                tv_prise.text = format.toString()


            }


        }
    }

    private fun initNavigation() {
        sbtm = mMainActivity.getstm()

        sbtm.setTintColor(mMainActivity.resources.getColor(R.color.titilegrey))

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
