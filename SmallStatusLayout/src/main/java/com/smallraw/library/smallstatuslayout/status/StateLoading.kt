package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.smallraw.library.smallstatuslayout.*

class StateLoading : StatePage() {
    lateinit var tvLoadingMsg: TextView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_loading, container, false)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
        tvLoadingMsg = view.findViewById(R.id.tv_loading_msg)
        (config ?: SmallStatus.globalConfig).apply {
            tvLoadingMsg.setText(loadingMsg)
        }
    }

    override fun enableReload(): Boolean {
        return false
    }
}