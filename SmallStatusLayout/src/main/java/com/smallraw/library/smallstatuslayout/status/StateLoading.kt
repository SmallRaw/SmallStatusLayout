package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.smallraw.library.smallstatuslayout.R
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.StatePage

class StateLoading : StatePage() {
    private lateinit var tvLoadingMsg: TextView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_loading, container, false)
    }

    override fun onStateViewCreate(view: View) {
        tvLoadingMsg = view.findViewById(R.id.tv_loading_msg)
    }

    override fun enableReload(): Boolean {
        return false
    }
}