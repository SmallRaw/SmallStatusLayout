package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.smallraw.library.smallstatuslayout.R
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.StatePage

class StateEmpty : StatePage() {
    private lateinit var tvEmptyMsg: TextView
    private lateinit var imgEmpty: ImageView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_empty, container, false)
    }

    override fun onStateViewCreate(view: View) {
        tvEmptyMsg = view.findViewById(R.id.tv_empty_msg)
        imgEmpty = view.findViewById(R.id.img_empty)

//        setEmptyMsg(MultiStatePage.config.emptyMsg)
//        setEmptyIcon(MultiStatePage.config.emptyIcon)
    }

    override fun enableReload(): Boolean = false

    fun setEmptyMsg(emptyMsg: String) {
        tvEmptyMsg.text = emptyMsg
    }

    fun setEmptyIcon(@DrawableRes emptyIcon: Int) {
        imgEmpty.setImageResource(emptyIcon)
    }
}