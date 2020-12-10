package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.smallraw.library.smallstatuslayout.*

class StateEmpty : StatePage() {
    lateinit var tvEmptyMsg: TextView
    lateinit var imgEmpty: ImageView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_empty, container, false)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
        tvEmptyMsg = view.findViewById(R.id.tv_empty_msg)
        imgEmpty = view.findViewById(R.id.img_empty)

        (config ?: SmallStatus.globalConfig).apply {
            tvEmptyMsg.setText(errorMsg)
            imgEmpty.setImageResource(errorIcon)
        }
    }

    override fun enableReload(): Boolean = false
}