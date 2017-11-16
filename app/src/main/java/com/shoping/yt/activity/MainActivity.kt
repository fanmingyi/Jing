package com.shoping.yt.activity


import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.shoping.yt.R
import com.shoping.yt.databinding.ActivityMainBinding
import com.shoping.yt.fragment.ClassifyFragment
import com.shoping.yt.fragment.HomeFragment
import com.shoping.yt.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding;

    lateinit var sbtm: SystemBarTintManager;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        bind = DataBindingUtil.bind(rootView)

        initFragment()

        sbtm = SystemBarTintManager(this)


    }


    lateinit var homeFragment: HomeFragment

    var current = R.id.rb_home

    public fun getstm(): SystemBarTintManager {
        return sbtm
    }

    private fun initFragment() {

        val beginTransaction = supportFragmentManager.beginTransaction()

        homeFragment = HomeFragment()



        homeFragment.arguments


        beginTransaction.add(R.id.fl_containt, homeFragment, "" + R.id.rb_home)

        beginTransaction.commit()

        rg_navigation.setOnCheckedChangeListener({ group: RadioGroup?, checkedId: Int ->

            hildAndShowFragment(current, checkedId)

        })
    }

    private fun hildAndShowFragment(before: Int, current: Int) {

        val beginTransaction = supportFragmentManager.beginTransaction()

        val beforeFragment = supportFragmentManager.findFragmentByTag("" + before)

        var currentFragment = supportFragmentManager.findFragmentByTag("" + current)

        if (currentFragment == null) {
            when (current) {
                R.id.rb_classify -> {
                    currentFragment = ClassifyFragment()

                }
                R.id.rb_mime -> {
                    currentFragment = MineFragment()

                }
            }
        }

        beginTransaction.hide(beforeFragment)

        if (currentFragment.isAdded) {

            beginTransaction.show(currentFragment)
        } else {
            beginTransaction.add(R.id.fl_containt, currentFragment, "" + current)
        }

        this.current = current

        beginTransaction.commit()

    }



}
