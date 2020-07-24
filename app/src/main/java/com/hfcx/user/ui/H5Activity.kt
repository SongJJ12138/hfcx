package com.hfcx.user.ui

import android.os.Bundle
import com.hfcx.user.R
import com.hfcx.user.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_base_title.*
import org.jetbrains.anko.backgroundColorResource

class H5Activity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.initStatus(window)
        titleBar.setTitleColor(R.color.textColor)
        titleBar.backgroundColorResource = R.color.white
        titleBar.leftView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_back_arrow,0,0,0)
    }
}