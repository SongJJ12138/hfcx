package com.hfcx.user.ui.Order.BusOrder

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.hfcx.user.R
import com.hfcx.user.fragment.BusFragment
import com.hfcx.user.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_order.*


class BusMainActivity : BaseActivity() {
    var fragments= ArrayList<Fragment>()
    public val busFragment by lazy {
        BusFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        fragments.add(busFragment)
        order_pager
        order_pager.adapter = object: FragmentStatePagerAdapter(supportFragmentManager){
            override fun getItem(p0: Int): Fragment {
                return fragments[p0]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }
        order_tab.setupWithViewPager(order_pager)
    }

}
