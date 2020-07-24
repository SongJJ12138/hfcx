package com.hfcx.user.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hfcx.user.R
import com.hfcx.user.network.Api
import com.hfcx.user.ui.user.*
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_mine.*
import kotlinx.android.synthetic.main.item_title.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult

class MineActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)
        StatusBarUtil.initStatus(window)
        iv_head.setImageURI(SPUtils.instance().getString(Const.User.USER_HEAD))
        tv_name.text = SPUtils.instance().getString(Const.User.USER_NAME)
        title_back.visibility=View.GONE
        title_back2.visibility=View.VISIBLE
        title_str.setText("个人中心")
        title_back2.setOnClickListener{
            this.finish()
        }
        tv_more.setOnClickListener {
            startActivityForResult<MoreActivity>(10)
        }
        tv_my_trip.setOnClickListener {
        }
        tv_rule.setOnClickListener {
            startActivity<H5Activity>("title" to "计费规则", "url" to Api.PRICE_RULE)
        }
        iv_head.onClick {
            startActivityForResult<EditActivity>(5)
        }
        tv_my_coupon.onClick {
            startActivity<MyCouponActivity>()
        }
        tv_my_score.onClick {
            startActivity<MyScoreActivity>()
        }
        tv_invite.onClick {
            startActivity<InviteActivity>()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            finish()
        }
    }

}